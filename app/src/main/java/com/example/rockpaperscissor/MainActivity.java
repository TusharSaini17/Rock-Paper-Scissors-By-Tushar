package com.example.rockpaperscissor;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public SoundPool soundPool;
    public int M_win,M_lose,lose,win,tie;
    TextView scoreText, wonLostText, userSelectText, compSelectText;

    int userScore = 0;
    int compScore = 0;

    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        wonLostText = findViewById(R.id.wonLostText);
        compSelectText = findViewById(R.id.compSelectText);
        userSelectText = findViewById(R.id.userSelectText);

       wonLostText.setText("");
        compSelectText.setText("");
        userSelectText.setText("");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        M_win = soundPool.load(this, R.raw.victory, 1);
        M_lose = soundPool.load(this, R.raw.over, 1);
        win = soundPool.load(this, R.raw.win, 1);
        lose = soundPool.load(this, R.raw.lose, 1);
        tie = soundPool.load(this, R.raw.tie, 1);
    }

    public void resetButton(View view) {
        wonLostText.setText("");
        userSelectText.setText("");
        compSelectText.setText("");
        userScore=0;
        compScore=0;
        setScoreTextView(userScore,compScore);

    }
    public void reset(){
        wonLostText.setText("");
        userSelectText.setText("");
        compSelectText.setText("");
        userScore=0;
        compScore=0;
        setScoreTextView(userScore,compScore);
    }

    public void rpsButton(View view) {
        Log.i(TAG, "rpsButton" + view.getTag());
        int userSelection = Integer.parseInt(view.getTag().toString());
        match(userSelection);
    }
    private void match(int userSelection) {




        int compSelection = random.nextInt(3);


        if (userSelection == compSelection) {
            wonLostText.setText("It's a Tie !");
            soundPool.autoPause();
            soundPool.play(tie, 1, 1, 0, 0, 1);
        } else if (userSelection == 0 && compSelection == 2 || userSelection == 1 && compSelection == 0
                || userSelection == 2 && compSelection == 1) {
            wonLostText.setText("You Won !");
            soundPool.autoPause();
            soundPool.play(win, 1, 1, 0, 0, 1);
            userScore++;
        } else {
            wonLostText.setText("You Lost !");
            soundPool.autoPause();
            soundPool.play(lose, 1, 1, 0, 0, 1);
            compScore++;
        }


        switch (compSelection) {
            case 0:
                compSelectText.setText("Rock");
                break;
            case 1:
                compSelectText.setText("Paper");
                break;
            case 2:
                compSelectText.setText("Scissor");
                break;
            default:
                compSelectText.setText("");
        }

        switch (userSelection) {
            case 0:
                userSelectText.setText("Rock");
                break;
            case 1:
                userSelectText.setText("Paper");
                break;
            case 2:
                userSelectText.setText("Scissor");
                break;
            default:
                userSelectText.setText("");
        }
        setScoreTextView(userScore, compScore);
    if (userScore== 5){
        soundPool.autoPause();
        soundPool.play(M_win, 1, 1, 0, 0, 1);
        openWin();
        reset();
    }
    else if (compScore==5){
        soundPool.autoPause();
        soundPool.play(M_lose, 1, 1, 0, 0, 1);
        openlose();
        reset();
    }
    }
    private void setScoreTextView(int userScore, int compScore){
        scoreText.setText(String.valueOf(userScore)+":"+String.valueOf(compScore));
    }
    public void openWin(){
        windialog windialog= new windialog();
        windialog.show(getSupportFragmentManager(),"Win dialog");
    }
    public void openlose(){
      losedialog losedialog=new losedialog();
      losedialog.show(getSupportFragmentManager(),"lose dialog");
    }


}