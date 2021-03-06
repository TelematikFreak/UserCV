package es.ulpgc.alexmoreno.usercv.newUser;


import android.util.Log;

import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class NewUserModel implements NewUserContract.Model {

    public static String TAG = NewUserModel.class.getSimpleName();
    private Realm realm;

    public NewUserModel() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void createUser(User user, Curriculum cv, final OnNewUserCreated callback) {
        Log.d(TAG, "createUser: " + user.getRate());
        int lastC = 0;
        int lastU = 0;
        Number lastCv = realm.where(Curriculum.class).max("id");
        Number lastUser = realm.where(User.class).max("id");
        try {
            if (lastCv != null && lastUser != null) {
                lastC = lastCv.intValue();
                lastU = lastUser.intValue();
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "createUser: ", e);
            callback.changeViewToMaster(true);
        }

        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                callback.changeViewToMaster(false);
            }
        });

        realm.beginTransaction();
        Curriculum cvToCreate = realm.createObject(Curriculum.class);
        User userToCreate = realm.createObject(User.class);
        cvToCreate.setId(lastC + 1);
        cvToCreate.setTitle(cv.getTitle());
        cvToCreate.setCvDescription(cv.getCvDescription());
        userToCreate.setId(lastU + 1);
        userToCreate.setName(user.getName());
        userToCreate.setSurname(user.getSurname());
        userToCreate.setAge(user.getAge());
        userToCreate.setJob(user.getJob());
        userToCreate.setRate(user.getRate()); // examen
        userToCreate.setIdNumber(user.getIdNumber());
        userToCreate.setCv(lastC + 1);
        realm.commitTransaction();
    }
}
