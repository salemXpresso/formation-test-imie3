package com.imie.money.connector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CurrencyRateWsClient {

    public Map<Currency, Float> downloadLatestCurrencyRates(String apiKey, Currency baseCurrency) throws IOException{

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://data.fixer.io/api/latest?base=" + baseCurrency.getCurrencyCode() +"&access_key=" + apiKey)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String jsonRaw = response.body().string();
        return parseDownloadedRates(jsonRaw, baseCurrency);
    }

    protected Map<Currency, Float> parseDownloadedRates(String data, Currency baseCurrency) {

        Map<Currency, Float> ratesMap = new HashMap<>();

        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> rates = jsonObject.getAsJsonObject("rates").entrySet();

        for(Map.Entry<String,JsonElement> aRate : rates){
            ratesMap.put(Currency.getInstance(aRate.getKey()), aRate.getValue().getAsFloat());
        }
        ratesMap.put(baseCurrency, 1f);
        return ratesMap;
    }
}
