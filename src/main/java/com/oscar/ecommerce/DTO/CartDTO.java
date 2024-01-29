package com.oscar.ecommerce.DTO;

import java.util.List;

public class CartDTO {
    private String sturkUserId;
    private List<Long> productsId;

    public String getSturkUserId() {
        return sturkUserId;
    }

    public void setSturkUserId(String sturkUserId) {
        this.sturkUserId = sturkUserId;
    }

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "sturkUserId='" + sturkUserId + '\'' +
                ", productsId=" + productsId +
                '}';
    }
}
