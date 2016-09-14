package by.pvt.academy.yarkovich.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Dish> list;
	public Order() {
        list = new LinkedList<Dish>();
    }
    
    public Integer getLength() {
        return new Integer(list.size());
    }
    
    public void addProduct(Dish dish) {
        list.add(dish);
    }
    
    public void removeProduct(Dish dish) {
        Long id = dish.getId();
        Dish p = null;
        for(Iterator<Dish> i = list.iterator(); i.hasNext() ; ) {
            p = i.next();
            if(p.getId() == id) {
                i.remove();
                break;
            }
        }
    }
    
    public List<Dish> getList() {
        return list;
    }
    
    public void clear() {
        list.clear();
    }

	
}
