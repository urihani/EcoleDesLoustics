package com.example.ecoledesloustics.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.avatar.AvatarActivity;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.users_data.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
    private static final int REQUEST_GET_AVATAR_LOCATION = 0;

    // DATA
    private DatabaseClient mDb;
    private int image;

    private EditText firstNameET, lastNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        firstNameET = findViewById(R.id.idETFirstName);
        lastNameET = findViewById(R.id.idETLastName);

        // previous button
        ImageButton previousBT = findViewById(R.id.idIBPrevious);
        previousBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // validate button
        Button registerBt = findViewById(R.id.idBTRegister);
        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });

        // avatar button
        ImageButton cameraIB = findViewById(R.id.idIBcamera);
        cameraIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AvatarActivity.class);

                startActivityForResult(intent, REQUEST_GET_AVATAR_LOCATION);
            }
        });
    }

    private void saveUser() {
        // Récupérer les informations contenues dans les vues
        final String firstName = firstNameET.getText().toString().trim();
        final String firstNameCap;
        if (firstName.length() > 0){
            firstNameCap = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        } else {
            firstNameCap = new String();
        }

        final String lastName = lastNameET.getText().toString().trim();
        final String lastNameCap;
        if (lastName.length() > 0){
            lastNameCap = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        } else {
            lastNameCap = new String();
        }

        // Vérifier les informations fournies par l'utilisateur
        if (firstNameCap.isEmpty()) {
            firstNameET.setError("Prénom obligatoire");
            firstNameET.requestFocus();
            return;
        }

        if (lastNameCap.isEmpty()) {
            lastNameET.setError("Nom obligatoire");
            lastNameET.requestFocus();
            return;
        }

        /**
         * Création d'une classe asynchrone pour sauvegarder l'utilisateur'
         */
        class SaveUser extends AsyncTask<Void, Void, UserModel> {

            @Override
            protected UserModel doInBackground(Void... voids) {
                if (mDb.getAppDatabase().userDao().getUserFromName(firstNameCap, lastNameCap) == null) {
                    if (image == 0){
                        return null;
                    } else {
                        // creating a user
                        UserModel userModel = new UserModel(image, firstNameCap, lastNameCap);

                        // adding to database
                        long id = mDb.getAppDatabase()
                                .userDao()
                                .insert(userModel);

                        // mettre à jour l'id de la tache
                        // Nécessaire si on souhaite avoir accès à l'id plus tard dans l'activité
                        userModel.setId(id);
                        return userModel;
                    }
                } else {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(UserModel userModel) {
                super.onPostExecute(userModel);

                if (userModel == null && image != 0) {
                    Toast.makeText(getApplicationContext(), "Déjà enregistré", Toast.LENGTH_LONG).show();
                } else if (userModel == null && image == 0) {
                    Toast.makeText(getApplicationContext(), "Veuillez choisir un avatar",
                            Toast.LENGTH_LONG).show();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        SaveUser su = new SaveUser();
        su.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GET_AVATAR_LOCATION && resultCode == Activity.RESULT_OK) {
            image = data.getIntExtra("image", 0);
            ImageView profileImageIV = findViewById(R.id.idIVProfileImage);
            profileImageIV.setImageResource(image);
        }
    }
}