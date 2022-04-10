package com.example.ecoledesloustics.games;

import static com.example.ecoledesloustics.R.*;
import static com.example.ecoledesloustics.R.drawable.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.db.DatabaseClient;
import com.example.ecoledesloustics.end_game.LoseActivity;
import com.example.ecoledesloustics.end_game.WinActivity;
import com.example.ecoledesloustics.quiz.QuizActivity;
import com.example.ecoledesloustics.scores.ScoresTrackerModel;
import com.example.ecoledesloustics.settings.SettingsActivity;
import com.example.ecoledesloustics.users_data.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MemoryActivity extends AppCompatActivity {
    private UserModel userModel;
    private GamesCategoryModel gamesCatModel;
    private MemoryGameModel game;
    ScoresTrackerModel scores;
    private DatabaseClient mDb;
    int secondsRemaining;
    TextView timeTV;
    TextView categoryTV;
    ProgressBar timePB;
    Button startBT;
    CountDownTimer timer;
    Bundle bundle;
    int clicked = 0;
    boolean turnIsOver = false;
    ImageButton firstCard;
    ImageButton secondCard;
    List<ImageButton> disabledCards;

    ImageButton answer0, answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11;
    ImageButton[] cards;
    Integer[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_memory);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        disabledCards = new ArrayList<>();

        getObjectsFromIntent();
        makeNewGame();
        makeBundle();
        manageViews();
        manageButtonViews();
        makeCardsArray();
        disableAllCards();
        clickOnStartButton();
        makeTimerVisible();
        clickOnPreviousButton();
        clickOnUserButton();
        images = game.getImages();


        for (int i = 0; i < cards.length; i++) {
            final int finalI = i;
            cards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clicked == 0 && !turnIsOver) {
                        setFirstImage(finalI);
                    } else if (clicked == 1 && !turnIsOver) {
                        setSecondImage(finalI);
                    }

                    if (clicked == 2 && turnIsOver) {
                        if (game.isCorrect()) {
                            manageCorrectAnswer();
                            if (game.checkWin()) {
                                updateScores();
                            }
                        } else {
                            manageWrongAnswer();
                            flipCards();
                        }
                    }
                }
            });
        }
    }

    private void manageViews() {
        timeTV = findViewById(R.id.idTVTime);
        timePB = findViewById(R.id.idPBTime);
        categoryTV = findViewById(R.id.idTVCategory);
        categoryTV.setText(gamesCatModel.getTitle());
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

    private void goToSettingsActivity(View view) {
        stopTimer();
        Intent intent = new Intent(view.getContext(), SettingsActivity.class);
        intent.putExtra("user", userModel);
        startActivity(intent);
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

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onBackPressed() {
        stopTimer();
        finish();
    }

    private void makeTimerVisible() {
        if (gamesCatModel.isTimed()) {
            timeTV.setVisibility(View.VISIBLE);
            timeTV.setText(Integer.toString(secondsRemaining) + "sec");
            timePB.setVisibility(View.VISIBLE);
        }
    }

    private void goToWinActivity() {
        final Intent intent;
        intent = new Intent(MemoryActivity.this, WinActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void clickOnStartButton() {
        View.OnClickListener startBTClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBT = (Button) view;
                startBT.setVisibility(View.INVISIBLE);
                makeTimer();
                makeNewGame();
                if (gamesCatModel.isTimed()) {
                    timer.start();
                }
                enableAllCards();
            }
        };
        Button goBT = findViewById(R.id.idBTGo);
        goBT.setOnClickListener(startBTClickListener);
    }

    private void makeTimer() {
        secondsRemaining = gamesCatModel.getSecondsRemaining();
        timer = new CountDownTimer((gamesCatModel.getSecondsRemaining() * 1000), 1000) {
            @Override
            public void onTick(long l) {
                secondsRemaining--;
                timeTV.setText(Integer.toString(secondsRemaining) + "sec");
                timePB.setMax(gamesCatModel.getSecondsRemaining());
                timePB.setProgress(gamesCatModel.getSecondsRemaining() - secondsRemaining);
            }

            @Override
            public void onFinish() {
                disableAllCards();
                resetExercise();
                final Intent intent;
                intent = new Intent(MemoryActivity.this, LoseActivity.class);
                bundle.putInt("errors", 0);
                bundle.putBoolean("timeOut", true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
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

    private void makeBundle() {
        bundle = new Bundle();
        bundle.putParcelable("user", userModel);
        bundle.putParcelable("game_cat", gamesCatModel);
    }

    private void manageWrongAnswer() {
        turnIsOver = false;
        clicked = 0;
        disableAllCards();
    }

    private void disableAllCards() {
        for (ImageButton btn : cards) {
            btn.setEnabled(false);
        }
    }

    private void flipCards() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enableAllCards();
                firstCard.setImageResource(ic_card);
                secondCard.setImageResource(ic_card);
            }
        }, 1000);
    }

    private void enableAllCards() {
        for (ImageButton btn : cards) {
            btn.setEnabled(true);
        }
        if (!disabledCards.isEmpty()){
            for (ImageButton btn : disabledCards) {
                btn.setEnabled(false);
            }
        }
    }

    private void manageCorrectAnswer() {
        game.addGoodAnswer();
        if (!disabledCards.contains(firstCard) || !disabledCards.contains(secondCard)){
            disabledCards.add(firstCard);
            disabledCards.add(secondCard);
        }
        disableAllCards();
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enableAllCards();
                turnIsOver = false;
                clicked = 0;
            }
        }, 1000);
    }

    private void setSecondImage(int finalI) {
        secondCard = cards[finalI];
        secondCard.setImageResource(images[finalI]);
        game.setSecondImage(images[finalI]);
        clicked++;
        turnIsOver = true;
    }

    private void setFirstImage(int finalI) {
        firstCard = cards[finalI];
        firstCard.setImageResource(images[finalI]);
        game.setFirstImage(images[finalI]);
        clicked++;
    }

    private void makeCardsArray() {
        cards = new ImageButton[]{answer0, answer1, answer2, answer3, answer4, answer5, answer6,
                answer7,
                answer8,
                answer9
                , answer10, answer11};
    }

    private void manageButtonViews() {
        answer0 = findViewById(id.idBTAnswer0);
        answer1 = findViewById(id.idBTAnswer1);
        answer2 = findViewById(id.idBTAnswer2);
        answer3 = findViewById(id.idBTAnswer3);
        answer4 = findViewById(id.idBTAnswer4);
        answer5 = findViewById(id.idBTAnswer5);
        answer6 = findViewById(id.idBTAnswer6);
        answer7 = findViewById(id.idBTAnswer7);
        answer8 = findViewById(id.idBTAnswer8);
        answer9 = findViewById(id.idBTAnswer9);
        answer10 = findViewById(id.idBTAnswer10);
        answer11 = findViewById(id.idBTAnswer11);
    }

    private void makeNewGame() {
        game = new MemoryGameModel(gamesCatModel.getTitle());
    }

    private void getObjectsFromIntent() {
        if (getIntent().getExtras() != null) {
            userModel = getIntent().getParcelableExtra("user");
            gamesCatModel = getIntent().getParcelableExtra("cat");
        }
    }

    private void updateScores() {
        class UpdateScores extends AsyncTask<Void, Void, ScoresTrackerModel> {

            @Override
            protected ScoresTrackerModel doInBackground(Void... voids) {
                ScoresTrackerModel updatedScores =
                        mDb.getAppDatabase().scoresDAO().getScoreFromUserId(userModel.getId());
                ArrayList<Long> updatedMemoryCompleted =
                        new ArrayList<>(updatedScores.getGamesCompleted());

                if (!updatedScores.getGamesCompleted().contains(gamesCatModel.getId())) {
                    updatedMemoryCompleted.add(gamesCatModel.getId());
                    updatedScores.setGamesCompleted(updatedMemoryCompleted);
                    updatedScores.computeGamesScore();
                    mDb.getAppDatabase().scoresDAO().update(updatedScores);
                }

                return updatedScores;
            }

            @Override
            protected void onPostExecute(ScoresTrackerModel scoresModel) {
                super.onPostExecute(scoresModel);
                bundle.putParcelable("scores", scoresModel);
                stopTimer();
                goToWinActivity();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        UpdateScores update = new UpdateScores();
        update.execute();
    }
}