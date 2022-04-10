package com.example.ecoledesloustics.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoledesloustics.MainActivity;
import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.games.GamesCategoryActivity;
import com.example.ecoledesloustics.games.MemoryActivity;
import com.example.ecoledesloustics.mathematics.MathsCategoryActivity;
import com.example.ecoledesloustics.quiz.QuizCategoryActivity;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.Viewholder>  {
    private Context context;
    private UserModel userModel;
    private ScoresTrackerModel scores;
    private ArrayList<DashBoardModel> dashBoardModelArrayList;

    // Constructor
    public DashBoardAdapter(Context context, ArrayList<DashBoardModel> dashBoardModelArrayList,
                            UserModel userModel, ScoresTrackerModel scores) {
        this.context = context;
        this.dashBoardModelArrayList = dashBoardModelArrayList;
        this.userModel = userModel;
        this.scores = scores;
    }

    @NonNull
    @Override
    public DashBoardAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_layout, parent, false);
        return new DashBoardAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        DashBoardModel model = dashBoardModelArrayList.get(position);
        holder.categoryIV.setImageResource(model.getImg());
        holder.categoryTV.setText(model.getTitle());
        if (model.getTitle().equals("Mathématiques")){
            holder.dashBoardPB.setProgress(scores.getMathProgress());
            holder.percentageTV.setText(Integer.toString(scores.getMathProgress()) + "%");
        } else if (model.getTitle().equals("Culture générale")){
            holder.dashBoardPB.setProgress(scores.getCultureProgress());
            holder.percentageTV.setText(Integer.toString(scores.getCultureProgress()) + "%");
        } else if (model.getTitle().equals("Géographie")){
            holder.dashBoardPB.setProgress(scores.getGeographyProgress());
            holder.percentageTV.setText(Integer.toString(scores.getGeographyProgress()) + "%");
        } else if (model.getTitle().equals("Jeux")){
            holder.dashBoardPB.setProgress(scores.getGamesProgress());
            holder.percentageTV.setText(Integer.toString(scores.getGamesProgress()) + "%");
        }
        holder.dashBoardPB.setIndeterminate(false);

        // routes
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                final Intent intent;
                switch (holder.getAdapterPosition()){
                    case 0:
                        intent = new Intent(context, MathsCategoryActivity.class);
                        break;
                    case 1:
                        bundle.putString("typeOfQuiz", "Culture générale");
                        intent = new Intent(context, QuizCategoryActivity.class);
                        break;
                    case 2:
                        bundle.putString("typeOfQuiz", "Géographie");
                        intent = new Intent(context, QuizCategoryActivity.class);
                        break;
                    case 3:
                        intent = new Intent(context, GamesCategoryActivity.class);
                        break;
                    default:
                        intent = new Intent(context, MainActivity.class);
                        break;
                }
                bundle.putParcelable("user", userModel);
                bundle.putParcelable("category", model);
                bundle.putParcelable("scores", scores);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return dashBoardModelArrayList.size();
    }

    // View holder class for initializing of
    // views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView categoryIV;
        private TextView categoryTV, percentageTV;
        private ProgressBar dashBoardPB;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            categoryIV = itemView.findViewById(R.id.idIVCategoryImage);
            categoryTV = itemView.findViewById(R.id.idTVCategoryName);
            dashBoardPB = itemView.findViewById(R.id.idPBDashBoardProgress);
            percentageTV = itemView.findViewById(R.id.idTVPercentage);
        }
    }
}
