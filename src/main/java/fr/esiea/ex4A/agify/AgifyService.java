package fr.esiea.ex4A.agify;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AgifyService {

    private final AgifyClient agifyClient;
    private final Map<String, Integer> cache;

    public AgifyService(AgifyClient agifyClient) {
        this.agifyClient = agifyClient;
        this.cache = new HashMap<>();
    }

    public Integer getAgeFromApi(String name, String countryId) throws IOException {
        Call<Map<String, String>> apiCall = agifyClient.getAge(name, countryId);

        Response<Map<String, String>> response = apiCall.execute();
        Integer age = Integer.parseInt(response.body().getOrDefault("age", "18"));
        addToCache(name, countryId, age);
        return age;
    }

    public void addToCache(String name, String country, Integer age) {
        String key = (name + "_" + country).toLowerCase();
        cache.put(key, age);
    }

    public Integer getFromCache(String name, String country) {
        String key = (name + "_" + country).toLowerCase();
        return cache.containsKey(key) ? (Integer) cache.get(key) : null;
    }

    public Integer getAge(String name, String country) throws IOException {
        Integer age;
        if ((age = getFromCache(name, country)) == null)
            return getAgeFromApi(name, country);
        else
            return age;
    }
}
