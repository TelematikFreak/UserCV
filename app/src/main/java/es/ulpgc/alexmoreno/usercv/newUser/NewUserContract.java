package es.ulpgc.alexmoreno.usercv.newUser;

import java.lang.ref.WeakReference;

import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;

interface NewUserContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void showErrorCreatingItem();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void onSendButtonClicked(User user, Curriculum cv);
    }

    interface Model {
        interface OnNewUserCreated {
            void changeViewToMaster(boolean error);
        }
        void createUser(User user, Curriculum cv, OnNewUserCreated callback);
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(NewUserState state);

        NewUserState getDataFromPreviousScreen();
    }
}
