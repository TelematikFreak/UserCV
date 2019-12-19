package es.ulpgc.alexmoreno.usercv.detail;

import java.lang.ref.WeakReference;

import es.ulpgc.alexmoreno.usercv.data.User;

interface DetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(DetailViewModel viewModel);

        void showErrorGettingItem();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchDetailData();
    }

    interface Model {
        interface OnDetailDataFetchedCallback {
            void setDetailItemList(User item);
        }
        void loadDetailData(OnDetailDataFetchedCallback callback);
    }

    interface Router {
        DetailState getDataFromPreviousScreen();
    }
}
