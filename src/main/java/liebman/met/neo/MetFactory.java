package liebman.met.neo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MetFactory {
    public MetService getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://collectionapi.metmuseum.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MetService service = retrofit.create(MetService.class);
        return service;
    }
}
