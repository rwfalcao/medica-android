package com.example.android.util;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 1712130027 on 19/04/2018.
 */

public class FetchJson extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL jsonUrl = new URL("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/remedios");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
