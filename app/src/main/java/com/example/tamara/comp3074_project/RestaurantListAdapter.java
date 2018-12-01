package com.example.tamara.comp3074_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tamara.comp3074_project.MainActivity;
import com.example.tamara.comp3074_project.R;
import com.example.tamara.comp3074_project.Restaurant;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.app.ActivityCompat.startActivityForResult;
import static com.example.tamara.comp3074_project.MainActivity.NEW_WORD;
import static java.lang.System.in;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>{
    private Context mContext;
    private List<Restaurant> restaurants;


    public RestaurantListAdapter(Context context, List<Restaurant> restaurants){
        this.restaurants = restaurants;
        this.mContext = context;
    }

    public RestaurantListAdapter(MainActivity restaurantsActivity) {
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{
        private  final TextView restaurantItemView;
        public LinearLayout linearLayout;

        private RestaurantViewHolder(View itemView){
            super(itemView);
            restaurantItemView = itemView.findViewById(R.id.textViewName);
            linearLayout = itemView.findViewById(R.id.linearlayout);
            itemView.setTag(itemView);
        }
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_item_layout, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }
    public final static int DELETE_RES = 2;
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int pos) {
        final Restaurant pItem = restaurants.get(pos);
        if(restaurants!=null){
            Restaurant current = restaurants.get(pos);
            String fullName = current.getRestaurant();
            restaurantViewHolder.restaurantItemView.setText(fullName);
        } else {
            restaurantViewHolder.restaurantItemView.setText("No data!");
        }

        restaurantViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateRestaurantActivity.class);
                //Intent i_start = new Intent(v.getContext(), MainActivity.class);
                i.putExtra("name", pItem.getRestaurant());
                i.putExtra("address", pItem.getAddress());
                i.putExtra("phone", pItem.getPhone());
                i.putExtra("description", pItem.getDescription());
                i.putExtra("tags", pItem.getTags());
                //v.getContext().startActivity(i);
                ((Activity) v.getContext()).startActivityForResult(i, DELETE_RES);
                //Toast.makeText(v.getContext(), "You clicked " + pItem.getRestaurant(), Toast.LENGTH_LONG).show();
            }

        });

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

    } }







