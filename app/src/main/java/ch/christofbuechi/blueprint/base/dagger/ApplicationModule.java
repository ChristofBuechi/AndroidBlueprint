package ch.christofbuechi.blueprint.base.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import ch.christofbuechi.blueprint.BuildConfig;
import ch.christofbuechi.blueprint.network.SchedulerWrapper;
import ch.christofbuechi.blueprint.network.NetworkService;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Christof on 07.01.2017.
 */

@Module
public class ApplicationModule {
    private Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    public Context provideContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    @NonNull
    public Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss ZZZ").create();
    }

    @Provides
    @Singleton
    @NonNull
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @Singleton
    @NonNull
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("url to webservice")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public SchedulerWrapper providesSchedulerWrapper() {
        return new SchedulerWrapper();
    }


    @Provides
    @Singleton
    public NetworkService provideNetworkService(Retrofit retrofit, SchedulerWrapper schedulerWrapper) {
        return createNetworkService(retrofit, schedulerWrapper);
    }

    protected NetworkService createNetworkService(final Retrofit retrofit, final SchedulerWrapper schedulerWrapper) {
        return new NetworkService(retrofit, schedulerWrapper);
    }
}
