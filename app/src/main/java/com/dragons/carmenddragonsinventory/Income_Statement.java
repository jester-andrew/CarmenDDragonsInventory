package com.dragons.carmenddragonsinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class Income_Statement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income__statement);

        {   //This code puts the current date into a string
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

            TextView textViewDate = findViewById(R.id.textView11);
            textViewDate.setText(currentDate);
        }
    }
}
