package com.example.ecoledesloustics.end_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.games.GamesCategoryActivity;
import com.example.ecoledesloustics.games.GamesCategoryModel;
import com.example.ecoledesloustics.quiz.QuizCategoryActivity;
import com.example.ecoledesloustics.quiz.QuizCategoryModel;
import com.example.ecoledesloustics.dashboard.DashBoardActivity;
import com.example.ecoledesloustics.mathematics.MathsCategoryActivity;
import com.example.ecoledesloustics.mathematics.MathsCategoryModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

public class WinActivity extends AppCompatActivity {

    private UserModel userModel;
    private MathsCategoryModel mathCat;
    private QuizCategoryModel quizCategoryModel;
    private GamesCategoryModel gameCategoryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        // get user object from activity
        if (getIntent().getExtras() != null) {
            userModel = getIntent().getParcelableExtra("user");
            mathCat = getIntent().getParcelableExtra("math_cat");
            quizCategoryModel = getIntent().getParcelableExtra("quiz_cat");
            gameCategoryModel = getIntent().getParcelableExtra("game_cat");
        }

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

        Button choiceBT = findViewById(R.id.idBTChoice);
        choiceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                final Intent intent;
                bundle.putParcelable("user", userModel);
                if (!(mathCat == null) &&
                        (mathCat.getCategory().equals("Multiplications") ||
                                mathCat.getCategory().equals("Multiplications chronométrées") ||
                                mathCat.getCategory().equals("Additions") ||
                                mathCat.getCategory().equals("Additions chronométrées") ||
                                mathCat.getCategory().equals("Soustractions") ||
                                mathCat.getCategory().equals("Soustractions chronométrées"))) {
                    intent =
                            new Intent(view.getContext(), MathsCategoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (!(gameCategoryModel == null) &&
                        (gameCategoryModel.getCategory().equals("Memory") ||
                                gameCategoryModel.getCategory().equals("Memory timed"))){
                    intent =
                            new Intent(view.getContext(), GamesCategoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    bundle.putParcelable("game_cat", gameCategoryModel);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (!(quizCategoryModel == null) &&
                        (quizCategoryModel.getCategory().equals("Culture") ||
                                quizCategoryModel.getCategory().equals("Culture chronométrée"))) {
                    bundle.putString("typeOfQuiz", "Culture générale");
                    intent =
                            new Intent(view.getContext(), QuizCategoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (!(quizCategoryModel == null) && quizCategoryModel.getCategory().equals("Geographie") ||
                        quizCategoryModel.getCategory().equals("Geographie chronométrée")) {
                    bundle.putString("typeOfQuiz", "Géographie");
                    intent =
                            new Intent(view.getContext(), QuizCategoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}