package com.oscar.ecommerce.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String sturshkUserId;
    private Date dateCreated;
    private String status;
    private List<Long> orderDetailsId;

    public String getSturshkUserId() {
        return sturshkUserId;
    }

    public void setSturshkUserId(String sturshkUserId) {
        this.sturshkUserId = sturshkUserId;
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

    public List<Long> getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(List<Long> orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "sturshkUserId=" + sturshkUserId +
                ", dateCreated=" + dateCreated +
                ", status='" + status + '\'' +
                ", orderDetailsId=" + orderDetailsId +
                '}';
    }
}
