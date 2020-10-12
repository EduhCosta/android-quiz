package com.example.quiz.utils;

import android.content.Context;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class TxtReader {
    private LinkedList<String> fileContent;

    public TxtReader(AssetManager manager) {
        this.fileContent = this.readFromAssets(manager);
    }

    private LinkedList<String> readFromAssets(AssetManager assetManager) {
        LinkedList<String> listLinhas = new LinkedList<>();

        try {
            InputStream inputStream = assetManager.open("Perguntas.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha = "";
            while(linha !=null){ // enquanto não for null..
                linha = bufferedReader.readLine();   // lemos a próxima linha...
                listLinhas.add(linha); // adicionamos a linha na LinkedList
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listLinhas;
    }


    public LinkedList<String> getFileContent() {
        return fileContent;
    }
}
