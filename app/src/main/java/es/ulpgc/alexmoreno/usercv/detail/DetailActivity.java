package es.ulpgc.alexmoreno.usercv.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;

public class DetailActivity
        extends AppCompatActivity implements DetailContract.View {

    public static String TAG = DetailActivity.class.getSimpleName();

    private DetailContract.Presenter presenter;

    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.name);

        // do the setup
        DetailScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.fetchDetailData();
    }

    @Override
    public void injectPresenter(DetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(DetailViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        if (viewModel.userSelected != null) {
            name.setText(viewModel.userSelected.getName());
        }
    }

    @Override
    public void showErrorGettingItem() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(R.string.errorLoading);
        alertBuilder.setMessage(R.string.errorLoadingUser);
        alertBuilder.setCancelable(true);

        alertBuilder.setNegativeButton(R.string.dismiss,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        onBackPressed();
                    }
                });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}
