package es.ulpgc.alexmoreno.usercv.app;

import android.app.Application;

import es.ulpgc.alexmoreno.usercv.data.User;
import es.ulpgc.alexmoreno.usercv.detail.DetailState;
import es.ulpgc.alexmoreno.usercv.master.MasterState;
import io.realm.Realm;

public class AppMediator extends Application {
    private MasterState masterState;
    private DetailState detailState;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        masterState = new MasterState();
        detailState = new DetailState();
    }

    public MasterState getMasterState() {
        return masterState;
    }

    public void setMasterState(MasterState masterState) {
        this.masterState = masterState;
    }

    public DetailState getDetailState() {
        return detailState;
    }

    public void setDetailState(DetailState detailState) {
        this.detailState = detailState;
    }

}
