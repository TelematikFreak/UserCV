package es.ulpgc.alexmoreno.usercv.detail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;

public class DetailActivity
        extends AppCompatActivity implements DetailContract.View {

    public static String TAG = DetailActivity.class.getSimpleName();

    private DetailContract.Presenter presenter;

    private Button deleteUserButton;
    private TextView name;
    private TextView surname;
    private TextView age;
    private TextView job;
    private TextView idNumber;
    private TextView cvTitle;
    private TextView cvResume;

    private TextView rate; // examen


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        deleteUserButton = findViewById(R.id.deleteButton);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        age = findViewById(R.id.age);
        job = findViewById(R.id.job);
        idNumber = findViewById(R.id.idCard);
        cvTitle = findViewById(R.id.cvTitle);
        cvResume = findViewById(R.id.cvResume);

        rate = findViewById(R.id.rate); // examen


        androidx.appcompat.app.ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(true);
        }

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteUser();
            }
        });


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
        // deal with the data
        if (viewModel.userSelected != null) {
            name.setText(viewModel.userSelected.getName());
            surname.setText(viewModel.userSelected.getSurname());
            int ageFromViewModel = viewModel.userSelected.getAge();
            String ageString = Integer.toString(ageFromViewModel);
            age.setText(ageString);
            job.setText(viewModel.userSelected.getJob());
            idNumber.setText(viewModel.userSelected.getIdNumber());
            rate.setText(String.valueOf(viewModel.userSelected.getRate())); // examen
            cvTitle.setText(viewModel.curriculumFromUser.getTitle());
            cvResume.setText(viewModel.curriculumFromUser.getCvDescription());
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

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
