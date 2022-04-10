package com.example.ecoledesloustics.quiz;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.end_game.LoseActivity;
import com.example.ecoledesloustics.end_game.WinActivity;
import com.example.ecoledesloustics.exercises_data.ExerciseDataModel;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private UserModel userModel;
    private QuizCategoryModel quizCategoryModel;
    private DatabaseClient mDb;
    private QuizGameModel game;
    private HashMap<String, List<String>> questions;
    private int currentQuestion = 1;
    private int numExercises = 0;
    TextView pageTV;
    TextView answersTV;
    ScoresTrackerModel scores;
    int secondsRemaining;
    TextView timeTV;
    TextView categoryTV;
    TextView equalTV;
    ProgressBar timePB;
    CountDownTimer timer;
    Button startBT;
    Bundle bundle;
    View.OnClickListener answerBTClickListener;
    TextView operationTV;
    private String typeOfQuiz;

    Button answer0, answer1, answer2, answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());
    }

    private void executeActivity() {
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
        answerBTClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedBT = (Button) view;
                String selectedAnswer = clickedBT.getText().toString();
                currentQuestion++;
                game.checkAnswer(selectedAnswer);

                if (game.checkEndGame()) {
                    if (game.checkWin()) {
                        updateScores();
                        stopTimer();
                        goToWinActivity();
                    } else {
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
                startBT.setVisibility(View.INVISIBLE);
                makeTextViewsVisible();
                makeTimer();
                resetGameValues();
                if (quizCategoryModel.isTimed()) {
                    timer.start();
                }
                nextTurn();
            }
        };
        Button goBT = findViewById(R.id.idBTGo);
        goBT.setOnClickListener(startBTClickListener);
    }

    private void clickOnUserButton() {
        ImageButton userIB = findViewById(R.id.idIBUser);
        userIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettingsActivity(view);
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
        game = new QuizGameModel(questions, quizCategoryModel.getTotalQuestions(),
                quizCategoryModel.getCategory());
    }

    private void makeTextViewsVisible() {
        operationTV.setVisibility(View.VISIBLE);
        answersTV.setVisibility(View.VISIBLE);
    }

    private void resetGameValues() {
        game = new QuizGameModel(questions, quizCategoryModel.getTotalQuestions(),
                quizCategoryModel.getCategory());
        currentQuestion = 1;
    }

    private void makeTimer() {
        secondsRemaining = quizCategoryModel.getSecondsRemaining();
        timer = new CountDownTimer((quizCategoryModel.getSecondsRemaining() * 1000), 1000) {
            @Override
            public void onTick(long l) {
                secondsRemaining--;
                timeTV.setText(Integer.toString(secondsRemaining) + "sec");
                timePB.setMax(quizCategoryModel.getSecondsRemaining());
                timePB.setProgress(quizCategoryModel.getSecondsRemaining() - secondsRemaining);
            }

            @Override
            public void onFinish() {
                disableAnswerButtons();
                resetExercise();
                final Intent intent;
                intent = new Intent(QuizActivity.this, LoseActivity.class);
                bundle.putInt("errors", game.getNumberIncorrect());
                bundle.putBoolean("timeOut", true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
    }

    private void disableAnswerButtons() {
        answer0.setEnabled(false);
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
    }

    private void goToSettingsActivity(View view) {
        stopTimer();
        Intent intent = new Intent(view.getContext(), SettingsActivity.class);
        intent.putExtra("user", userModel);
        startActivity(intent);
    }

    private void makeTimerVisible() {
        if (quizCategoryModel.isTimed()) {
            timeTV.setVisibility(View.VISIBLE);
            timeTV.setText(Integer.toString(secondsRemaining) + "sec");
            timePB.setVisibility(View.VISIBLE);
        }
    }

    private void makeBundle() {
        bundle = new Bundle();
        bundle.putParcelable("user", userModel);
        bundle.putParcelable("quiz_cat", quizCategoryModel);
    }

    private void manageViews() {
        equalTV = findViewById(R.id.idTVEqual);
        equalTV.setVisibility(View.INVISIBLE);
        pageTV = findViewById(R.id.idTVPage);
        timeTV = findViewById(R.id.idTVTime);
        timePB = findViewById(R.id.idPBTime);
        categoryTV = findViewById(R.id.idTVCategory);
        answersTV = findViewById(R.id.idTVAnswers);
        answersTV.setVisibility(View.INVISIBLE);
        operationTV = findViewById(R.id.idTVOperation);
        operationTV.setVisibility(View.INVISIBLE);
        categoryTV.setText(quizCategoryModel.getTitle());
        answer0 = findViewById(R.id.idBTAnswer0);
        answer1 = findViewById(R.id.idBTAnswer1);
        answer2 = findViewById(R.id.idBTAnswer2);
        answer3 = findViewById(R.id.idBTAnswer3);
    }

    private void goToLoseActivity() {
        final Intent intent;
        intent = new Intent(QuizActivity.this, LoseActivity.class);
        bundle.putInt("errors", game.getNumberIncorrect());
        bundle.putBoolean("timeOut", false);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToWinActivity() {
        final Intent intent;
        intent = new Intent(QuizActivity.this, WinActivity.class);
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
        pageTV.setText(Integer.toString(currentQuestion) + "/" + Integer.toString(quizCategoryModel.getTotalQuestions()));

        game.makeNewQuestion();
        String[] answers = game.getCurrentQuestion().getAnswerArray();
        String[] letters = game.getCurrentQuestion().getLettersArray();

        answersTV.setText(
                answers[0] +
                        "\n" +
                        answers[1] +
                        "\n" +
                        answers[2] +
                        "\n" +
                        answers[3]);

        answer0.setText(letters[0]);
        answer1.setText(letters[1]);
        answer2.setText(letters[2]);
        answer3.setText(letters[3]);

        answer0.setEnabled(true);
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);


        operationTV.setText(game.getCurrentQuestion().getQuestion());
    }

    private void updateScores() {
        class UpdateScores extends AsyncTask<Void, Void, ScoresTrackerModel> {

            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                ScoresTrackerModel updatedScores =
                        mDb.getAppDatabase().scoresDAO().getScoreFromUserId(userModel.getId());
                ArrayList<Long> updatedCultureCompleted =
                        new ArrayList<>(updatedScores.getCultureCompleted());
                ArrayList<Long> updatedGeographyCompleted =
                        new ArrayList<>(updatedScores.getGeographyCompleted());

                if (!updatedScores.getCultureCompleted().contains(quizCategoryModel.getId()) && typeOfQuiz.equals("Culture générale")) {
                    updatedCultureCompleted.add(quizCategoryModel.getId());
                    updatedScores.setCultureCompleted(updatedCultureCompleted);
                    updatedScores.computeCultureScore();
                    mDb.getAppDatabase().scoresDAO().update(updatedScores);
                } else if (!updatedScores.getGeographyCompleted().contains(quizCategoryModel.getId()) && typeOfQuiz.equals("Géographie")){
                    updatedGeographyCompleted.add(quizCategoryModel.getId());
                    updatedScores.setGeographyCompleted(updatedGeographyCompleted);
                    updatedScores.computeGeographyScore();
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

    private void getExercise() {
        class GetExercise extends AsyncTask<Void, Void, ExerciseDataModel> {
            @Override
            protected ExerciseDataModel doInBackground(Void... voids) {
                ExerciseDataModel exercisesData = mDb.getAppDatabase()
                        .exerciseDAO()
                        .getExerciseFromId(1);
                return exercisesData;
            }

            @Override
            protected void onPostExecute(ExerciseDataModel exercisesData) {
                super.onPostExecute(exercisesData);
                getObjectsFromIntent();
                if (quizCategoryModel.getTitle().equals("Ecologie")) {
                    questions = new HashMap<>(exercisesData.getCultureEcologyQuestions());
                    executeActivity();
                } else if (quizCategoryModel.getTitle().equals("France")){
                    questions = new HashMap<>(exercisesData.getGeography1Questions());
                    executeActivity();
                }
            }
        }
        GetExercise getExercise = new GetExercise();
        getExercise.execute();
    }

    private void getObjectsFromIntent() {
        if (getIntent().getExtras() != null) {
            userModel = getIntent().getParcelableExtra("user");
            quizCategoryModel = getIntent().getParcelableExtra("cat");
            typeOfQuiz = getIntent().getStringExtra("typeOfQuiz");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getExercise();
    }
}