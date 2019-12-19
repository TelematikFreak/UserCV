package es.ulpgc.alexmoreno.usercv.master;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.alexmoreno.usercv.data.User;
import es.ulpgc.alexmoreno.usercv.detail.DetailState;

interface MasterContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(MasterViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchMasterItemsData();

        void selectMasterItemData(User item);
    }

    interface Model {
        interface OnMasterItemListFetchedCallback {
            void setMasterItemList(List<User> itemList);
        }
        void loadMasterItems(OnMasterItemListFetchedCallback callback);
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(DetailState state);

    }
}
