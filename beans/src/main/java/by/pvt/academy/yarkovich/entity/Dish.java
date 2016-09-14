package by.pvt.academy.yarkovich.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Dish extends PersistentObject implements Serializable {
	private static final long serialVersionUID = 5L;
	@Column
	private String name;
	@Column
	private Double price;
	@Column
	private Double secondPrice=0.0;
	@Column
	private String about;
	@Column
	private String picture;

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSecondPrice() {
		return secondPrice;
	}
	public void setSecondPrice(double secondPrice) {
		this.secondPrice = secondPrice;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(secondPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dish other = (Dish) obj;
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(secondPrice) != Double.doubleToLongBits(other.secondPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dish [name=" + name + ", price=" + price + ", secondPrice=" + secondPrice + ", about="
				+ about + ", picture=" + picture + "]";
	}
	public Dish() {
		// TODO Auto-generated constructor stub
	}
	
	
}
