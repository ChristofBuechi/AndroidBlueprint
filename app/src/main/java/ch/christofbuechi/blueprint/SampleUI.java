package ch.christofbuechi.blueprint;

import ch.christofbuechi.blueprint.base.BaseUI;

/**
 * Created by Christof on 07.01.2017.
 */
public interface SampleUI extends BaseUI {
    void showErrorOnView(String text);

    void dismissDialog();
}
