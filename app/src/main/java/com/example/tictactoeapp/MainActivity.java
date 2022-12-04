package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton[][] buttons = new ImageButton[3][3];

    private boolean player1Turn = true;
    private TextView textWinnerView;
    private int[] board;
    private int roundCount;
    private ImageView background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBoard();


        textWinnerView = findViewById(R.id.text_winner);
        textWinnerView.setText("New Game - X Turn");
        background=findViewById(R.id.imageView4);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "button_" + i + j;
                int res = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(res);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setBackgroundColor(Color.parseColor("#000000"));
            }
        }


        Button buttonReset = findViewById(R.id.reset_button);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setImageResource(0);
                        buttons[i][j].setBackgroundColor(Color.parseColor("#000000"));
                    }
                }
                roundCount = 0;
                textWinnerView.setText("New Game - X Turn");
                background.setImageResource(0);
                setBoard();

            }
        });
    }


    @Override
    public void onClick(View view) {

        int tag = Integer.parseInt(String.valueOf(view.getTag()));

        if (player1Turn) {
            textWinnerView.setText("O Turn");
            ((ImageButton) view).setImageResource(R.drawable.x);
            board[tag] = 0;

        } else {
            ((ImageButton) view).setImageResource(R.drawable.o);
            textWinnerView.setText("X Turn");
            board[tag] = 1;
        }

        roundCount++;


        if (checkWin()) {   //there is a win
            if (player1Turn) {
                player1Wins();
                System.out.println("1 won");
            } else {
                player2Wins();
                System.out.println("2 won");

            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private void setBoard() {
        board = new int[]{
                2, 2, 2,
                2, 2, 2,
                2, 2, 2,
        };
    }

}