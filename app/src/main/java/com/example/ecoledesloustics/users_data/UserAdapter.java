package com.example.ecoledesloustics.users_data;

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

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder> {

    private Context context;
    private List<UserModel> userModelArrayList;

    // Constructor
    public UserAdapter(Context context, List<UserModel> userModelArrayList) {
        this.context = context;
        this.userModelArrayList = userModelArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        UserModel model = userModelArrayList.get(position);
        holder.firstNameTV.setText(model.getFirstName());
        holder.lastNameTV.setText(model.getLastName());
        holder.profileIV.setImageResource(model.getImg());

        // route to dashboard
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DashBoardActivity.class);
                intent.putExtra("user", model);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return userModelArrayList.size();
    }

    // View holder class for initializing of 
    // views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView profileIV;
        private TextView firstNameTV, lastNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileIV = itemView.findViewById(R.id.idIVProfileImage);
            firstNameTV = itemView.findViewById(R.id.idTVFirstName);
            lastNameTV = itemView.findViewById(R.id.idTVLastName);
        }
    }

    public void swap(List<UserModel> list){
        if (userModelArrayList != null) {
            userModelArrayList.clear();
            userModelArrayList.addAll(list);
        }
        else {
            userModelArrayList = list;
        }
        notifyDataSetChanged();
    }
}