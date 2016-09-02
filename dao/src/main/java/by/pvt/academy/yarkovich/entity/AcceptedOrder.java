package by.pvt.academy.yarkovich.entity;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "rcpbody")
public class AcceptedOrder extends PersistentObject implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private Integer productNo;
    @Column
    private Double price;
    @Column
    private String starttime;
    @Column(name = "remote_ip_id")
    private Integer ip;
    @Column
    private Integer status;
    private String prodname;
    private int tableNum;
    private List<Product> list;
    @ManyToOne
    @JoinColumn (name = "client_id")
    private Client client;

    public AcceptedOrder() {
        list = new LinkedList<Product>();
    }

    public void clear() {
        list.clear();
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
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

    @Override
    public String toString() {
        return "AcceptedOrder [productNo=" + productNo + ", price=" + price + ", status=" + status
                + ", ip=" + ip + ", starttime=" + starttime + ", prodname=" + prodname + ", tableNum=" + tableNum
                + ", list=" + list + "]";
    }

}
