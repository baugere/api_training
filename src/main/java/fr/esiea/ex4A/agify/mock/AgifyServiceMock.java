package fr.esiea.ex4A.agify.mock;


import fr.esiea.ex4A.agify.AgifyClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class AgifyServiceMock {
    public static AgifyClient getAgifyClientMock() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AgifyClient.BASE_URI).addConverterFactory(GsonConverterFactory.create()).build();

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).build();
        BehaviorDelegate<AgifyClient> delegate = mockRetrofit.create(AgifyClient.class);

        return new AgifyClientMockImpl(delegate);
    }
}
