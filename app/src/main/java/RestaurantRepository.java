

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

}
