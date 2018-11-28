

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>{

    class RestaurantViewHolder extends RecyclerView.ViewHolder{
        private  final TextView restaurantItemView;

        private RestaurantViewHolder(View itemView){
            super(itemView);
            restaurantItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private List<Restaurant> restaurants;

    RestaurantListAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View itemView = inflater.inflate(R.layout.rec_item_layout, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int pos) {
        if(restaurants!=null){
            Restaurant current = restaurants.get(pos);
            String fullName = current.getRestaurant();
            restaurantViewHolder.restaurantItemView.setText(fullName);
        } else {
            restaurantViewHolder.restaurantItemView.setText("No data!");
        }
    }

    @Override
    public int getItemCount() {
        if(restaurants!=null)
            return restaurants.size();
        return 0;
    }

    void setRestaurants(List<Restaurant> restaurants){
        this.restaurants = restaurants;
        notifyDataSetChanged();

    }
}




