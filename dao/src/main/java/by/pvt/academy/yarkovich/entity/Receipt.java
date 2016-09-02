package by.pvt.academy.yarkovich.entity;

import java.util.List;

public class Receipt {
	private int id;
	private double sum;

	public List<AcceptedOrder> getList() {
		return list;
	}

	public void setList(List<AcceptedOrder> list) {
		this.list = list;
	}

	List<AcceptedOrder> list;
	private String starttime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

}
