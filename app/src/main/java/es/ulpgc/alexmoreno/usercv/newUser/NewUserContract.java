package es.ulpgc.alexmoreno.usercv.newUser;

import java.lang.ref.WeakReference;

interface NewUserContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(NewUserViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();
    }

    interface Model {
        String fetchData();
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(NewUserState state);

        NewUserState getDataFromPreviousScreen();
    }
}
