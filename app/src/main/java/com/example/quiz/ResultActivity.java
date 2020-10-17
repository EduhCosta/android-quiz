package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.quiz.enums.Constants.PERCENTAGE_RESULTS;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        TextView txtPercentage = findViewById(R.id.val_percentage_result);
        String intentMessage = intent.getStringExtra(PERCENTAGE_RESULTS.getValor());
        txtPercentage.setText(intentMessage);

        this.setGoHome();
    }

    private void setGoHome() {
        Button btnRestart = findViewById(R.id.btn_restart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });
    }


}