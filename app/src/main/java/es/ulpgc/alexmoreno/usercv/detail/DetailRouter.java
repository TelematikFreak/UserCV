package es.ulpgc.alexmoreno.usercv.detail;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.alexmoreno.usercv.app.AppMediator;
import es.ulpgc.alexmoreno.usercv.master.MasterActivity;

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

    @Override
    public void navigateToMasterActivity() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MasterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
