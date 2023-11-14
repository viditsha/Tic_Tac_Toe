package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    private boolean playerXTurn = true;
    private boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        buttons = new Button[3][3];

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGridButtonClick((Button) v);
                    }
                });
            }
        }

        // Set click listener for the Reset button
        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void onGridButtonClick(Button button) {
        if (gameActive && button.getText().toString().equals("")) {
            if (playerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            if (checkForWin()) {
                gameActive = false;
                displayWinner();
            } else if (checkForDraw()) {
                gameActive = false;
                displayDraw();
            } else {
                playerXTurn = !playerXTurn;
            }
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                    checkRowCol(buttons[0][i], buttons[1][i], buttons[2][i])) {
                return true;
            }
        }

        return checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkRowCol(Button b1, Button b2, Button b3) {
        return b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText()) && !b1.getText().toString().equals("");
    }

    private boolean checkForDraw() {
        // Check if all buttons are filled (draw)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayWinner() {
        String winner = playerXTurn ? "Player X" : "Player O";
        // Display winner or tie message
        // You can customize this part based on your preference (e.g., using a dialog)
        // For simplicity, we'll use toast messages.
        // For a better user experience, consider using a custom dialog.
        showToast();
    }

    private void displayDraw() {
        // Display a draw message
        showToast();
    }

    private void showToast() {
    }

    private void resetGame() {
        // Reset the game state
        gameActive = true;
        playerXTurn = true;

        // Clear the text on all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
    
