
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Restaurant restaurant);

    @Query("DELETE FROM restaurant_table")
    void deleteAll();

    @Query("SELECT * FROM restaurant_table ORDER BY restaurant")
    LiveData<List<Restaurant>> getAllRestaurants();

    //@Query("SELECT * FROM restaurant_table WHERE restaurant= :restaurant")
    //Restaurant getRestaurant(String restaurant);



}
