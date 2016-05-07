package com.example.c4q_abass.abassportfoliolab;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by c4q-Abass on 5/6/16.
 */
public class AppLoader {

    private static final String TAG = "AppLoader";
    private Context context;
    private static AppLoader instance;

    public AppLoader(Context context){
        this.context = context;
    }

    public static AppLoader getInstance(Context context){
        if(instance == null){
            instance = new AppLoader(context);
        }
        return instance;
    }

    public String loadAppFile() throws IOException {
        InputStream inputStream =  context.getResources().openRawResource(R.raw.portfolio_apps_data);
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
