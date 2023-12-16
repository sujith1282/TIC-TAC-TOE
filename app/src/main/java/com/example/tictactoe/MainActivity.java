package com.example.tictactoe;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = ((Button[][]) Array.newInstance(Button.class, new int[]{3, 3}));
//    private boolean player1Turn = true;
    private int roundCount;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j] = (Button) findViewById(getResources().getIdentifier("button_" + i + j, "id", getPackageName()));
                this.buttons[i][j].setOnClickListener(this);
            }
        }
        ((Button) findViewById(R.id.button_reset)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.resetGame();
            }
        });
    }

    public void onClick(View v) {
        if (((Button) v).getText().toString().equals("")) {
            if (this.player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }
            this.roundCount++;
            if (checkForWin()) {
                if (this.player1Turn) {
                    player1Wins();
                } else {
                    player2Wins();
                }
            } else if (this.roundCount == 9) {
                draw();
            } else {
                this.player1Turn = !this.player1Turn;
            }
        }
    }

    private boolean checkForWin() {
        String[][] board = (String[][]) Array.newInstance(String.class, new int[]{3, 3});
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = this.buttons[i][j].getText().toString();
            }
        }
        for (int i2 = 0; i2 < 3; i2++) {
            if (board[i2][0].equals(board[i2][1]) && board[i2][0].equals(board[i2][2]) && !board[i2][0].equals("")) {
                return true;
            }
        }
        for (int i3 = 0; i3 < 3; i3++) {
            if (board[0][i3].equals(board[1][i3]) && board[0][i3].equals(board[2][i3]) && !board[0][i3].equals("")) {
                return true;
            }
        }
        if (!board[0][0].equals(board[1][1]) || !board[0][0].equals(board[2][2]) || board[0][0].equals("")) {
            return board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("");
        }
        return true;
    }

    private void player1Wins() {
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void player2Wins() {
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.buttons[i][j].setText("");
            }
        }
        this.roundCount = 0;
        this.player1Turn = true;
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
        resetBoard();
    }

    /* access modifiers changed from: private */
    public void resetGame() {
        resetBoard();
        Toast.makeText(this, "Game Reset!", Toast.LENGTH_LONG).show();
    }
}