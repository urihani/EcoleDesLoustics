package com.example.ecoledesloustics.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoledesloustics.MainActivity;
import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.avatar.AvatarActivity;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.users_data.UserModel;

public class SettingsActivity extends AppCompatActivity {
    private static final int REQUEST_GET_AVATAR_LOCATION = 1;
    private DatabaseClient mDb;
    private int image;
    private String firstName, lastName;
    ScoresTrackerModel scores;

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // get user object from activity
        if (getIntent().getExtras() != null){
            userModel = getIntent().getParcelableExtra("user");
        }

        firstName = userModel.getFirstName();

        // display first name
        TextView firstNameTV = findViewById(R.id.idTVFirstName);
        firstNameTV.setText(userModel.getFirstName() + " " + userModel.getLastName());

        // previous button action
        ImageButton previousIB = findViewById(R.id.idIBPrevious);
        previousIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // display profile picture
        ImageView profilePictureIV = findViewById(R.id.idIVProfileImage);
        profilePictureIV.setImageResource(userModel.getImg());

        // Disconnect
        Button signOutBT = findViewById(R.id.idBTSignOut);
        signOutBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
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

        // delete button (disabled if user is anonymous)
        Button deleteBT = findViewById(R.id.idBTDelete);
        if (firstName.equals("Anonyme")){
            deleteBT.setEnabled(false);
            cameraIB.setEnabled(false);
        } else{
            deleteBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlert();
                }
            });
        }
    }

    private void showAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Suppression");
        alert.setMessage("Es-tu sûr de vouloir supprimer ton compte ?");
        alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteUser();
            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SettingsActivity.this, "Annulé", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }

    private void deleteUser() {
        class DeleteUser extends AsyncTask<Void, Void, UserModel> {

            @Override
            protected UserModel doInBackground(Void... voids) {
                UserModel updatedUser =
                        mDb.getAppDatabase().userDao().getUserFromName(userModel.getFirstName(),
                                userModel.getLastName());

                mDb.getAppDatabase().userDao().delete(updatedUser);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                return updatedUser;
            }

            @Override
            protected void onPostExecute(UserModel userModel) {
                super.onPostExecute(userModel);

                Toast.makeText(SettingsActivity.this, "Compte supprimé", Toast.LENGTH_SHORT).show();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        DeleteUser delete = new DeleteUser();
        delete.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GET_AVATAR_LOCATION && resultCode == Activity.RESULT_OK) {
            image = data.getIntExtra("image", 0);
            ImageView profileImageIV = findViewById(R.id.idIVProfileImage);
            profileImageIV.setImageResource(image);

            updateUser();
        }
    }

    private void updateUser() {
        class UpdateUser extends AsyncTask<Void, Void, UserModel> {

            @Override
            protected UserModel doInBackground(Void... voids) {
                UserModel updatedUser =
                        mDb.getAppDatabase().userDao().getUserFromName(userModel.getFirstName(),
                                userModel.getLastName());

                updatedUser.setImg(image);

                mDb.getAppDatabase().userDao().update(updatedUser);
                return updatedUser;
            }

            @Override
            protected void onPostExecute(UserModel userModel) {
                super.onPostExecute(userModel);
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        UpdateUser update = new UpdateUser();
        update.execute();
    }

    private void getScores() {
        class GetScores extends AsyncTask<Void, Void, ScoresTrackerModel> {
            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                scores = mDb.getAppDatabase()
                        .scoresDAO()
                        .getScoreFromUserId(userModel.getId());

                return scores;
            }

            @Override
            protected void onPostExecute(ScoresTrackerModel scores) {
                super.onPostExecute(scores);
                // set progress bars
                ProgressBar mathsPB = findViewById(R.id.idPBMathsProgress);
                mathsPB.setProgress(scores.getMathProgress());
                ProgressBar culturePB = findViewById(R.id.idPBCultureProgress);
                culturePB.setProgress(scores.getCultureProgress());
                ProgressBar geoPB = findViewById(R.id.idPBGeographyProgress);
                geoPB.setProgress(scores.getGeographyProgress());
                ProgressBar logicPB = findViewById(R.id.idPBLogicProgress);
                logicPB.setProgress(scores.getGamesProgress());

                // set percentages
                TextView mathsTV = findViewById(R.id.idTVMathsPercentage);
                mathsTV.setText(Integer.toString(scores.getMathProgress()) + "%");
                TextView cultureTV = findViewById(R.id.idTVCulturePercentage);
                cultureTV.setText(Integer.toString(scores.getCultureProgress()) + "%");
                TextView geoTV = findViewById(R.id.idTVGeographyPercentage);
                geoTV.setText(Integer.toString(scores.getGeographyProgress()) + "%");
                TextView logicTV = findViewById(R.id.idTVLogicPercentage);
                logicTV.setText(Integer.toString(scores.getGamesProgress()) + "%");
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