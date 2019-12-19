package es.ulpgc.alexmoreno.usercv.detail;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;

public class DetailActivity
        extends AppCompatActivity implements DetailContract.View {

    public static String TAG = DetailActivity.class.getSimpleName();

    private DetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // do the setup
        DetailScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.fetchData();
    }

    @Override
    public void injectPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(DetailViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }
}
