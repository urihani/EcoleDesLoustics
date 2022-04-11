package com.example.ecoledesloustics.quiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class QuizCategoryActivity extends AppCompatActivity {
    private RecyclerView classicCategoryRV;
    private RecyclerView timedCategoryRV;
    private ArrayList<QuizCategoryModel> classicCategoryModel;
    private ArrayList<QuizCategoryModel> timedCategoryModel;
    private DatabaseClient mDb;
    UserModel userModel;
    ScoresTrackerModel scoresModel;
    int numExercises;
    String typeOfQuiz;
    TextView quizTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_category);

        Bundle bundle = getIntent().getExtras();
        userModel = bundle.getParcelable("user");
        typeOfQuiz = bundle.getString("typeOfQuiz");
        scoresModel = bundle.getParcelable("scores");

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        quizTV = findViewById(R.id.idTVQuiz);
        if (typeOfQuiz.equals("Culture générale")){
            quizTV.setText(typeOfQuiz);
            setAdapterForCulture();
        } else if (typeOfQuiz.equals("Géographie")){
            quizTV.setText(typeOfQuiz);
            setAdapterForGeography();
        }

        // count activities to help compute the value of the progress bar
        numExercises =
                classicCategoryModel.size() +
                        timedCategoryModel.size();
        updateScore();
        // get num of activities from previous view
        // if null => update table with num of exercises

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

    private void setAdapterForGeography() {
        // ----------------------------------------------------------------------------------------
        // adding data to classic culture activity
        classicCategoryRV = findViewById(R.id.idRVclassicCategory);
        classicCategoryModel = new ArrayList<>();
        classicCategoryModel.add(new QuizCategoryModel(11, "Capitales 1",
                R.drawable.ic_globe, 5,
                false, 0,
                "Geographie"));
        classicCategoryModel.add(new QuizCategoryModel(12, "Capitales 2",
                R.drawable.ic_geography, 5,
                false, 0,
                "Geographie"));
        classicCategoryModel.add(new QuizCategoryModel(13, "Capitales 3",
                R.drawable.ic_geography2, 5,
                false, 0,
                "Geographie"));

        // we are initializing our adapter class and passing our arraylist to it.
        QuizCategoryAdapter classicQuizCategoryAdapter = new QuizCategoryAdapter(this,
                classicCategoryModel,
                userModel, typeOfQuiz, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager cultureLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        classicCategoryRV.setLayoutManager(cultureLayoutManager);
        classicCategoryRV.setAdapter(classicQuizCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to timed activity
        timedCategoryRV = findViewById(R.id.idRVTimedCategory);
        timedCategoryModel = new ArrayList<>();
        timedCategoryModel.add(new QuizCategoryModel(14, "Capitales 1", R.drawable.ic_globe, 5,
                true, 46,
                "Geographie chronométrée"));
        timedCategoryModel.add(new QuizCategoryModel(15, "Capitales 2",
                R.drawable.ic_geography, 5,
                true, 46,
                "Geographie chronométrée"));
        timedCategoryModel.add(new QuizCategoryModel(16, "Capitales 3",
                R.drawable.ic_geography2, 5,
                true, 46,
                "Geographie chronométrée"));

        // we are initializing our adapter class and passing our arraylist to it.
        QuizCategoryAdapter timedQuizCategoryAdapter = new QuizCategoryAdapter(this,
                timedCategoryModel, userModel, typeOfQuiz, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager timedCultureLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        timedCategoryRV.setLayoutManager(timedCultureLayoutManager);
        timedCategoryRV.setAdapter(timedQuizCategoryAdapter);
    }

    private void setAdapterForCulture() {
        // ----------------------------------------------------------------------------------------
        // adding data to classic culture activity
        classicCategoryRV = findViewById(R.id.idRVclassicCategory);
        classicCategoryModel = new ArrayList<>();
        classicCategoryModel.add(new QuizCategoryModel(1, "Ecologie", R.drawable.ic_ecology, 5,
                false, 0,
                "Culture"));
        classicCategoryModel.add(new QuizCategoryModel(2, "Mythologie", R.drawable.ic_mythology, 5,
                false, 0,
                "Culture"));
        classicCategoryModel.add(new QuizCategoryModel(3, "Prehistoire", R.drawable.ic_caveman, 5,
                false, 0,
                "Culture"));
        classicCategoryModel.add(new QuizCategoryModel(4, "Stars", R.drawable.ic_celebrity, 5,
                false, 0,
                "Culture"));
        classicCategoryModel.add(new QuizCategoryModel(5, "Heros", R.drawable.ic_superhero, 5,
                false, 0,
                "Culture"));

        // we are initializing our adapter class and passing our arraylist to it.
        QuizCategoryAdapter classicQuizCategoryAdapter = new QuizCategoryAdapter(this,
                classicCategoryModel,
                userModel, typeOfQuiz, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager cultureLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        classicCategoryRV.setLayoutManager(cultureLayoutManager);
        classicCategoryRV.setAdapter(classicQuizCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to timed activity
        timedCategoryRV = findViewById(R.id.idRVTimedCategory);
        timedCategoryModel = new ArrayList<>();
        timedCategoryModel.add(new QuizCategoryModel(6, "Ecologie", R.drawable.ic_ecology, 5,
                true, 46,
                "Culture chronométrée"));
        timedCategoryModel.add(new QuizCategoryModel(7, "Mythologie", R.drawable.ic_mythology, 5,
                true, 46,
                "Culture chronométrée"));
        timedCategoryModel.add(new QuizCategoryModel(8, "Prehistoire", R.drawable.ic_caveman, 5,
                true, 46,
                "Culture chronométrée"));
        timedCategoryModel.add(new QuizCategoryModel(9, "Stars", R.drawable.ic_celebrity, 5,
                true, 46,
                "Culture chronométrée"));
        timedCategoryModel.add(new QuizCategoryModel(10, "Heros", R.drawable.ic_superhero, 5,
                true, 46,
                "Culture chronométrée"));

        // we are initializing our adapter class and passing our arraylist to it.
        QuizCategoryAdapter timedQuizCategoryAdapter = new QuizCategoryAdapter(this,
                timedCategoryModel, userModel, typeOfQuiz, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager timedCultureLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        timedCategoryRV.setLayoutManager(timedCultureLayoutManager);
        timedCategoryRV.setAdapter(timedQuizCategoryAdapter);
    }

    private void updateScore() {
        class UpdateScore extends AsyncTask<Void, Void, ScoresTrackerModel> {

            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                ScoresTrackerModel updatedScore =
                        mDb.getAppDatabase().scoresDAO().getScoreFromUserId(userModel.getId());

                if (typeOfQuiz.equals("Culture générale")){
                    updatedScore.setTotalOfCultureExercises(numExercises);
                }else if (typeOfQuiz.equals("Géographie")){
                    updatedScore.setTotalOfGeographyExercises(numExercises);
                }

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