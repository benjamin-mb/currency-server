package com.arka.currency_service.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {

    private final WebClient webClient;

    public CurrencyService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Cacheable(value = "exchangeCurrencies", key = "COP")
    public Mono<Map<String,Double>> getCurrency (){
        return webClient.get()
                .uri("/e5711abb6046eaab04868f49/latest/COP")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    Map<String,Double> rates= new HashMap<>();
                    json.get("conversion_rates").fields()
                            .forEachRemaining(entry-> rates.put(entry.getKey(), entry.getValue().doubleValue()));
                    return rates;
                });

    }

    public Mono<Integer> getPriceToCurrency (String currency, Integer originalPrice){

       return getCurrency().map(rate->{
            if (!rate.containsKey(currency)){throw  new IllegalArgumentException("currency not found");}
            Double currencyToChange =rate.get(currency);
            Double calculation= originalPrice*currencyToChange;
             return (int) Math.round(calculation);
        });

    }

}
