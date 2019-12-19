package es.ulpgc.alexmoreno.usercv.master;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.alexmoreno.usercv.R;
import es.ulpgc.alexmoreno.usercv.data.User;

public class MasterActivity
        extends AppCompatActivity implements MasterContract.View {

    public static String TAG = MasterActivity.class.getSimpleName();

    private MasterContract.Presenter presenter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        // do the setup
        listView = findViewById(R.id.master_list);
        MasterScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        Log.d(TAG, "onResume: fetchMasterItemsData");
        presenter.fetchMasterItemsData();
    }

    @Override
    public void injectPresenter(MasterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(MasterViewModel viewModel) {
        Log.d(TAG, "displayData: viewModel: " + viewModel.masterItemList);
        listView.setAdapter(new MasterAdapter(this, viewModel.masterItemList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User item = (User) v.getTag();
                presenter.selectMasterItemData(item);
            }
        }));
    }
}
