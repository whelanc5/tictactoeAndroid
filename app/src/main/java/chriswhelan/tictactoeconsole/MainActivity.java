package chriswhelan.tictactoeconsole;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;



public class MainActivity extends  Activity implements OnEditorActionListener, OnClickListener {

    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';
    private final int BOARD_SIZE = 9;
    private Random mRand;
    private static final String TAG = "MainActivity";
    private TextView status;
    private Button   button1;
    private Button   button2;
    private Button   button3;
    private Button   button4;
    private Button   button5;
    private Button   button6;
    private Button   button7;
    private Button   button8;
    private Button   button9;
    //private Button   newGame;
    boolean gameOver = false;
    private SharedPreferences mPrefs;

    private List<Button> bList = new ArrayList<Button>();

    char turn = HUMAN_PLAYER;
    private char mBoard[] = {'1','2','3','4','5','6','7','8','9'};



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_game, menu);
        return true;
    }




    private void startNewGame(){
        clearGrid();
        status.setText("X's Turn");
        for (int i = 0; i < 9; i++){
            mBoard[i] = (char) i;
        }
        turn = HUMAN_PLAYER;

    }

    private void setStartingValues(){

    }

    public void clearGrid(){
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        gameOver = false;


    }

    private void checkForGameOver(){
        if (checkForWinner() > 0){
            gameOver = true;
        }

    }

    public void onResume() {
        super.onResume();
        button1.setText(mPrefs.getString("button1", ""));
        button2.setText(mPrefs.getString("button2", ""));
        button3.setText(mPrefs.getString("button3", ""));
        button4.setText(mPrefs.getString("button4", ""));
        button5.setText(mPrefs.getString("button5", ""));
        button6.setText(mPrefs.getString("button6", ""));
        button7.setText(mPrefs.getString("button7", ""));
        button8.setText(mPrefs.getString("button8", ""));
        button9.setText(mPrefs.getString("button9", ""));
        status.setText(mPrefs.getString("status", ""));
        if(mPrefs.getString("button1", "").equals("X") || mPrefs.getString("button1", "").equals("O"))
        mBoard[0] = mPrefs.getString("button1", "").charAt(0);
        if(mPrefs.getString("button2", "").equals("X") || mPrefs.getString("button2", "").equals("O"))
        mBoard[1] = mPrefs.getString("button2", "").charAt(0);
        if(mPrefs.getString("button3", "").equals("X") || mPrefs.getString("button3", "").equals("O"))
        mBoard[2] = mPrefs.getString("button3", "").charAt(0);
        if(mPrefs.getString("button4", "").equals("X") || mPrefs.getString("button4", "").equals("O"))
        mBoard[3] = mPrefs.getString("button4", "").charAt(0);
        if(mPrefs.getString("button5", "").equals("X") || mPrefs.getString("button5", "").equals("O"))
        mBoard[4] = mPrefs.getString("button5", "").charAt(0);
        if(mPrefs.getString("button6", "").equals("X") || mPrefs.getString("button6", "").equals("O"))
        mBoard[5] = mPrefs.getString("button6", "").charAt(0);
        if(mPrefs.getString("button7", "").equals("X") || mPrefs.getString("button7", "").equals("O"))
        mBoard[6] = mPrefs.getString("button7", "").charAt(0);
        if(mPrefs.getString("button8", "").equals("X") || mPrefs.getString("button8", "").equals("O"))
        mBoard[7] = mPrefs.getString("button8", "").charAt(0);
        if(mPrefs.getString("button9", "").equals("X") || mPrefs.getString("button9", "").equals("O"))
        mBoard[8] = mPrefs.getString("button9", "").charAt(0);

        // get preferences

    }
    public void onPause() {
        // save the instance variables
        Editor editor = mPrefs.edit();
        editor.putString("button1", button1.getText().toString());
        editor.putString("button2", button2.getText().toString());
        editor.putString("button3", button3.getText().toString());
        editor.putString("button4", button4.getText().toString());
        editor.putString("button5", button5.getText().toString());
        editor.putString("button6", button6.getText().toString());
        editor.putString("button7", button7.getText().toString());
        editor.putString("button8", button8.getText().toString());
        editor.putString("button9", button9.getText().toString());
        editor.putString("status", status.getText().toString());
        editor.putBoolean("gameOver", gameOver);
        editor.commit();

        super.onPause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRand = new Random();
        status = (TextView) findViewById(R.id.textView2);
       // newGame = (Button) findViewById(R.id.newGameButton);
        button1 = (Button) findViewById(R.id.square1);

        button2 = (Button) findViewById(R.id.square2);
        button3 = (Button) findViewById(R.id.square3);
        button4 = (Button) findViewById(R.id.square4);
        button5 = (Button) findViewById(R.id.square5);
        button6 = (Button) findViewById(R.id.square6);
        button7 = (Button) findViewById(R.id.square7);
        button8 = (Button) findViewById(R.id.square8);
        button9 = (Button) findViewById(R.id.square9);



        button9.setOnClickListener(this);
        button8.setOnClickListener(this);
        button7.setOnClickListener(this);
        button6.setOnClickListener(this);
        button5.setOnClickListener(this);
        button4.setOnClickListener(this);
        button3.setOnClickListener(this);
        button2.setOnClickListener(this);
        button1.setOnClickListener(this);
      //  newGame.setOnClickListener(this);

        bList.add(button1);
        bList.add(button2);
        bList.add(button3);
        bList.add(button4);
        bList.add(button5);
        bList.add(button6);
        bList.add(button7);
        bList.add(button8);
        bList.add(button9);


//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

         mPrefs = getSharedPreferences("preferences", 0);
        //prefs = PreferenceManager.getDefaultSharedPreferences(this);


    }
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.menu:
                startNewGame();

                break;

        }

        return true;
    }

    @Override
    public void onClick(View v) {
        /*
        if (v.getId() == R.id.newGameButton) {

            startNewGame();
        }
        */
        checkForGameOver();
        if (gameOver == false) {

            if (turn == HUMAN_PLAYER) {
                switch (v.getId()) {
                    case R.id.square1:
                        if (!button1.getText().equals("X") && !button1.getText().equals("O")) {
                            bList.get(0).setText("X");
                            mBoard[0] = 'X';
                            turn = COMPUTER_PLAYER;

                        }
                        break;
                    case R.id.square2:
                        if (!button2.getText().equals("X") && !button2.getText().equals("O")) {
                            bList.get(1).setText("X");
                            mBoard[1] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square3:
                        if (!button3.getText().equals("X") && !button3.getText().equals("O")) {
                            bList.get(2).setText("X");
                            mBoard[2] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square4:
                        if (!button4.getText().equals("X") && !button4.getText().equals("O")) {
                            bList.get(3).setText("X");
                            mBoard[3] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square5:
                        if (!button5.getText().equals("X") && !button5.getText().equals("O")) {
                            bList.get(4).setText("X");
                            mBoard[4] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square6:
                        if (!button6.getText().equals("X") && !button6.getText().equals("O")) {
                            bList.get(5).setText("X");
                            mBoard[5] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square7:
                        if (!button7.getText().equals("X") && !button7.getText().equals("O")) {
                            bList.get(6).setText("X");
                            mBoard[6] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square8:
                        if (!button8.getText().equals("X") && !button8.getText().equals("O")) {
                            bList.get(7).setText("X");
                            mBoard[7] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                    case R.id.square9:
                        if (!button9.getText().equals("X") && !button9.getText().equals("O")) {
                            bList.get(8).setText("X");
                            mBoard[8] = 'X';
                            turn = COMPUTER_PLAYER;
                        }
                        break;
                }
            }
            if (checkForWinner() == 2) {
                status.setText("X wins");
            } else if (checkForWinner() == 1) {
                status.setText("Tie Game");
            } else if (turn == COMPUTER_PLAYER) {
                bList.get(getComputerMove()).setText("O");
                turn = HUMAN_PLAYER;
                if (checkForWinner() == 3) {
                    status.setText("O wins");
                } else if (checkForWinner() == 1) {
                    status.setText("Tie Game");
                } else {
                    status.setText("X 's Turn");
                }
            }
        }
    }
    private int checkForWinner() {

        // Check horizontal wins
        for (int i = 0; i <= 6; i += 3)	{
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+1] == HUMAN_PLAYER &&
                    mBoard[i+2]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+1]== COMPUTER_PLAYER &&
                    mBoard[i+2] == COMPUTER_PLAYER)
                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+3] == HUMAN_PLAYER &&
                    mBoard[i+6]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+3] == COMPUTER_PLAYER &&
                    mBoard[i+6]== COMPUTER_PLAYER)
                return 3;
        }

        // Check for diagonal wins
        if ((mBoard[0] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[8] == HUMAN_PLAYER) ||
                (mBoard[2] == HUMAN_PLAYER &&
                        mBoard[4] == HUMAN_PLAYER &&
                        mBoard[6] == HUMAN_PLAYER))
            return 2;
        if ((mBoard[0] == COMPUTER_PLAYER &&
                mBoard[4] == COMPUTER_PLAYER &&
                mBoard[8] == COMPUTER_PLAYER) ||
                (mBoard[2] == COMPUTER_PLAYER &&
                        mBoard[4] == COMPUTER_PLAYER &&
                        mBoard[6] == COMPUTER_PLAYER))
            return 3;

        // Check for tie
        for (int i = 0; i < 9; i++) {
            // If we find a number, then no one has won yet
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
                return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;
    }
    private int getComputerMove()
    {
        int move;


        // First see if there's a move O can make to win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = COMPUTER_PLAYER;
                if (checkForWinner() == 3) {
                    return i;
                }else {
                    mBoard[i] = curr;
                }

                   // mBoard[i] = curr;
            }
        }

            // See if there's a move O can make to block X from winning
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                    char curr = mBoard[i];   // Save the current number
                    mBoard[i] = HUMAN_PLAYER;
                    if (checkForWinner() == 2) {
                        mBoard[i] = COMPUTER_PLAYER;
                        //  System.out.println("Computer is moving to " + (i + 1));

                        return i;
                    }else {
                        mBoard[i] = curr;
                    }
                    // mBoard[i] = curr;
                }
            }

        // Generate random move

            do {
                move = mRand.nextInt(BOARD_SIZE);
            } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER);

       // System.out.println("Computer is moving to " + (move + 1));

        mBoard[move] = COMPUTER_PLAYER;

      return move;

    }
}
