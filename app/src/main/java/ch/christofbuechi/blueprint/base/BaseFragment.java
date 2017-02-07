package ch.christofbuechi.blueprint.base;

import android.support.v4.app.Fragment;

import ch.christofbuechi.blueprint.base.dagger.AppComponent;
import timber.log.Timber;

/**
 * Created by Christof on 07.01.2017.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseUI {
    public BaseFragment() {
        injectMembers(AppComponent.Holder.getAppComponent());
        Timber.tag(this.getClass().getSimpleName());
    }

    public abstract void injectMembers(AppComponent appComponent);

}
