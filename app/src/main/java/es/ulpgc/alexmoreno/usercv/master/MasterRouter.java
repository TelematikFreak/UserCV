package es.ulpgc.alexmoreno.usercv.master;

import android.content.Intent;
import android.content.Context;

import es.ulpgc.alexmoreno.usercv.app.AppMediator;
import es.ulpgc.alexmoreno.usercv.data.User;
import es.ulpgc.alexmoreno.usercv.detail.DetailActivity;
import es.ulpgc.alexmoreno.usercv.detail.DetailState;

public class MasterRouter implements MasterContract.Router {

    public static String TAG = MasterRouter.class.getSimpleName();

    private AppMediator mediator;

    public MasterRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(DetailState state) {
        mediator.setDetailState(state);
    }

}
