package com.example.scs.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0;
    int[] currentState = {2,2,2,2,2,2,2,2,2};
    int[][] winningCombinations = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    LinearLayout linearLayout;
    TextView gameWonText;
    android.support.v7.widget.GridLayout gridLayout;
    boolean gameActive = true;

    public void play(View view){

        ImageView img = (ImageView) view;
        int tag = Integer.parseInt(img.getTag().toString());
        String player;

        if(currentState[tag] == 2 && gameActive){

            currentState[tag] = currentPlayer;
            img.setTranslationY(-1000f);

            //0 = red, 1=yellow
            if(currentPlayer == 0) {
                img.setImageResource(R.drawable.red);
                currentState[tag] = 0;
                player = "Red";
                currentPlayer = 1;
            }
            else{
                img.setImageResource(R.drawable.yellow);
                currentState[tag] = 1;
                player = "Yellow";
                currentPlayer = 0;
            }

            img.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningCombination : winningCombinations){
                if(( currentState[winningCombination[0]] == currentState[winningCombination[1]] &&
                        currentState[winningCombination[1]] == currentState[winningCombination[2]] &&
                        currentState[winningCombination[0]] != 2 )) {
                    gameEnd(player + " Won!");
                }
                else{
                    boolean gameOver = true;

                    for(int state : currentState){
                        if(state == 2)
                            gameOver = false;
                    }

                    if(gameOver){
                        gameEnd("Game Tied!");
                    }
                }
            }
        }
    }

    public void gameEnd(String text){
        linearLayout = (LinearLayout) findViewById(R.id.new_game_layout);
        gameWonText = (TextView) findViewById(R.id.team_won_text);

        linearLayout.setVisibility(View.VISIBLE);
        gameActive = false;
        gameWonText.setText(text);

    }

    public  void playAgain(View view){

        gameActive = true;
        gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.grid_layout);
        linearLayout.setVisibility(View.INVISIBLE);
        currentPlayer = 0;

        for(int i=0; i<currentState.length; i++)
            currentState[i] = 2;

        for(int i=0; i<gridLayout.getChildCount(); i++)
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}