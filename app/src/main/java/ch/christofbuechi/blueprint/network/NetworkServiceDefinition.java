package ch.christofbuechi.blueprint.network;

import ch.christofbuechi.blueprint.network.model.JavaPojo;
import ch.christofbuechi.blueprint.network.model.StationWrapper;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Christof on 07.01.2017.
 */

public interface NetworkServiceDefinition {
    @GET("path/to/rest/ressource")
    Observable<Response<JavaPojo>> makeRestcall(@Query("query") String user);
}
