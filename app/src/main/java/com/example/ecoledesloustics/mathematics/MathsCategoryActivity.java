package com.example.ecoledesloustics.mathematics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class MathsCategoryActivity extends AppCompatActivity {

    private RecyclerView multiplyCategoryRV;
    private RecyclerView timedMultiplyCategoryRV;
    private RecyclerView additionsCategoryRV;
    private RecyclerView timedAdditionsCategoryRV;
    private RecyclerView substractionsCategoryRV;
    private RecyclerView timedSubstractionsCategoryRV;
    private ArrayList<MathsCategoryModel> multiplyCategoryModel;
    private ArrayList<MathsCategoryModel> timedMultiplyCategoryModel;
    private ArrayList<MathsCategoryModel> additionsCategoryModel;
    private ArrayList<MathsCategoryModel> timedAdditionsCategoryModel;
    private ArrayList<MathsCategoryModel> substractionsCategoryModel;
    private ArrayList<MathsCategoryModel> timedSubstractionsCategoryModel;
    private DatabaseClient mDb;
    UserModel userModel;
    ScoresTrackerModel scoresModel;
    int numExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_category);

        Bundle bundle = getIntent().getExtras();
        userModel = bundle.getParcelable("user");
        scoresModel = bundle.getParcelable("scores");

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // ----------------------------------------------------------------------------------------
        // adding data to multiply activity
        multiplyCategoryRV = findViewById(R.id.idRVmultiplyCategory);
        multiplyCategoryModel = new ArrayList<>();
        multiplyCategoryModel.add(new MathsCategoryModel(0, R.drawable.ic_easy, 2, 2, 5, false, 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(1, R.drawable.ic_medium, 4, 4, 10, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(2, R.drawable.ic_medium, 5, 5, 10, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(3, R.drawable.ic_hard, 6, 6, 10, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(4, R.drawable.ic_hard, 7, 7, 15, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(5, R.drawable.ic_hard, 8, 8, 15, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(6, R.drawable.ic_hard, 9, 9, 15, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(7, R.drawable.ic_hard, 10, 10, 20, false
                , 0,
                "Multiplications"));
        multiplyCategoryModel.add(new MathsCategoryModel(8, R.drawable.ic_hard, 11, 11, 20, false
                , 0,
                "Multiplications"));

        // we are initializing our adapter class and passing our arraylist to it.
        MathsCategoryAdapter multiplyCategoryAdapter = new MathsCategoryAdapter(this,
                multiplyCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        multiplyCategoryRV.setLayoutManager(linearLayoutManager);
        multiplyCategoryRV.setAdapter(multiplyCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to timed multiply activity
        timedMultiplyCategoryRV = findViewById(R.id.idRVTimedMultiplyCategory);
        timedMultiplyCategoryModel = new ArrayList<>();
//        timedMultiplyCategoryModel.add(new MathsCategoryModel(7, R.drawable.ic_easy, 2, 2, 10,
//                true, 31,
//                "Multiplications chronométrées"));

        // we are initializing our adapter class and passing our arraylist to it.
        MathsCategoryAdapter timedMultiplyCategoryAdapter = new MathsCategoryAdapter(this,
                timedMultiplyCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager timedMultiplyLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        timedMultiplyCategoryRV.setLayoutManager(timedMultiplyLayoutManager);
        timedMultiplyCategoryRV.setAdapter(timedMultiplyCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to additions activity
        additionsCategoryRV = findViewById(R.id.idRVAdditionsCategory);
        additionsCategoryModel = new ArrayList<>();
//        additionsCategoryModel.add(new MathsCategoryModel(8, R.drawable.ic_easy, 2, 2, 10, false,
//                0,
//                "Additions"));
//        additionsCategoryModel.add(new MathsCategoryModel(9, R.drawable.ic_easy, 3, 3, 10, false,
//                0,
//                "Additions"));

        // we are initializing our adapter class and passing our arraylist to it.
        MathsCategoryAdapter additionsCategoryAdapter = new MathsCategoryAdapter(this,
                additionsCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager additionsLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        additionsCategoryRV.setLayoutManager(additionsLayoutManager);
        additionsCategoryRV.setAdapter(additionsCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to timed additions activity
        timedAdditionsCategoryRV = findViewById(R.id.idRVTimedAdditionsCategory);
        timedAdditionsCategoryModel = new ArrayList<>();
//        timedAdditionsCategoryModel.add(new MathsCategoryModel(10, R.drawable.ic_easy, 2, 2, 10,
//                true, 31,
//                "Additions chronométrées"));
//        timedAdditionsCategoryModel.add(new MathsCategoryModel(11, R.drawable.ic_easy, 3, 3, 10,
//                true, 31,
//                "Additions chronométrées"));

        // we are initializing our adapter class and passing our arraylist to it.
        MathsCategoryAdapter timedAdditionsCategoryAdapter = new MathsCategoryAdapter(this,
                timedAdditionsCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager timedAdditionsLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        timedAdditionsCategoryRV.setLayoutManager(timedAdditionsLayoutManager);
        timedAdditionsCategoryRV.setAdapter(timedAdditionsCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to substractions activity
        substractionsCategoryRV = findViewById(R.id.idRVSubstractionsCategory);
        substractionsCategoryModel = new ArrayList<>();
//        substractionsCategoryModel.add(new MathsCategoryModel(12, R.drawable.ic_easy, 2, 2, 10,
//                false, 0,
//                "Soustractions"));
//        substractionsCategoryModel.add(new MathsCategoryModel(13, R.drawable.ic_easy, 3, 3, 10,
//                false, 0,
//                "Soustractions"));

        // we are initializing our adapter class and passing our arraylist to it.
        MathsCategoryAdapter substractionsCategoryAdapter = new MathsCategoryAdapter(this,
                substractionsCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager substractionsLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        substractionsCategoryRV.setLayoutManager(substractionsLayoutManager);
        substractionsCategoryRV.setAdapter(substractionsCategoryAdapter);

        // ----------------------------------------------------------------------------------------
        // adding data to timed substractions activity
        timedSubstractionsCategoryRV = findViewById(R.id.idRVTimedSubstractionsCategory);
        timedSubstractionsCategoryModel = new ArrayList<>();
//        timedSubstractionsCategoryModel.add(new MathsCategoryModel(14, R.drawable.ic_easy, 2, 2, 10,
//                true, 31,
//                "Soustractions chronométrées"));
//        timedSubstractionsCategoryModel.add(new MathsCategoryModel(15, R.drawable.ic_easy, 3, 3, 10,
//                true, 31,
//                "Soustractions chronométrées"));

        // we are initializing our adapter class and passing our arraylist to it.
        MathsCategoryAdapter timedSubstractionsCategoryAdapter = new MathsCategoryAdapter(this,
                timedSubstractionsCategoryModel, userModel, scoresModel);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager timedSubstractionsLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        timedSubstractionsCategoryRV.setLayoutManager(timedSubstractionsLayoutManager);
        timedSubstractionsCategoryRV.setAdapter(timedSubstractionsCategoryAdapter);

        // count mathematics activities to help compute the value of the progress bar
        numExercises =
                multiplyCategoryModel.size() +
                        timedMultiplyCategoryModel.size() +
                        additionsCategoryModel.size() +
                        timedAdditionsCategoryModel.size() +
                        substractionsCategoryModel.size() +
                        timedSubstractionsCategoryModel.size();
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

    private void updateScore() {
        class UpdateScore extends AsyncTask<Void, Void, ScoresTrackerModel> {

            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                ScoresTrackerModel updatedScore =
                        mDb.getAppDatabase().scoresDAO().getScoreFromUserId(userModel.getId());

                updatedScore.setTotalOfMathExercises(numExercises);

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