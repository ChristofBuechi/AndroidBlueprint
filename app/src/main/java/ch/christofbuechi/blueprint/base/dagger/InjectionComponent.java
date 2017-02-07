package ch.christofbuechi.blueprint.base.dagger;

import ch.christofbuechi.blueprint.SampleFragment;
import ch.christofbuechi.blueprint.MainActivity;

/**
 * Created by Christof on 07.01.2017.
 */
public interface InjectionComponent {
    void inject(SampleFragment sampleFragment);

    void inject(MainActivity mainActivity);
}
