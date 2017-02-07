package ch.christofbuechi.blueprint.network;


import ch.christofbuechi.blueprint.base.BaseService;
import ch.christofbuechi.blueprint.network.model.JavaPojo;
import ch.christofbuechi.blueprint.network.model.StationWrapper;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Christof on 07.01.2017.
 */
public class NetworkService extends BaseService {


    private final NetworkServiceDefinition networkServiceDefinition;


    public NetworkService(Retrofit retrofit, SchedulerWrapper schedulerWrapper) {
        super(retrofit, schedulerWrapper);
        this.networkServiceDefinition = createServiceDefinition(retrofit);
    }

    private NetworkServiceDefinition createServiceDefinition(Retrofit retrofit) {
        return retrofit.create(NetworkServiceDefinition.class);
    }


    public Observable<JavaPojo> makeRestcall(String user) {
       return networkServiceDefinition.makeRestcall(user)
               .flatMap((response) -> applyHTTPErrorHandling(response))
               .compose((observable) -> applySchedulers(observable));
    }
}
