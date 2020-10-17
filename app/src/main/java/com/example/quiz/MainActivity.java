package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.enums.Constants;
import com.example.quiz.utils.Engine;
import com.example.quiz.utils.TxtReader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.quiz.enums.Constants.PERCENTAGE_RESULTS;

public class MainActivity extends AppCompatActivity {
    private Engine engine;
    private int actualQuestion = 0;
    private LinkedList<String> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getResources().getAssets();
        LinkedList<String> fileContent = new TxtReader(assetManager).getFileContent();

        this.engine = new Engine(this.defineResponsesList(fileContent));
        this.questions = fileContent;

        this.setQuestionOnCard();
        this.defineBtns();
    }

    private void defineBtns() {
        Button btnTrue = findViewById(R.id.btn_true);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine.responseQuestion(MainActivity.this, "true", actualQuestion);
                next();
            }
        });

        Button btnFalse = findViewById(R.id.btn_false);
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engine.responseQuestion(MainActivity.this, "false", actualQuestion);
                next();
            }
        });
    }

    private void next() {
        if (actualQuestion < (questions.size() - 2)) {
            actualQuestion++;
            this.setQuestionOnCard();
        } else {
            actualQuestion = 0;
            Intent resultIntent = new Intent(this, ResultActivity.class);
            String percent = this.engine.showPercentage();
            resultIntent.putExtra(PERCENTAGE_RESULTS.getValor(), percent);
            startActivity(resultIntent);
        }
    }

    private List<String> defineResponsesList(LinkedList<String> fileContent) {
        List<String> responseList = new ArrayList<>();
        for (int i = 0; i < fileContent.size() - 1; i++) {
            String[] resSplit = fileContent.get(i).split("; ");
            responseList.add(resSplit[1]);
        }
        return responseList;
    }

    private void setQuestionOnCard() {
        String questionAsk = questions.get(actualQuestion);
        int totalQuestion = questions.size();
        this.setTitleCount(actualQuestion, totalQuestion);
        String[] questionSplitAsk = questionAsk.split("; ");
        this.setQuestionContent(questionSplitAsk[0]);
    }

    private void setTitleCount(int index, int totalQuestion) {
        TextView title = findViewById(R.id.title);
        String placeholder = getString(R.string.lbl_question_title) + " " + (index + 1) + "/" + (totalQuestion - 1);
        title.setText(placeholder);
    }

    private void setQuestionContent(String question) {
        TextView questionContent = findViewById(R.id.question_content);
        questionContent.setText(question);
    }

}