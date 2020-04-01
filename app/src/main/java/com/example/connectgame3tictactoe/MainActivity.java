package com.example.connectgame3tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: red, 1: yellow, 2: blank
    int imageColor = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningCombinations = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean hasWon = false;

    public void dropIn(View view) {
        ImageView clickedImage = (ImageView) view;
        int clickedPosition = Integer.parseInt(clickedImage.getTag().toString());
        if (gameState[clickedPosition] == 2 && !hasWon) {
            clickedImage.setTranslationY(-1000);
            if (imageColor == 0) {
                clickedImage.setImageResource(R.drawable.yellow);
                gameState[clickedPosition] = 1;
                imageColor = 1;
            } else {
                clickedImage.setImageResource(R.drawable.red);
                gameState[clickedPosition] = 0;
                imageColor = 0;
            }
            clickedImage.animate().translationYBy(1000).rotation(3600);
            for (int[] combination : winningCombinations) {
                if (gameState[combination[0]] != 2 && gameState[combination[0]] == gameState[combination[1]] && gameState[combination[1]] == gameState[combination[2]]) {
                    // winner found
                    hasWon = true;
                    String winner = "";
                    if (imageColor == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }
                    TextView winnerTextView = findViewById(R.id.winnerText);
                    Button playAgainButton = findViewById(R.id.playAgainButton);

                    winnerTextView.setVisibility(View.VISIBLE);
                    winnerTextView.setText(winner + " has won ");
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        TextView winnerTextView = findViewById(R.id.winnerText);
        Button playAgainButton = findViewById(R.id.playAgainButton);

        winnerTextView.setVisibility(View.INVISIBLE);

        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            if (gameState[i] != 2) {
                ImageView imageView = (ImageView) gridLayout.getChildAt(i);
                imageView.setImageDrawable(null);
            }
        }
        hasWon = false;
        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
