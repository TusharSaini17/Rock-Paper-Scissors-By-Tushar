package com.example.rockpaperscissor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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

        int low = 1;
        int high = 3;


        int compSelection = random.nextInt(high) + low;


        if (userSelection == compSelection) {
            wonLostText.setText("It's a Tie !");
        } else if ( (userSelection - compSelection) % 3 == 1 ) {
            wonLostText.setText("you Won !");
            userScore++;
        } else {
            wonLostText.setText("you Lost !");
            compScore++;
        }


        switch (compSelection) {
            case 1:
                compSelectText.setText("Rock");
                break;
            case 2:
                compSelectText.setText("Paper");
                break;
            case 3:
                compSelectText.setText("Scissor");
                break;
            default:
                compSelectText.setText("");
        }

        switch (userSelection) {
            case 1:
                userSelectText.setText("Rock");
                break;
            case 2:
                userSelectText.setText("Paper");
                break;
            case 3:
                userSelectText.setText("Scissor");
                break;
            default:
                userSelectText.setText("");
        }
        setScoreTextView(userScore, compScore);
    if (userScore== 5){
        openWin();
        reset();
    }
    else if (compScore==5){
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