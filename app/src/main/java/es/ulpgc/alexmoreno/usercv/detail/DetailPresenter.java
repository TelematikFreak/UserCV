package es.ulpgc.alexmoreno.usercv.detail;

import android.util.Log;

import java.lang.ref.WeakReference;

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
            Log.d(TAG, "fetchDetailData: state:" + state.userSelected);
        }

        // update the view
        if (viewModel.userSelected != null) {
            Log.d(TAG, "fetchDetailData: viewmodel:" + viewModel.userSelected);
            view.get().displayData(viewModel);
        }
    }


}
