package by.pvt.academy.yarkovich.entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by dima on 05.09.2016.
 */
@Entity
@Table (name = "tableIPs")
public class TablePOS extends PersistentObject implements Serializable {
    private static final long serialVersionUID = 10L;

    @Column
    private String ipAdress;
    @Column
    private Integer tableID;

    public TablePOS() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public TablePOS(String ipAdress, Integer tableID) {
        this.ipAdress = ipAdress;
        this.tableID = tableID;
    }
}
