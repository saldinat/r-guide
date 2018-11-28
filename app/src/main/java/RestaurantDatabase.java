
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Restaurant.class}, version = 4)
public abstract class RestaurantDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();
    private static volatile RestaurantDatabase instance;
    static RestaurantDatabase getDatabase(final Context context){
        if(instance == null){
            synchronized (RestaurantDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            RestaurantDatabase.class, "restaurant_database").addCallback(callback).fallbackToDestructiveMigration().build();

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
            dao.insert(new Restaurant("name", "aff", "email", "shortbio","shortbio,tag,tag"));
            dao.insert(new Restaurant("name", "aff", "email", "shortbio","shortbio,tag,tag"));
            dao.insert(new Restaurant("name", "aff", "email", "shortbio","shortbio,tag,tag"));
            dao.insert(new Restaurant("name", "aff", "email", "shortbio","shortbio,tag,tag"));
            return null;
        }
    }
}
