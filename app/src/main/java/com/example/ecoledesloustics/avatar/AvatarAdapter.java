package com.example.ecoledesloustics.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.dashboard.DashBoardActivity;
import com.example.ecoledesloustics.users_data.UserAdapter;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.List;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.Viewholder>  {
    private Context context;
    private List<Integer> imagesArrayList;

    // Constructor
    public AvatarAdapter(Context context, List<Integer> imagesArrayList) {
        this.context = context;
        this.imagesArrayList = imagesArrayList;
    }

    @NonNull
    @Override
    public AvatarAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avatar_card_layout, parent, false);
        return new AvatarAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        Integer image = imagesArrayList.get(position);
        holder.profileIV.setImageResource(image);

        // route to registration with selected avatar
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).setResult(Activity.RESULT_OK,
                        new Intent().putExtra("image", image));
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return imagesArrayList.size();
    }

    // View holder class for initializing of
    // views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView profileIV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileIV = itemView.findViewById(R.id.idIVProfileImage);
        }
    }
}
