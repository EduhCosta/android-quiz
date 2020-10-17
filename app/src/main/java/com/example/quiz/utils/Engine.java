package com.example.quiz.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.quiz.R;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private List<String> respostas = new ArrayList<>();
    private List<String> respostasCorretas;

    public Engine(List<String> respostasCorretas) {
        this.respostasCorretas = respostasCorretas;
    }

    public int showPercentage() {
        int success = 0;
        for(int i = 0; i < respostasCorretas.size() ; i++) {
            if (respostas.get(i).equals(respostasCorretas.get(i))) {
                success++;
            }
        }

        return success / respostasCorretas.size() * 100;
    }

    private void validateResponse(Context ctx, int position) {
        if (respostasCorretas.get(position).equals(respostas.get(position))) {
            Toast.makeText(ctx, ctx.getString(R.string.toast_message_success), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, R.string.toast_message_error, Toast.LENGTH_SHORT).show();
        }
    }

    public void responseQuestion(Context ctx, String feedback, int questionOrder) {
        respostas.add(questionOrder, feedback);
        this.validateResponse(ctx, questionOrder);
    }
}
