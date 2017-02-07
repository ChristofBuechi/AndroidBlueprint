package ch.christofbuechi.blueprint.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

import ch.christofbuechi.blueprint.BuildConfig;
import ch.christofbuechi.blueprint.base.dagger.AppComponent;
import timber.log.Timber;

/**
 * Created by Christof on 07.01.2017.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }

        AppComponent.Holder.initialize(this);
    }

}
