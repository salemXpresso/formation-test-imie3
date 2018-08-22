package com.imie.money.connector;

import com.google.gson.JsonSyntaxException;
import org.junit.Test;

import java.util.Currency;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CurrencyRateWsClientTest {

    @Test
    public void test_parseDownloadedRates_nominal_returnExpectedMap() {

        String baseCurrency = "EUR";
        String exampleJsonFromFixerIo =
                "{\n" +
                        "    \"success\": true,\n" +
                        "    \"timestamp\": 1519296206,\n" +
                        "    \"base\": \"" + baseCurrency + "\",\n" +
                        "    \"date\": \"2018-08-22\",\n" +
                        "    \"rates\": {\n" +
                        "        \"AUD\": 1.566015,\n" +
                        "        \"CAD\": 1.560132,\n" +
                        "        \"CHF\": 1.154727,\n" +
                        "        \"CNY\": 7.827874,\n" +
                        "        \"GBP\": 0.882047,\n" +
                        "        \"JPY\": 132.360679,\n" +
                        "        \"USD\": 1.23396\n" +
                        "    }\n" +
                        "}  ";

        CurrencyRateWsClient wsClient = new CurrencyRateWsClient();

        Map<Currency, Float> map = wsClient.parseDownloadedRates(exampleJsonFromFixerIo,
                Currency.getInstance(baseCurrency));

        assertThat(map).hasSize(8);
        assertThat(map.get(Currency.getInstance("AUD"))).isEqualTo(1.566015f);
        assertThat(map.get(Currency.getInstance("CAD"))).isEqualTo(1.560132f);
        assertThat(map.get(Currency.getInstance("USD"))).isEqualTo(1.23396f);
        assertThat(map.get(Currency.getInstance("EUR"))).isEqualTo(1f);
    }
}
