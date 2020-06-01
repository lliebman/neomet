package liebman.met.neo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetService {
    @GET("/public/collection/v1/departments")
    Call<MetFeed> getDepartment();

    @GET("/public/collection/v1/objects")
    Call<MetFeed> getObject(@Query("departmentIDs") String departmentIDs);

    @GET("/public/collection/v1/objects/{objectID}")
    Call<MetFeed> getMetadata(@Path("objectID") String objectID);
}
