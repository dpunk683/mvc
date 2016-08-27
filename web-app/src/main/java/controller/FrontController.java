package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.Action;
import actions.container.ActionContainer;
import actions.container.ActionNotFoundException;
import by.pvt.academy.yarkovich.managers.ErrorManager;
import constants.PageNames;

public class FrontController extends HttpServlet {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 729554496074095235L;
		private ActionContainer actionContainer = ActionContainer.getInstance();
	    private ErrorManager errorManager = ErrorManager.getInstance();
	    
	    /** 
	     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */

	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String page = null;
	        try {
	            Action action = actionContainer.getAction(request);
	            page = action.execute(request, response);
	            if(page == null) {System.out.println("page = 0");
	                page = PageNames.INDEX_PAGE;
	            }
	            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
	            dispatcher.forward(request, response);
	            
	        } catch (ActionNotFoundException e) {
	            errorManager.writeError(request, e, "Unknown Action command: ", true);
	            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PageNames.ERR_PAGE);
	            dispatcher.forward(request, response);
	            
	        } catch (Exception e) {
	            errorManager.writeError(request, e, "Exception at FrontController: ", true);
	            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PageNames.ERR_PAGE);
	            dispatcher.forward(request, response);
	        }
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    /** 
	     * Returns a short description of the servlet.
	     * @return a String containing servlet description
	     */
	    @Override
	    public String getServletInfo() {
	        return "Front Controller";
	    }
	    
	    @Override
	    public void init() throws ServletException {
	        super.init();
	    }    
}
