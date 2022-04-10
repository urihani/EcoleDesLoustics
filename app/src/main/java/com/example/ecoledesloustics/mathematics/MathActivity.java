package com.example.ecoledesloustics.mathematics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.end_game.LoseActivity;
import com.example.ecoledesloustics.end_game.WinActivity;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;

public class MathActivity extends AppCompatActivity {

    private UserModel userModel;
    private MathsCategoryModel mathCatModel;
    private MathGameModel game;
    private int currentQuestion = 1;
    private int numExercises = 0;
    TextView pageTV;
    ScoresTrackerModel scores;
    private DatabaseClient mDb;
    int secondsRemaining;
    TextView timeTV;
    TextView categoryTV;
    ProgressBar timePB;
    Button startBT;
    CountDownTimer timer;
    Bundle bundle;
    TextView operationTV;

    Button answer0, answer1, answer2, answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        getObjectsFromIntent();
        makeNewGame();
        makeBundle();
        manageViews();
        makeTimerVisible();
        clickOnPreviousButton();
        clickOnUserButton();
        disableAnswerButtons();
        clickOnStartButton();
        clickOnAnswersButtons();
    }

    private void clickOnAnswersButtons() {
        View.OnClickListener answerBTClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedBT = (Button) view;

                int selectedAnswer = Integer.parseInt(clickedBT.getText().toString());
                currentQuestion++;
                game.checkAnswer(selectedAnswer);

                if (game.checkEndGame()) {
                    if (game.checkWin()) {
                        updateScores();
                        stopTimer();
                        goToWinActivity();
                    } else {
                        disableAnswerButtons();
                        stopTimer();
                        resetExercise();
                        goToLoseActivity();
                    }
                } else {
                    nextTurn();
                }
            }
        };
        answer0.setOnClickListener(answerBTClickListener);
        answer1.setOnClickListener(answerBTClickListener);
        answer2.setOnClickListener(answerBTClickListener);
        answer3.setOnClickListener(answerBTClickListener);
    }

    private void clickOnStartButton() {
        View.OnClickListener startBTClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBT = (Button) view;
                makeTextViewsVisible();
                makeTimer();
                resetGameValues();
                if (mathCatModel.isTimed()) {
                    timer.start();
                }
                nextTurn();
            }
        };
        Button goBT = findViewById(R.id.idBTGo);
        goBT.setOnClickListener(startBTClickListener);
    }

    private void resetGameValues() {
        game = new MathGameModel(mathCatModel.getFirstUpperLimit(),
                mathCatModel.getSecondUpperLimit(), mathCatModel.getTotalQuestions(),
                mathCatModel.getCategory());
        currentQuestion = 1;
    }

    private void makeTimer() {
        secondsRemaining = mathCatModel.getSecondsRemaining();
        timer = new CountDownTimer((mathCatModel.getSecondsRemaining() * 1000), 1000) {
            @Override
            public void onTick(long l) {
                secondsRemaining--;
                timeTV.setText(Integer.toString(secondsRemaining) + "sec");
                timePB.setProgress(mathCatModel.getSecondsRemaining() - secondsRemaining);
            }

            @Override
            public void onFinish() {
                disableAnswerButtons();
                resetExercise();
                final Intent intent;
                intent = new Intent(MathActivity.this, LoseActivity.class);
                bundle.putInt("errors", game.getNumberIncorrect());
                bundle.putBoolean("timeOut", true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
    }

    private void makeTextViewsVisible() {
        startBT.setVisibility(View.INVISIBLE);
        operationTV.setVisibility(View.VISIBLE);
    }

    private void disableAnswerButtons() {
        answer0.setEnabled(false);
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
    }

    private void clickOnUserButton() {
        ImageButton userIB = findViewById(R.id.idIBUser);
        userIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                Intent intent = new Intent(view.getContext(), SettingsActivity.class);

                intent.putExtra("user", userModel);

                startActivity(intent);
            }
        });
    }

    private void clickOnPreviousButton() {
        ImageButton previousIB = findViewById(R.id.idIBPrevious);
        previousIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
                finish();
            }
        });
    }

    private void makeNewGame() {
        game = new MathGameModel(mathCatModel.getFirstUpperLimit(),
                mathCatModel.getSecondUpperLimit(), mathCatModel.getTotalQuestions(),
                mathCatModel.getCategory());
    }

    private void getObjectsFromIntent() {
        if (getIntent().getExtras() != null) {
            userModel = getIntent().getParcelableExtra("user");
            mathCatModel = getIntent().getParcelableExtra("cat");
        }
    }

    private void makeTimerVisible() {
        if (mathCatModel.isTimed()) {
            timeTV.setVisibility(View.VISIBLE);
            timePB.setVisibility(View.VISIBLE);
        }
    }

    private void manageViews() {
        operationTV = findViewById(R.id.idTVOperation);
        operationTV.setVisibility(View.INVISIBLE);
        pageTV = findViewById(R.id.idTVPage);
        timeTV = findViewById(R.id.idTVTime);
        timePB = findViewById(R.id.idPBTime);
        categoryTV = findViewById(R.id.idTVCategory);
        categoryTV.setText(mathCatModel.getCategory());
        answer0 = findViewById(R.id.idBTAnswer0);
        answer1 = findViewById(R.id.idBTAnswer1);
        answer2 = findViewById(R.id.idBTAnswer2);
        answer3 = findViewById(R.id.idBTAnswer3);
    }

    private void makeBundle() {
        bundle = new Bundle();
        bundle.putParcelable("user", userModel);
        bundle.putParcelable("math_cat", mathCatModel);
    }

    private void goToLoseActivity() {
        final Intent intent;
        intent = new Intent(MathActivity.this, LoseActivity.class);
        bundle.putInt("errors", game.getNumberIncorrect());
        bundle.putBoolean("timeOut", false);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToWinActivity() {
        final Intent intent;
        intent = new Intent(MathActivity.this, WinActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void resetExercise() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startBT.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    private void nextTurn() {
        pageTV.setText(Integer.toString(currentQuestion) + "/" + Integer.toString(mathCatModel.getTotalQuestions()));

        game.makeNewQuestion();
        int[] answers = game.getCurrentQuestion().getAnswerArray();

        answer0.setText(Integer.toString(answers[0]));
        answer1.setText(Integer.toString(answers[1]));
        answer2.setText(Integer.toString(answers[2]));
        answer3.setText(Integer.toString(answers[3]));

        answer0.setEnabled(true);
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);

        operationTV.setText(game.getCurrentQuestion().getQuestionPhrase());
    }

    private void updateScores() {
        class UpdateScores extends AsyncTask<Void, Void, ScoresTrackerModel> {

            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                ScoresTrackerModel updatedScores =
                        mDb.getAppDatabase().scoresDAO().getScoreFromUserId(userModel.getId());
                ArrayList<Long> updatedMathCompleted = new ArrayList<>(updatedScores.getMathCompleted());

                if (!updatedScores.getMathCompleted().contains(mathCatModel.getId())) {
                    updatedMathCompleted.add(mathCatModel.getId());
                    updatedScores.setMathCompleted(updatedMathCompleted);
                    updatedScores.computeMathScore();
                    mDb.getAppDatabase().scoresDAO().update(updatedScores);
                }

                return updatedScores;
            }

            @Override
            protected void onPostExecute(ScoresTrackerModel scoresModel) {
                super.onPostExecute(scoresModel);
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        UpdateScores update = new UpdateScores();
        update.execute();
    }
}
