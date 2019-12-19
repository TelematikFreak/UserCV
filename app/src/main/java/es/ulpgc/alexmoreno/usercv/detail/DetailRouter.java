package es.ulpgc.alexmoreno.usercv.detail;

import es.ulpgc.alexmoreno.usercv.app.AppMediator;

public class DetailRouter implements DetailContract.Router {

    public static String TAG = DetailRouter.class.getSimpleName();

    private AppMediator mediator;

    public DetailRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public DetailState getDataFromPreviousScreen() {
        DetailState state = mediator.getDetailState();
        return state;
    }
}
