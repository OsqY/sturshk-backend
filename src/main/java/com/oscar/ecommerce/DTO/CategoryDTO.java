package com.oscar.ecommerce.DTO;

import java.util.List;

public class CategoryDTO {
    private String name;
    private List<Long> productIds;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                ", productIds=" + productIds +
                '}';
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

}
