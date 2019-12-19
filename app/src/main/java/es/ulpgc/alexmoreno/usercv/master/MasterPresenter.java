package es.ulpgc.alexmoreno.usercv.master;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.alexmoreno.usercv.data.User;

public class MasterPresenter implements MasterContract.Presenter {

    public static String TAG = MasterPresenter.class.getSimpleName();

    private WeakReference<MasterContract.View> view;
    private MasterViewModel viewModel;
    private MasterContract.Model model;
    private MasterContract.Router router;

    public MasterPresenter(MasterState state) {
        viewModel = state;
    }

    @Override
    public void injectView(WeakReference<MasterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MasterContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(MasterContract.Router router) {
        this.router = router;
    }

    @Override
    public void fetchMasterItemsData() {
        model.loadMasterItems(new MasterContract.Model.OnMasterItemListFetchedCallback() {
            @Override
            public void setMasterItemList(List<User> itemList) {
                Log.d(TAG, "setMasterItemList: setting:" + itemList);
                viewModel.masterItemList = itemList;
                view.get().displayData(viewModel);
            }
        });
    }

    @Override
    public void selectMasterItemData(User item) {

    }


}
