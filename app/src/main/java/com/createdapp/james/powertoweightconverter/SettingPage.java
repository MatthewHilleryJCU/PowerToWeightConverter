package com.createdapp.james.powertoweightconverter;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingPage extends AppCompatActivity {

    public SharedPreferences preferences;
    private int powerPosition;
    private String powerValue;
    private int weightPosition;
    private String weightValue;
    private Spinner spinner1, spinner2;
    TextView errorDisplay;
    String wrongMsg = "Wrong Unit Type Selected";

    public void saveButtonHandler(View view) {
        if (!String.valueOf(powerPosition).equals(String.valueOf(weightPosition))) {
            errorDisplay.setText(wrongMsg);
        } else {
            preferences.edit()
                    .putInt("powerPosition", powerPosition)
                    .putString("powerValue", powerValue)
                    .putInt("weightPosition", weightPosition)
                    .putString("weightValue", weightValue).apply();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        preferences = getSharedPreferences("P2W_Prefs", MODE_PRIVATE);
        errorDisplay = (TextView) findViewById(R.id.errorMsg);

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.power_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.weight_arrays, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner1.setSelection(preferences.getInt("powerPosition", 0));
        spinner2.setSelection(preferences.getInt("weightPosition", 0));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                powerValue = spinner1.getSelectedItem().toString();
                powerPosition = spinner1.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                weightValue = spinner2.getSelectedItem().toString();
                weightPosition = spinner2.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        System.out.println("Changing Settings...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Settings Saved!");
    }
}
