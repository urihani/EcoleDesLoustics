package com.example.ecoledesloustics.games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.mathematics.MathActivity;
import com.example.ecoledesloustics.mathematics.MathsCategoryAdapter;
import com.example.ecoledesloustics.mathematics.MathsCategoryModel;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class GamesCategoryAdapter extends RecyclerView.Adapter<GamesCategoryAdapter.Viewholder>{
    private Context context;
    private UserModel userModel;
    private ArrayList<GamesCategoryModel> gamesCategoryModelArrayList;

    // Constructor
    public GamesCategoryAdapter(Context context,
                                ArrayList<GamesCategoryModel> gamesCategoryModelArrayList,
                                UserModel userModel) {
        this.context = context;
        this.gamesCategoryModelArrayList = gamesCategoryModelArrayList;
        this.userModel = userModel;
    }

    @NonNull
    @Override
    public GamesCategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_layout, parent, false);
        return new GamesCategoryAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesCategoryAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        GamesCategoryModel model = gamesCategoryModelArrayList.get(position);
        holder.itemCategoryImg.setImageResource(model.getImg());
        holder.itemCategoryNameTV.setText(model.getTitle());
        if (model.isTimed()) {
            holder.clockIV.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                final Intent intent;
                intent = new Intent(context, MemoryActivity.class);
                bundle.putParcelable("user", userModel);
                bundle.putParcelable("cat", model);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return gamesCategoryModelArrayList.size();
    }

    // View holder class for initializing of
    // views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView clockIV, itemCategoryImg;
        private TextView itemCategoryNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            clockIV = itemView.findViewById(R.id.idIVClock);
            itemCategoryImg = itemView.findViewById(R.id.idIVItemCategoryImage);
            itemCategoryNameTV = itemView.findViewById(R.id.idTVItemCategoryName);
        }
    }
}
