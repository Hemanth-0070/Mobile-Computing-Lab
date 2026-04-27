package com.hemanth.radiobuttonapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button showBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        showBtn = findViewById(R.id.showBtn);
        resultText = findViewById(R.id.resultText);

        showBtn.setOnClickListener(v -> {

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                resultText.setText("Please select an option");
            } else {
                RadioButton selectedRadio = findViewById(selectedId);
                resultText.setText("Selected: " + selectedRadio.getText());
            }
        });
    }
}