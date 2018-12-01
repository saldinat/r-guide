package com.example.tamara.comp3074_project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "restaurant_table")


public class Restaurant {
    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescrirption() {
        return description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescrirption(String description) {
        this.description = description;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="restaurant")

    private String restaurant;
    private String address;
    private String phone;
    private String description;
    private String tags;

    public Restaurant(String restaurant, String address, String phone, String description,
                      String tags) {
        this.restaurant = restaurant;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.tags = tags;
    }

    public void setRestaurant(@NonNull String restaurant) {
        this.restaurant = restaurant;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }
    public String getDescription() {
        return description;
    }


    public void setAffiliation(String address) {
        this.address = address;
    }

    public void setEmail(String phone) {
        this.phone = phone;
    }

    public void setShortBio(String descirption) {
        this.description = descirption;
    }

    public String getAffiliation() {
        return address;
    }

    public String getEmail() {
        return phone;
    }

    public String getShortBio() {
        return description;
    }

    public String getRestaurant(){
        return restaurant;
    }
}
