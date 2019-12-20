package es.ulpgc.alexmoreno.usercv.newUser;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;

public class NewUserActivity
        extends AppCompatActivity implements NewUserContract.View {

    public static String TAG = NewUserActivity.class.getSimpleName();

    private NewUserContract.Presenter presenter;
    private EditText name;
    private EditText surname;
    private EditText age;
    private EditText job;
    private EditText idNumber;
    private EditText cvTitle;
    private EditText cvResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        age = findViewById(R.id.age);
        job = findViewById(R.id.job);
        idNumber = findViewById(R.id.idnumber);
        cvTitle = findViewById(R.id.cvTitle);
        cvResume = findViewById(R.id.cvResume);

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
