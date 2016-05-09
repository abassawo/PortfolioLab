package com.example.c4q_abass.abassportfoliolab;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by c4q-Abass on 5/6/16.
 */
public class JSONReader {

    private static final int jsonFilePath = R.raw.portfolio_apps_data;
    private Context context;
    private static JSONReader instance;

    public JSONReader(Context context){
        this.context = context;
    }

    public static JSONReader getInstance(Context context){
        if(instance == null){
            instance = new JSONReader(context);
        }
        return instance;
    }

    public String readJSON() throws IOException {
        InputStream inputStream =  context.getResources().openRawResource(jsonFilePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null )
        {
            stringBuilder.append(receiveString);
            stringBuilder.append("\n");
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        return stringBuilder.toString();
    }


}
