package by.pvt.academy.yarkovich.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="employee")
public class Employee extends PersistentObject implements Serializable {
	private static final long serialVersionUID = 12L;
	@Column
	private String login;
	@Column
	private String name;
	@Column
	private Integer type;
	@Column
	private String pass;
	@Column
	private String cardNum;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	@Override
	public String toString() {
		return "Waiter [login=" + login + ", name=" + name + ", type=" + type + ", pass=" + pass
				+ ", cardNum=" + cardNum + "]";
	}
	public Employee(String name, Integer type, String pass, String cardNum) {
		super();
		this.name = name;
		this.type = type;
		this.pass = pass;
		this.cardNum = cardNum;
	}
	public Employee() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNum == null) ? 0 : cardNum.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + type;
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
		Employee other = (Employee) obj;
		if (cardNum == null) {
			if (other.cardNum != null)
				return false;
		} else if (!cardNum.equals(other.cardNum))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
