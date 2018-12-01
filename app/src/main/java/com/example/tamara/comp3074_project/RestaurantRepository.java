package com.example.tamara.comp3074_project;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RestaurantRepository {
    private RestaurantDao restaurantDao;
    private LiveData<List<Restaurant>> allRestaurants;

    RestaurantRepository(Application application){
        RestaurantDatabase db = RestaurantDatabase.getDatabase(application);
        restaurantDao = db.restaurantDao();
        allRestaurants = restaurantDao.getAllRestaurants();
    }

    public LiveData<List<Restaurant>> getAllRestaurants() {
        return allRestaurants;
    }

    public void insert(Restaurant restaurant){
        new insertAsyncTask(restaurantDao).execute(restaurant);
    }

    public void deleteRestaurantWithName(String res){
        new deleteRestaurantWithNameAsyncTask(restaurantDao).execute(res);
    }

    public void updateRestaurantWithName(String res, String address, String phone, String description,String tags){
        new updateRestaurantWithNameAsyncTask(restaurantDao).execute(res);
    }

    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void>{
        private RestaurantDao dao;
        insertAsyncTask(RestaurantDao wdao){
            dao = wdao;
        }
        @Override
        protected Void doInBackground(Restaurant... params){

            dao.insert(params[0]);

            return null;
        }
    }

    private static class deleteRestaurantAsyncTask extends AsyncTask<Restaurant, Void, Void>{
        private RestaurantDao mAsyncTaskDao;

        public deleteRestaurantAsyncTask(RestaurantDao restaurantDao) {
            mAsyncTaskDao = restaurantDao;

        }
        @Override

        protected Void doInBackground(final Restaurant... params) {
            mAsyncTaskDao.deleteRestaurant(params[0]);
            return null;
        }
    }

    private static class deleteRestaurantWithNameAsyncTask extends AsyncTask<String, Void, Void>{
        private RestaurantDao mAsyncTaskDao;
        public deleteRestaurantWithNameAsyncTask(RestaurantDao restaurantDao) {
            mAsyncTaskDao = restaurantDao;

        }

        @Override
        protected Void doInBackground(String... strings) {
            mAsyncTaskDao.deleteRestaurantWithName(strings[0]);
            return null;
        }
    }

    private static class updateRestaurantWithNameAsyncTask extends AsyncTask<String, Void, Void>{
        private RestaurantDao mAsyncTaskDao;
        public updateRestaurantWithNameAsyncTask(RestaurantDao restaurantDao) {
            mAsyncTaskDao = restaurantDao;

        }

        @Override
        protected Void doInBackground(String... strings) {
            mAsyncTaskDao.updateRestaurantWithName(strings[0], strings[1], strings[2], strings[3],
                    strings[4]);
            return null;
        }
    }

}
