package by.pvt.academy.yarkovich.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name="clients")
public class Client extends PersistentObject implements Serializable  {
    private static final long serialVersionUID = 2L;

	@Column
	private String name;
	@Column
	private String dateOfBirth;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String loyalityCardNo;
	@Column
	private String oldLoyalityCardNo;
	@Column
	private double spentMoney;
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List <AcceptedOrder> acceptedOrderSet;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoyalityCardNo() {
		return loyalityCardNo;
	}

	public void setLoyalityCardNo(String loyalityCardNo) {
		this.loyalityCardNo = loyalityCardNo;
	}

	public String getOldLoyalityCardNo() {
		return oldLoyalityCardNo;
	}

	public void setOldLoyalityCardNo(String oldLoyalityCardNo) {
		this.oldLoyalityCardNo = oldLoyalityCardNo;
	}

	public double getSpentMoney() {
		return spentMoney;
	}

	public void setSpentMoney(double spentMoney) {
		this.spentMoney = spentMoney;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((loyalityCardNo == null) ? 0 : loyalityCardNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oldLoyalityCardNo == null) ? 0 : oldLoyalityCardNo.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		long temp;
		temp = Double.doubleToLongBits(spentMoney);
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
		Client other = (Client) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loyalityCardNo == null) {
			if (other.loyalityCardNo != null)
				return false;
		} else if (!loyalityCardNo.equals(other.loyalityCardNo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (oldLoyalityCardNo == null) {
			if (other.oldLoyalityCardNo != null)
				return false;
		} else if (!oldLoyalityCardNo.equals(other.oldLoyalityCardNo))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (Double.doubleToLongBits(spentMoney) != Double.doubleToLongBits(other.spentMoney))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", dateOfBirth=" + dateOfBirth + ", phone=" + phone + ", email="
				+ email + ", loyalityCardNo=" + loyalityCardNo + ", oldLoyalityCardNo=" + oldLoyalityCardNo
				+ ", spentMoney=" + spentMoney + "]";
	}

	public Client(int id, String name, String dateOfBirth, String phone, String email, String loyalityCardNo,
			String oldLoyalityCardNo, double spentMoney) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.email = email;
		this.loyalityCardNo = loyalityCardNo;
		this.oldLoyalityCardNo = oldLoyalityCardNo;
		this.spentMoney = spentMoney;
	}
	
	public Client() {
		super();
	}
	
	

}
