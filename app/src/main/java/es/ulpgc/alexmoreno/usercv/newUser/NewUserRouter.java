package es.ulpgc.alexmoreno.usercv.newUser;

import android.content.Intent;
import android.content.Context;

import es.ulpgc.alexmoreno.usercv.app.AppMediator;
import es.ulpgc.alexmoreno.usercv.master.MasterActivity;

public class NewUserRouter implements NewUserContract.Router {

    public static String TAG = NewUserRouter.class.getSimpleName();

    private AppMediator mediator;

    public NewUserRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, MasterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(NewUserState state) {
        mediator.setNewUserState(state);
    }

    @Override
    public NewUserState getDataFromPreviousScreen() {
        NewUserState state = mediator.getNewUserState();
        return state;
    }
}
