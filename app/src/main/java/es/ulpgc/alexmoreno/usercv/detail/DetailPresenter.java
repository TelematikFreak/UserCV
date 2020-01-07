package es.ulpgc.alexmoreno.usercv.detail;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;

public class DetailPresenter implements DetailContract.Presenter {

    public static String TAG = DetailPresenter.class.getSimpleName();

    private WeakReference<DetailContract.View> view;
    private DetailViewModel viewModel;
    private DetailContract.Model model;
    private DetailContract.Router router;

    public DetailPresenter(DetailState state) {
        viewModel = state;
    }

    @Override
    public void injectView(WeakReference<DetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(DetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(DetailContract.Router router) {
        this.router = router;
    }

    @Override
    public void fetchDetailData() {
        // Log.e(TAG, "fetchData()");

        // set passed state
        DetailState state = router.getDataFromPreviousScreen();
        if (state != null) {
            viewModel.userSelected = state.userSelected;
        }

        // update the view
        if (viewModel.userSelected != null) {
            model.loadDetailData(viewModel.userSelected, new DetailContract.Model.OnDetailDataFetchedCallback() {
                @Override
                public void setDetailItemList(Curriculum item) {
                    if (item != null ){
                        viewModel.curriculumFromUser = item;
                        view.get().displayData(viewModel);
                    } else {
                        view.get().showErrorGettingItem();
                    }
                }
            });
        } else {
            view.get().showErrorGettingItem();
        }
    }

    @Override
    public void deleteUser() {
        User userToDelete = viewModel.userSelected;
        Curriculum curriculumToDelete = viewModel.curriculumFromUser;
        model.deleteUser(userToDelete, curriculumToDelete, new DetailContract.Model.OnDeleteUserCallback() {
            @Override
            public void onUserDeleted() {
                router.navigateToMasterActivity();
                view.get().finishActivity();
            }
        });
    }


}
