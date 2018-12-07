package com.example.tamara.comp3074_project;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Restaurant.class}, version = 6)
public abstract class RestaurantDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();
    private static volatile RestaurantDatabase instance;
    static RestaurantDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (RestaurantDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            RestaurantDatabase.class, "restaurant_database")
                            .addCallback(callback)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }

    private static Callback callback = new Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateAsyncTask(instance.restaurantDao()).execute();
        }
    };

    private static class populateAsyncTask extends AsyncTask<Void, Void,Void>{
        private final RestaurantDao dao;
        populateAsyncTask(RestaurantDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new Restaurant("Restaurant Test", "160 Kendal Ave", "416-222-34-34",
                    "Family style","family, hearty", "5"));
            return null;
        }
    }
}
