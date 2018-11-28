

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "restaurant_table")


public class Restaurant {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="restaurant")

    private String restaurant;
    private String address;
    private String phone;
    private String descirption;
    private String tags;




    public Restaurant(@NonNull String restaurant, String address, String phone, String descirption,
                      String tags) {
        this.restaurant = restaurant;
        this.address = address;
        this.phone = phone;
        this.descirption = descirption;
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

    public void setAffiliation(String address) {
        this.address = address;
    }

    public void setEmail(String phone) {
        this.phone = phone;
    }

    public void setShortBio(String descirption) {
        this.descirption = descirption;
    }





    public String getAffiliation() {
        return address;
    }

    public String getEmail() {
        return phone;
    }

    public String getShortBio() {
        return descirption;
    }


    public String getRestaurant(){
        return restaurant;
    }
}
