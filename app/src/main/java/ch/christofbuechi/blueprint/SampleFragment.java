package ch.christofbuechi.blueprint;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import ch.christofbuechi.blueprint.base.BaseFragment;
import ch.christofbuechi.blueprint.base.dagger.AppComponent;
import ch.christofbuechi.blueprint.network.model.Station;

/**
 * Created by Christof on 07.01.2017.
 */
public class SampleFragment extends BaseFragment<SamplePresenter> implements SampleUI {


    @Inject
    SamplePresenter presenter;
    private TextView sampleTextView;
    private AlertDialog dialog;

    public static SampleFragment newInstance() {
        Bundle args = new Bundle();

        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setUi(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sampleTextView = (TextView) view.findViewById(R.id.textview);

    }







    @Override
    public void injectMembers(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void showErrorOnView(String errorText) {
        dialog = new AlertDialog.Builder(getActivity()).setTitle("Stations")
                .setMessage(errorText)
                .setPositiveButton(android.R.string.ok, (dialog1, id) -> dismissDialog()).create();
        dialog.show();
    }

    @Override
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
