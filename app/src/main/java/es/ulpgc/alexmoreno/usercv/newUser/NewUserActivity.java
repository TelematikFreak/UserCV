package es.ulpgc.alexmoreno.usercv.newUser;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;
import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;

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
    private Button sendButton;
    private String nameString;
    private String surnameString;
    private String ageString;
    private String jobString;
    private String idNumberString;
    private String cvTitleString;
    private String cvResumeString;

    // examen

    private SeekBar seekBar;
    private TextView seekBarText;
    private int rate;

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
        sendButton = findViewById(R.id.sendButton);

        // examen
        seekBarText = findViewById(R.id.rate);
        seekBar = findViewById(R.id.rateBar);
        rate = 0;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarText.setText(String.valueOf(progress));
                rate = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString().trim();
                surnameString = surname.getText().toString().trim();
                ageString = age.getText().toString().trim();
                jobString = job.getText().toString().trim();
                idNumberString = idNumber.getText().toString().trim();
                cvTitleString = cvTitle.getText().toString().trim();
                cvResumeString = cvResume.getText().toString().trim();
                int ageInt = Integer.parseInt(ageString);
                User user = new User(0,nameString,surnameString,ageInt,jobString,idNumberString,0,rate);
                Curriculum cv = new Curriculum(0,cvTitleString,cvResumeString);
                presenter.onSendButtonClicked(user, cv);
            }
        });

        androidx.appcompat.app.ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(true);
        }

        // do the setup
        NewUserScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void injectPresenter(NewUserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorCreatingItem() {
        Log.d(TAG, "showErrorCreatingItem: ");
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(R.string.errorCreating);
        alertBuilder.setMessage(R.string.errorCreatingUser);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
