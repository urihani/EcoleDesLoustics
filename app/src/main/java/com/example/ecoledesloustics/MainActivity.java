package com.example.ecoledesloustics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.exercises_data.ExerciseDataModel;
import com.example.ecoledesloustics.registration.RegistrationActivity;
import com.example.ecoledesloustics.users_data.UserAdapter;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseClient mDb;

    private RecyclerView userRV;
    private UserAdapter userAdapter;

    // Arraylist for storing data
    private ArrayList<UserModel> userModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        setContentView(R.layout.activity_main);
        userRV = findViewById(R.id.idRVUser);

        // we are initializing our adapter class and passing our arraylist to it.
        userAdapter = new UserAdapter(this, userModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        userRV.setLayoutManager(linearLayoutManager);
        userRV.setAdapter(userAdapter);

        // route to settings activity
        Button registerBT = findViewById(R.id.idBTRegister);
        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);

                startActivity(intent);
            }
        });
    }

    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<UserModel>> {
            @Override
            protected List<UserModel> doInBackground(Void... voids) {
                List<UserModel> userList = mDb.getAppDatabase()
                        .userDao()
                        .getAll();

                // if the list of users is empty we pre-populate the data
                if (userList.isEmpty()){
                    mDb.getAppDatabase().userDao().insertAll(
                            new UserModel(R.drawable.ic_anonymity,
                                    "Anonyme", ""),
                            new UserModel(R.drawable.ic_fille, "Fleurette", "Poisson"),
                            new UserModel(R.drawable.ic_garcon, "Théodore", "Mailhot"),
                            new UserModel(R.drawable.ic_fille_2, "Virginie", "Laux"),
                            new UserModel(R.drawable.ic_garcon_2, "Eliot", "Achin")
                    );

                    userList = mDb.getAppDatabase()
                            .userDao()
                            .getAll();
                }
                return userList;
            }
            @Override
            protected void onPostExecute(List<UserModel> userModels) {
                super.onPostExecute(userModels);

                userAdapter.swap(userModels);
            }
        }
        GetUsers getUsers = new GetUsers();
        getUsers.execute();
    }

    private void setExercises() {
        class SetExercises extends AsyncTask<Void, Void, ExerciseDataModel> {
            @Override
            protected ExerciseDataModel doInBackground(Void... voids) {
                ExerciseDataModel exercisesData = mDb.getAppDatabase()
                        .exerciseDAO()
                        .getExerciseFromId(1);

                // if the list of users is empty we pre-populate the data
                if (exercisesData == null){
                    mDb.getAppDatabase().exerciseDAO().insert(new ExerciseDataModel());

                    exercisesData = mDb.getAppDatabase()
                            .exerciseDAO().getExerciseFromId(1);
                }
                return exercisesData;
            }
            @Override
            protected void onPostExecute(ExerciseDataModel exercisesData) {
                super.onPostExecute(exercisesData);
            }
        }
        SetExercises setExercises = new SetExercises();
        setExercises.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getUsers();
        setExercises();
    }
}