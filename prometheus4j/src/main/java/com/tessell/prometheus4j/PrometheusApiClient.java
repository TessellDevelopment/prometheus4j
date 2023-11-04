package com.tessell.prometheus4j;

import com.tessell.prometheus4j.models.KeyValResponse;
import com.tessell.prometheus4j.models.MatrixResponse;
import com.tessell.prometheus4j.models.VectorResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class PrometheusApiClient {
    public String baseUrl;

    private Retrofit retrofit;
    private PrometheusRest service;

    public PrometheusApiClient(String baseUrl) {
        this.baseUrl = baseUrl;

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PrometheusRest.class);
    }

    public VectorResponse query(String query) throws IOException {
        return service.query(query, null, null).execute().body();
    }

    public String generateQueryApiUrl(String query) {
        Call<VectorResponse> call = service.query(query, null, null);
        return call.request().url().toString();
    }

    public VectorResponse query(String query, String time) throws IOException {
        return service.query(query, time, null).execute().body();
    }

    public String generateQueryApiUrl(String query, String time) {
        Call<VectorResponse> call = service.query(query, time, null);
        return call.request().url().toString();
    }

    public VectorResponse query(String query, String time, String timeout) throws IOException {
        return service.query(query, time, timeout).execute().body();
    }

    public String generateQueryApiUrl(String query, String time, String timeout) {
        Call<VectorResponse> call = service.query(query, time, timeout);
        return call.request().url().toString();
    }

    public MatrixResponse queryRange(String query, String start, String end, String step, String timeout) throws IOException {
        return service.queryRange(query, start, end, step, timeout).execute().body();
    }

    public String generateQueryRangeApiUrl(String query, String start, String end, String step, String timeout) {
        Call<MatrixResponse> call = service.queryRange(query, start, end, step, timeout);
        return call.request().url().toString();
    }

    public KeyValResponse findSeries(String match, String start, String end) throws IOException {
        return service.findSeries(match, start, end).execute().body();
    }

    public String generateFindSeriesApiUrl(String match, String start, String end) {
        Call<KeyValResponse> call = service.findSeries(match, start, end);
        return call.request().url().toString();
    }
}
