package com.createdapp.james.powertoweightconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    public SharedPreferences preferences;

    TextView resultDisplay, userChoicePower, userChoiceWeight, messageDisplay;
    EditText userInputPower, userInputWeight;

    String kw = "KW/t";
    String w = "W/kg";
    String hp = "HP/lb";
    String emptyAll = "Empty Power and Weight Values";
    String emptyPower = "Empty Power Value";
    String emptyWeight = "Empty Weight Value";

    public void calculateButtonHandler(View view) {
        try {
            if (userChoicePower.getText().toString().equals("Kilowatt") && userChoiceWeight.getText().toString().equals("Kilogram")) {
                messageDisplay.setText(kw);
                if ((userInputPower.getText().toString().length() == 0) && (userInputWeight.getText().toString().length() == 0)) {
                    messageDisplay.setText(emptyAll);
                } else if (userInputPower.getText().toString().length() == 0) {
                    messageDisplay.setText(emptyPower);
                } else if (userInputWeight.getText().toString().length() == 0) {
                    messageDisplay.setText(emptyWeight);
                }
            } else if (userChoicePower.getText().toString().equals("Watt") && userChoiceWeight.getText().toString().equals("Gram")) {
                messageDisplay.setText(w);
                if ((userInputPower.getText().toString().length() == 0) && (userInputWeight.getText().toString().length() == 0)) {
                    messageDisplay.setText(emptyAll);
                } else if (userInputPower.getText().toString().length() == 0) {
                    messageDisplay.setText(emptyPower);
                } else if (userInputWeight.getText().toString().length() == 0) {
                    messageDisplay.setText(emptyWeight);
                }
            } else if (userChoicePower.getText().toString().equals("Horsepower") && userChoiceWeight.getText().toString().equals("Pound")) {
                messageDisplay.setText(hp);
                if ((userInputPower.getText().toString().length() == 0) && (userInputWeight.getText().toString().length() == 0)) {
                    messageDisplay.setText(emptyAll);
                } else if (userInputPower.getText().toString().length() == 0) {
                    messageDisplay.setText(emptyPower);
                } else if (userInputWeight.getText().toString().length() == 0) {
                    messageDisplay.setText(emptyWeight);
                }
            }

            String value = userInputPower.getText().toString();
            String value2 = userInputWeight.getText().toString();
            int val1 = Integer.parseInt(value);
            int val2 = Integer.parseInt(value2);
            int resA = (int) (((double) val1 * 1000) / (double) val2);
            resultDisplay.setText(String.valueOf(resA));
        } catch (Exception e) {
            System.out.println("Exception caught!");
        }
    }

    public void clearButtonHandler(View view) {
        ((EditText) findViewById(R.id.powerInput)).setText("");
        ((EditText) findViewById(R.id.weightInput)).setText("");
        ((TextView) findViewById(R.id.ansDisplay)).setText("");
    }

    public void settingsButtonHandler(View view) {
        ((EditText) findViewById(R.id.powerInput)).setText("");
        ((EditText) findViewById(R.id.weightInput)).setText("");
        ((TextView) findViewById(R.id.ansDisplay)).setText("");
        Intent intent = new Intent(this, SettingPage.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences("P2W_Prefs", MODE_PRIVATE);

        userInputPower = (EditText) findViewById(R.id.powerInput);
        userChoicePower = (TextView) findViewById(R.id.powerChoice);
        userInputWeight = (EditText) findViewById(R.id.weightInput);
        userChoiceWeight = (TextView) findViewById(R.id.weightChoice);
        resultDisplay = (TextView) findViewById(R.id.ansDisplay);
        messageDisplay = (TextView) findViewById(R.id.ansUnit);

        userChoicePower.setText(preferences.getString("powerValue", ""));
        userChoiceWeight.setText(preferences.getString("weightValue", ""));

        if (userChoicePower.getText().toString().equals("Kilowatt") && userChoiceWeight.getText().toString().equals("Kilogram")) {
            messageDisplay.setText(kw);
        } else if (userChoicePower.getText().toString().equals("Watt") && userChoiceWeight.getText().toString().equals("Gram")) {
            messageDisplay.setText(w);
        } else if (userChoicePower.getText().toString().equals("Horsepower") && userChoiceWeight.getText().toString().equals("Pound")) {
            messageDisplay.setText(hp);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        System.out.println("Ready To Calculate!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Calculation Stopped...");
    }
}

