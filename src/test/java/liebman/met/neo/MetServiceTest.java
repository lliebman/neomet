package liebman.met.neo;

import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class MetServiceTest {

    @Test
    public void getDepartment() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://collectionapi.metmuseum.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MetService service = retrofit.create((MetService.class));

        //when
        Response<MetFeed> response = service.getDepartment().execute();


        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed feed = response.body();

        assertNotNull(feed);
        assertNotNull(feed.departments);
        assertNotNull(feed.departments[0].displayName);
        assertNotNull(feed.departments[0].departmentId);
    }

    @Test
    public void getObject() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://collectionapi.metmuseum.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MetService service = retrofit.create((MetService.class));

        //when
        MetFeed feed = service.getDepartment().execute().body();
        Response<MetFeed> response = service.getObject(Integer.toString(feed.departments[0].departmentId)).execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed feed2 = response.body();

        assertNotNull(feed2);
        assertNotNull(feed2.objectIDs);
    }

    @Test
    public void getMetadata() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://collectionapi.metmuseum.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MetService service = retrofit.create((MetService.class));

        //when
        MetFeed feed = service.getDepartment().execute().body();
        MetFeed feed2 = service.getObject(Integer.toString(feed.departments[0].departmentId)).execute().body();
        Response<MetFeed> response = service.getMetadata(Integer.toString(feed2.objectIDs[0])).execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed feed3 = response.body();
        assertNotNull(feed3);
        assertNotNull(feed3.objectDate);
        assertNotNull(feed3.primaryImage);
        assertNotNull(feed3.title);
    }
}