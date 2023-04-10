package com.directinsuranceexercise.rest.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public abstract class GenericAdvertisement {

    protected long id;

    @NotEmpty
    @Pattern(regexp = "^(asset|electricity|car)$", message = "category must be 'asset','car' or 'electricity'")
    private String category;

    @Min(value = 0, message = "Price must be a non-negative integer")
    protected Integer price;

    @NotEmpty
    private String contactPhoneNumber;

    @NotEmpty
    private String contactName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
