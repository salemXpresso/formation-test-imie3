package com.imie.money.connector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyRateWsClient {

    public Map<String, Float> downloadLatestCurrencyRates(String apiKey) throws IOException{

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://data.fixer.io/api/latest?access_key=" + apiKey)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String jsonRaw = response.body().string();
        return parseDownloadedRates(jsonRaw);
    }

    protected Map<String, Float> parseDownloadedRates(String data) {

        Map<String, Float> ratesMap = new HashMap<>();

        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> rates = jsonObject.getAsJsonObject("rates").entrySet();

        for(Map.Entry<String,JsonElement> aRate : rates){
            ratesMap.put(aRate.getKey(), aRate.getValue().getAsFloat());
        }
        return ratesMap;
    }
}
