package com.example.ecoledesloustics.end_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.dashboard.DashBoardActivity;
import com.example.ecoledesloustics.mathematics.MathsCategoryModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

public class LoseActivity extends AppCompatActivity {

    private UserModel userModel;
    private MathsCategoryModel mathCatModel;
    private int errors;
    private boolean timeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        // get user object from activity
        if (getIntent().getExtras() != null) {
            userModel = getIntent().getParcelableExtra("user");
            mathCatModel = getIntent().getParcelableExtra("math_cat");
            errors = getIntent().getIntExtra("errors", 0);
            timeOut = getIntent().getBooleanExtra("timeOut", true);
        }

        // display message when time is out
        TextView loseTV = findViewById(R.id.idTVLose);
        if (timeOut){
            loseTV.setText("Temps écoulé");
        }

        // updating the scores
        TextView errorsTV = findViewById(R.id.idTVErrors);
        errorsTV.setText(Integer.toString(errors) + " erreurs");

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

        // home button
        Button homeBT = findViewById(R.id.idBTHome);
        homeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DashBoardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user", userModel);
                view.getContext().startActivity(intent);
            }
        });

        Button retryBT = findViewById(R.id.idBTRetry);
        retryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}