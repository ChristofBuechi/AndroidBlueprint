package ch.christofbuechi.blueprint;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ch.christofbuechi.blueprint.base.BasePresenter;
import ch.christofbuechi.blueprint.network.NetworkService;
import ch.christofbuechi.blueprint.network.model.JavaPojo;
import ch.christofbuechi.blueprint.network.model.Station;
import ch.christofbuechi.blueprint.network.model.StationWrapper;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Christof on 07.01.2017.
 */
public class SamplePresenter extends BasePresenter<SampleUI> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    @Inject
    NetworkService networkService;


    @Inject
    public SamplePresenter() {
    }


    public void callService(Location location) {

        networkService.makeRestcall("username").subscribe(new Consumer<JavaPojo>() {
            @Override
            public void accept(JavaPojo javaPojo) throws Exception {
                Timber.d("Received Response");

            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Timber.d(throwable);
                getUi().showErrorOnView("Error happened");
                // TODO: 07.01.2017 error happened
            }
        });
    }


}
