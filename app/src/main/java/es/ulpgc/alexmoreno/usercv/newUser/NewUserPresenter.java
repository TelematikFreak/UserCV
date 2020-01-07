package es.ulpgc.alexmoreno.usercv.newUser;

import java.lang.ref.WeakReference;

import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;

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
    public void onSendButtonClicked(User user, Curriculum cv) {
        model.createUser(user, cv, new NewUserContract.Model.OnNewUserCreated() {
            @Override
            public void changeViewToMaster(boolean error) {
                if (error) {
                    view.get().showErrorCreatingItem();
                } else {
                    router.navigateToNextScreen();
                    view.get().finish();
                }
            }
        });
    }


}
