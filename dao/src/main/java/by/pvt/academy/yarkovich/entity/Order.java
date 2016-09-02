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
	private List<Product> list;
	public Order() {
        list = new LinkedList<Product>();
    }
    
    public Integer getLength() {
        return new Integer(list.size());
    }
    
    public void addProduct(Product product) {
        list.add(product);
    }
    
    public void removeProduct(Product product) {
        int id = product.getId();
        Product p = null;
        for(Iterator<Product> i = list.iterator(); i.hasNext() ; ) {
            p = i.next();
            if(p.getId() == id) {
                i.remove();
                break;
            }
        }
    }
    
    public List<Product> getList() {
        return list;
    }
    
    public void clear() {
        list.clear();
    }

	
}
