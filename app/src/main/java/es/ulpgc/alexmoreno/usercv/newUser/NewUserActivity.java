package es.ulpgc.alexmoreno.usercv.newUser;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;

public class NewUserActivity
        extends AppCompatActivity implements NewUserContract.View {

    public static String TAG = NewUserActivity.class.getSimpleName();

    private NewUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        // do the setup
        NewUserScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.fetchData();
    }

    @Override
    public void injectPresenter(NewUserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(NewUserViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
    }
}
