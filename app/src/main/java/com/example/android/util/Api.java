package com.example.android.util;

import com.example.android.models.Medication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://mobile-aceite.tcu.gov.br/mapa-da-saude/";

    @GET("rest/remedios?quantidade=300")
    Call<List<Medication>> getMeds();
}
