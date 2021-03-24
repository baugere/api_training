package fr.esiea.ex4A;

import fr.esiea.ex4A.agify.AgifyClient;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    AgifyClient agifyClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AgifyClient.BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

        return retrofit.create(AgifyClient.class);
    }

}
