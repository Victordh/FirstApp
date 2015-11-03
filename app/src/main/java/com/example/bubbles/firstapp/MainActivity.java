package com.example.bubbles.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView hint, number, up_arrow_text, down_arrow_text, win;
    private Button hint_button, submit_guess;
    private ImageView up_arrow, down_arrow;
    private EditText guess;
    private int computer_number, hint_min = 0, hint_max = 1000;
    private String string_hint_min, string_hint_max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialises all text, images and buttons
        initialise();
        // hides text and images that shouldn't be shown at the start
        hide_arrows_and_win();
        // hides hint
        hint.setVisibility(View.GONE);

        // creates a random number for the user to find
        random_number();
    }

    // initialises all text, images and buttons
    public void initialise() {
        // initialises edittext
        guess = (EditText) findViewById(R.id.guess);
        // initialises text
        TextView instructions = (TextView) findViewById(R.id.instructions);
        TextView number_of_guesses = (TextView) findViewById(R.id.number_of_guesses);
        number = (TextView) findViewById(R.id.number);
        hint = (TextView) findViewById(R.id.hint);
        up_arrow_text = (TextView) findViewById(R.id.up_arrow_text);
        down_arrow_text = (TextView) findViewById(R.id.down_arrow_text);
        win = (TextView) findViewById(R.id.win);
        // initialises images
        up_arrow = (ImageView) findViewById(R.id.up_arrow);
        down_arrow = (ImageView) findViewById(R.id.down_arrow);
        // initialises buttons
        submit_guess = (Button) findViewById(R.id.submit_guess);
        Button reset = (Button) findViewById(R.id.reset);
        hint_button = (Button) findViewById(R.id.hint_button);
    }

    // hides arrows, arrow text and win message
    public void hide_arrows_and_win() {
        // hides win message
        win.setVisibility(View.GONE);
        // hides arrows
        up_arrow.setVisibility(View.GONE);
        down_arrow.setVisibility(View.GONE);
        // hides arrow text
        up_arrow_text.setVisibility(View.GONE);
        down_arrow_text.setVisibility(View.GONE);
    }

    // switches between showing and hiding the hint
    public void hint_button_click(View view) {
        string_hint_min = Integer.toString(hint_min);
        string_hint_max = Integer.toString(hint_max);
        hint.setText("Try guessing between " + string_hint_min + " and " + string_hint_max + ".");
        if (hint.getVisibility() == View.VISIBLE) {
            hint.setVisibility(View.GONE);
            hint_button.setText(R.string.show_hint);
        }
        else {
            hint.setVisibility(View.VISIBLE);
            hint_button.setText(R.string.hide_hint);
        }
    }

    // generates a random number between min (inclusive) and max (exclusive)
    public void random_number() {
        int min = 1;
        int max = 1001;
        Random r = new Random();
        computer_number = r.nextInt(max - min) + min;
    }

    // resets the game
    public void reset_button_click(View view) {
        hide_arrows_and_win();
        number.setText(R.string.number);
        guess.clearComposingText();
        hint_min = 0;
        hint_max = 1000;
        submit_guess.setVisibility(View.VISIBLE);
        random_number();
    }

    // compares the_number with the entered value and updates number_of_guesses
    public void click_submit_guess(View view) {
        // hides any previously shown arrows, arrow text and win message
        hide_arrows_and_win();
        // compares the_number with entered value and gives the correct arrow as hint
        int user_number;
        if (guess.getText().toString().equals(""))
            user_number = -1;
        else
            user_number = Integer.parseInt(guess.getText().toString());
        if (user_number < computer_number) {
            up_arrow.setVisibility(View.VISIBLE);
            up_arrow_text.setVisibility(View.VISIBLE);
            hint_min = user_number;
        }
        else if (user_number > computer_number) {
            down_arrow.setVisibility(View.VISIBLE);
            down_arrow_text.setVisibility(View.VISIBLE);
            hint_max = user_number;
        }
        else if (user_number == computer_number) {
            win.setVisibility(View.VISIBLE);
            submit_guess.setVisibility(View.GONE);
        }

        // updates the number of guesses
        int number_of_guesses_int = Integer.parseInt(number.getText().toString());
        number_of_guesses_int++;
        number.setText(Integer.toString(number_of_guesses_int));
    }
}