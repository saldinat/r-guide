package com.example.tamara.comp3074_project;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private RestaurantRepository restaurantRepository;
    private LiveData<List<Restaurant>> allRestaurants;
    private List<Restaurant> restaurants;

    public  RestaurantViewModel(Application application){
        super(application);
        restaurantRepository = new RestaurantRepository(application);
        allRestaurants = restaurantRepository.getAllRestaurants();
    }

    public LiveData<List<Restaurant>> getAllRestaurants() {
        return allRestaurants;
    }


    public void insert(Restaurant restaurant){
        restaurantRepository.insert(restaurant);
    }

    public void deleteRestaurantWithName(String res) {restaurantRepository.deleteRestaurantWithName(res);}

    public void updateRestaurantWithName(String res, String address, String phone,
                                         String description,String tags)
    {restaurantRepository.updateRestaurantWithName(res, address, phone, description, tags);}


}
