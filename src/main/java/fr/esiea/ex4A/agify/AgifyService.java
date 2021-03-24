package fr.esiea.ex4A.agify;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Service
public class AgifyService {

    private final AgifyClient agifyClient;

    public AgifyService(AgifyClient agifyClient) {

        this.agifyClient = agifyClient;
    }

    public Integer getAge(String name, String countryID) throws IOException {
        Call<Map<String, String>> apiCall = agifyClient.getAge(name, countryID);

        Response<Map<String, String>> response = apiCall.execute();
        return Integer.parseInt(response.body().getOrDefault("age", "18"));
    }
}
