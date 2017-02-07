package ch.christofbuechi.blueprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ch.christofbuechi.blueprint.base.dagger.AppComponent;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent.Holder.getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, SampleFragment.newInstance(), SampleFragment.class.getSimpleName()).commit();
    }

}
