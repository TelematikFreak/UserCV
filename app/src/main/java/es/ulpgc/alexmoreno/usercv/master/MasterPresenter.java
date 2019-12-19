package es.ulpgc.alexmoreno.usercv.master;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.alexmoreno.usercv.data.User;
import es.ulpgc.alexmoreno.usercv.detail.DetailState;

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
                viewModel.masterItemList = itemList;
                view.get().displayData(viewModel);
            }
        });
    }

    @Override
    public void selectMasterItemData(User item) {
        DetailState state = new DetailState();
        state.userSelected = item;
        router.passDataToNextScreen(state);
        router.navigateToNextScreen();
    }
}
