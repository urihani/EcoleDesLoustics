package com.example.ecoledesloustics.dashboard;

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
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    private DatabaseClient mDb;
    private UserModel userModel;
    private RecyclerView categoryRV;
    private ArrayList<DashBoardModel> dashBoardModelArrayList = new ArrayList<>();
    DashBoardAdapter dashBoardAdapter;
    ScoresTrackerModel scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // get user object from activity
        if (getIntent().getExtras() != null) {
            userModel = getIntent().getParcelableExtra("user");
        }

        // welcome message
        TextView welcomeUserTV = findViewById(R.id.idTVWelcomeUser);
        welcomeUserTV.setText("Bonjour " + userModel.getFirstName() + ",");


        // user button
        ImageButton userIB = findViewById(R.id.idIBUser);
        userIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SettingsActivity.class);

                intent.putExtra("user", userModel);

                startActivity(intent);
            }
        });
    }

    private void getScores() {
        class GetScores extends AsyncTask<Void, Void, ScoresTrackerModel> {
            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                scores = mDb.getAppDatabase()
                        .scoresDAO()
                        .getScoreFromUserId(userModel.getId());

                // if the scores for the actual user does not exist we pre-populate the data
                if (scores == null) {
                    ScoresTrackerModel newScores = new ScoresTrackerModel(userModel.getId());
                    mDb.getAppDatabase().scoresDAO().insert(newScores);
                    scores = mDb.getAppDatabase()
                            .scoresDAO()
                            .getScoreFromUserId(userModel.getId());
                }
                return scores;
            }

            @Override
            protected void onPostExecute(ScoresTrackerModel scores) {
                super.onPostExecute(scores);
                // here we have created new array list and added data to it.
                categoryRV = findViewById(R.id.idRVCategory);
                dashBoardModelArrayList = new ArrayList<>();
                dashBoardModelArrayList.add(new DashBoardModel(R.drawable.ic_mathematic, "Mathématiques",
                        scores.getMathProgress()));
                dashBoardModelArrayList.add(new DashBoardModel(R.drawable.ic_quiz, "Culture générale",
                        scores.getCultureProgress()));
                dashBoardModelArrayList.add(new DashBoardModel(R.drawable.ic_geography, "Géographie", scores.getGeographyProgress()));
                dashBoardModelArrayList.add(new DashBoardModel(R.drawable.ic_puzzle, "Jeux",
                        scores.getGamesProgress()));

                // we are initializing our adapter class and passing our arraylist to it.
                dashBoardAdapter = new DashBoardAdapter(DashBoardActivity.this,
                        dashBoardModelArrayList,
                        userModel, scores);

                // below line is for setting a layout manager for our recycler view.
                // here we are creating vertical list so we will provide orientation as vertical
                LinearLayoutManager linearLayoutManager =
                        new LinearLayoutManager(DashBoardActivity.this,
                                LinearLayoutManager.VERTICAL, false);

                // in below two lines we are setting layoutmanager and adapter to our recycler view.
                categoryRV.setLayoutManager(linearLayoutManager);
                categoryRV.setAdapter(dashBoardAdapter);

            }
        }
        GetScores getScores = new GetScores();
        getScores.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getScores();
    }
}