package actions.dishes;

import actions.Action;
import by.pvt.academy.yarkovich.DishService;
import by.pvt.academy.yarkovich.entity.Dish;
import by.pvt.academy.yarkovich.exceptions.DishNameErrorException;
import by.pvt.academy.yarkovich.utils.HibernateUtil;
import constants.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by dzmitryyarkovich on 9/14/16.
 */
public class AddNewDishAction extends Action{
    private static AddNewDishAction instance;

    private AddNewDishAction() {
    }

    public synchronized static AddNewDishAction getInstance() {
        if (instance == null) {
            instance = new AddNewDishAction();
        }
        return instance;
    }

    public synchronized String execute(HttpServletRequest request, HttpServletResponse response) {
        // Create path components to save the file
        final String path = "images/dishes";
        Part filePart = null;
        try {
            filePart = request.getPart("file");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (filecontent != null) {
                try {
                    filecontent.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
        Dish dish = new Dish();
        dish.setName(request.getParameter("name"));
        dish.setAbout(request.getParameter("about"));
        dish.setPrice(Double.parseDouble(request.getParameter("price")));
        dish.setSecondPrice(Double.parseDouble(request.getParameter("secondPrice")));
        dish.setPicture(fileName);
        try {
            DishService.getInstance().validation(dish);
            DishService.getInstance().addDish(dish);
        } catch (DishNameErrorException e) {
            return PageNames.DISH_ALREADY_EXISTS_PAGE;
        } finally {
            HibernateUtil.closeSession();
        }
        return PageNames.SUCCESS_DISH_ADDED_PAGE;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
