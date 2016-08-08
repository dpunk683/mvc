package by.pvt.academy.yarkovich.entity;

import java.util.LinkedList;
import java.util.List;

public class AcceptedOrder {
	private int id;
	private int productNo;
	private double price;
	private int status;
	private int ip;
	private String starttime;
	private String prodname;
	private int tableNum;

	@Override
	public String toString() {
		return "AcceptedOrder [id=" + id + ", productNo=" + productNo + ", price=" + price + ", status=" + status
				+ ", ip=" + ip + ", starttime=" + starttime + ", prodname=" + prodname + ", tableNum=" + tableNum
				+ ", list=" + list + "]";
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	private List<Product> list;

	public AcceptedOrder() {
		list = new LinkedList<Product>();
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void clear() {
		list.clear();
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
