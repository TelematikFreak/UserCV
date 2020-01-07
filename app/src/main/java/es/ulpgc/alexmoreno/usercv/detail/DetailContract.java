package es.ulpgc.alexmoreno.usercv.detail;

import java.lang.ref.WeakReference;

import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;

interface DetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(DetailViewModel viewModel);

        void showErrorGettingItem();

        void finishActivity();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchDetailData();

        void deleteUser();
    }

    interface Model {
        interface OnDetailDataFetchedCallback {
            void setDetailItemList(Curriculum item);
        }

        interface OnDeleteUserCallback {
            void onUserDeleted();
        }

        void loadDetailData(User item, OnDetailDataFetchedCallback callback);

        void deleteUser(User userToDelete, Curriculum curriculumToDelete, OnDeleteUserCallback callback);
    }

    interface Router {
        DetailState getDataFromPreviousScreen();
    }
}
