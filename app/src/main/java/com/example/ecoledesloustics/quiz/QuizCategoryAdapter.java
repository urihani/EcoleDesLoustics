package com.example.ecoledesloustics.quiz;

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
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class QuizCategoryAdapter extends RecyclerView.Adapter<QuizCategoryAdapter.Viewholder> {
    private Context context;
    private UserModel userModel;
    private ScoresTrackerModel scoreModel;
    private ArrayList<QuizCategoryModel> quizCategoryModelArrayList;
    private String typeOfQuiz;

    // Constructor
    public QuizCategoryAdapter(Context context,
                               ArrayList<QuizCategoryModel> quizCategoryModelArrayList,
                               UserModel userModel, String typeOfQuiz, ScoresTrackerModel scoreModel) {
        this.context = context;
        this.quizCategoryModelArrayList = quizCategoryModelArrayList;
        this.userModel = userModel;
        this.typeOfQuiz = typeOfQuiz;
        this.scoreModel = scoreModel;
    }

    @NonNull
    @Override
    public QuizCategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_layout, parent, false);
        return new QuizCategoryAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizCategoryAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        QuizCategoryModel model = quizCategoryModelArrayList.get(position);
        holder.itemCategoryImg.setImageResource(model.getImg());
        holder.itemCategoryNameTV.setText(model.getTitle());
        if (scoreModel.getCultureCompleted().contains(model.getId())) {
            holder.checkedIV.setVisibility(View.VISIBLE);
            holder.itemCategoryImg.setVisibility(View.INVISIBLE);
        } else if (scoreModel.getGeographyCompleted().contains(model.getId())) {
            holder.checkedIV.setVisibility(View.VISIBLE);
            holder.itemCategoryImg.setVisibility(View.INVISIBLE);
        } else {
            holder.checkedIV.setVisibility(View.INVISIBLE);
            holder.itemCategoryImg.setVisibility(View.VISIBLE);
        }
        if (model.isTimed()) {
            holder.clockIV.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                final Intent intent;
                intent = new Intent(context, QuizActivity.class);
                bundle.putParcelable("user", userModel);
                bundle.putParcelable("cat", model);
                bundle.putString("typeOfQuiz", typeOfQuiz);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return quizCategoryModelArrayList.size();
    }

    // View holder class for initializing of
    // views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView clockIV, itemCategoryImg, checkedIV;
        private TextView itemCategoryNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            clockIV = itemView.findViewById(R.id.idIVClock);
            itemCategoryImg = itemView.findViewById(R.id.idIVItemCategoryImage);
            itemCategoryNameTV = itemView.findViewById(R.id.idTVItemCategoryName);
            checkedIV = itemView.findViewById(R.id.idIVChecked);
        }
    }
}
