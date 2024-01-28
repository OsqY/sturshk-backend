package com.oscar.ecommerce.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private SturshkUser sturshkUser;
    private Date dateCreated;
    private String status;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sturshkUser=" + sturshkUser +
                ", dateCreated=" + dateCreated +
                ", status='" + status + '\'' +
                ", orderDetailList=" + orderDetailList +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SturshkUser getSturshkUser() {
        return sturshkUser;
    }

    public void setSturshkUser(SturshkUser sturshkUser) {
        this.sturshkUser = sturshkUser;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }


}
