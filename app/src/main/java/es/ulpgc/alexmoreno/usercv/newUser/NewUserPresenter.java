package es.ulpgc.alexmoreno.usercv.newUser;

import java.lang.ref.WeakReference;

public class NewUserPresenter implements NewUserContract.Presenter {

    public static String TAG = NewUserPresenter.class.getSimpleName();

    private WeakReference<NewUserContract.View> view;
    private NewUserViewModel viewModel;
    private NewUserContract.Model model;
    private NewUserContract.Router router;

    public NewUserPresenter(NewUserState state) {
        viewModel = state;
    }

    @Override
    public void injectView(WeakReference<NewUserContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(NewUserContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(NewUserContract.Router router) {
        this.router = router;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // set passed state
        /*NewUserState state = router.getDataFromPreviousScreen();
        if (state != null) {
            viewModel.data = state.data;
        }

        if (viewModel.data == null) {
            // call the model
            String data = model.fetchData();

            // set initial state
            viewModel.data = data;
        }*/

        // update the view
        //view.get().displayData(viewModel);

    }


}
