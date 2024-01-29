package com.oscar.ecommerce.DTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
    private String name;

    private BigDecimal price;

    private List<String> urlImages;

    private String description;
    private Long categoryId;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(List<String> urlImages) {
        this.urlImages = urlImages;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", urlImages=" + urlImages +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
