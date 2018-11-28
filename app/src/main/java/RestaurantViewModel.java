
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private RestaurantRepository restaurantRepository;
    private LiveData<List<Restaurant>> allRestaurants;
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
}
