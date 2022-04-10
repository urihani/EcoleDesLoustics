package com.example.ecoledesloustics.games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.quiz.QuizCategoryAdapter;
import com.example.ecoledesloustics.quiz.QuizCategoryModel;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class GamesCategoryActivity extends AppCompatActivity {
    private RecyclerView classicCategoryRV;
    private RecyclerView timedCategoryRV;
    private ArrayList<GamesCategoryModel> classicCategoryModel;
    private ArrayList<GamesCategoryModel> timedCategoryModel;
    private DatabaseClient mDb;
    UserModel userModel;
    ScoresTrackerModel scoresModel;
    int numExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_category);

        Bundle bundle = getIntent().getExtras();
        userModel = bundle.getParcelable("user");
        scoresModel = bundle.getParcelable("scores");

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        setAdapterForMemory();

        // count activities to help compute the value of the progress bar
        numExercises =
                classicCategoryModel.size() +
                        timedCategoryModel.size();
        updateScore();

        // user button action
        ImageButton userBT = findViewById(R.id.idIBUser);
        userBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SettingsActivity.class);

                intent.putExtra("user", userModel);

                startActivity(intent);
            }
        });

        // previous button action
        ImageButton previousBT = findViewById(R.id.idIBPrevious);
        previousBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setAdapterForMemory() {
        // ----------------------------------------------------------------------------------------
        // adding data to classic activity
        classicCategoryRV = findViewById(R.id.idRVclassicCategory);
        classicCategoryModel = new ArrayList<>();
        classicCategoryModel.add(new GamesCategoryModel(0, R.drawable.ic_robot0, "Robots", false,
                46, "Memory"));
        classicCategoryModel.add(new GamesCategoryModel(1, R.drawable.ic_animal0, "Animaux", false,
                46, "Memory"));
        classicCategoryModel.add(new GamesCategoryModel(2, R.drawable.ic_vegetable0, "Légumes", false,
                46, "Memory"));
        classicCategoryModel.add(new GamesCategoryModel(3, R.drawable.ic_fruit0, "Fruits", false,
                46, "Memory"));
        classicCategoryModel.add(new GamesCategoryModel(4, R.drawable.ic_space0, "Space", false,
                46, "Memory"));
        classicCategoryModel.add(new GamesCategoryModel(5, R.drawable.ic_music0, "Music", false,
                46, "Memory"));

        // we are initializing our adapter class and passing our arraylist to it.
        GamesCategoryAdapter classicGamesCategoryAdapter = new GamesCategoryAdapter(this,
                classicCategoryModel,
                userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager memoryLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        classicCategoryRV.setLayoutManager(memoryLayoutManager);
        classicCategoryRV.setAdapter(classicGamesCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to timed activity
        timedCategoryRV = findViewById(R.id.idRVTimedCategory);
        timedCategoryModel = new ArrayList<>();
        timedCategoryModel.add(new GamesCategoryModel(6, R.drawable.ic_robot0, "Robots", true,
                46, "Memory timed"));
        timedCategoryModel.add(new GamesCategoryModel(7, R.drawable.ic_animal0, "Animaux", true,
                46, "Memory timed"));
        timedCategoryModel.add(new GamesCategoryModel(8, R.drawable.ic_vegetable0, "Légumes", true,
                46, "Memory timed"));
        timedCategoryModel.add(new GamesCategoryModel(9, R.drawable.ic_fruit0, "Fruits", true,
                46, "Memory timed"));
        timedCategoryModel.add(new GamesCategoryModel(10, R.drawable.ic_space0, "Espace", true,
                46, "Memory timed"));
        timedCategoryModel.add(new GamesCategoryModel(11, R.drawable.ic_music0, "Music", true,
                46, "Memory timed"));

        // we are initializing our adapter class and passing our arraylist to it.
        GamesCategoryAdapter timedGamesCategoryAdapter = new GamesCategoryAdapter(this,
                timedCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager timedGamesLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        timedCategoryRV.setLayoutManager(timedGamesLayoutManager);
        timedCategoryRV.setAdapter(timedGamesCategoryAdapter);
    }

    private void updateScore() {
        class UpdateScore extends AsyncTask<Void, Void, ScoresTrackerModel> {

            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                ScoresTrackerModel updatedScore =
                        mDb.getAppDatabase().scoresDAO().getScoreFromUserId(userModel.getId());

                    updatedScore.setTotalOfGamesExercises(numExercises);

                mDb.getAppDatabase().scoresDAO().update(updatedScore);
                return updatedScore;
            }

            @Override
            protected void onPostExecute(ScoresTrackerModel scoresModel) {
                super.onPostExecute(scoresModel);
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        UpdateScore update = new UpdateScore();
        update.execute();
    }
}