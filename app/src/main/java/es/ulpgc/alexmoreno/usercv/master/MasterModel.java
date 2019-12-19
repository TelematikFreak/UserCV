package es.ulpgc.alexmoreno.usercv.master;

import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MasterModel implements MasterContract.Model {

    public static String TAG = MasterModel.class.getSimpleName();
    private Realm realm;

    public MasterModel() {
        realm = Realm.getDefaultInstance();
        final RealmResults<User> users = realm.where(User.class).findAllAsync();
        users.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> users, OrderedCollectionChangeSet changeSet) {
                if (users.isEmpty()) {
                    insertData();
                }
            }
        });
    }

    private void insertData() {
        realm.beginTransaction();
        Curriculum cv1 = realm.createObject(Curriculum.class);
        cv1.setId(0);
        cv1.setTitle("Titulo 1");
        cv1.setCvDescription("Descripcion 1");
        User user1 = realm.createObject(User.class);
        user1.setId(0);
        user1.setName("Alex");
        user1.setSurname("Moreno");
        user1.setAge(33);
        user1.setJob("Estudiante");
        user1.setIdNumber("1234A");
        user1.setCv(0);
        Curriculum cv2 = realm.createObject(Curriculum.class);
        cv2.setId(1);
        cv2.setTitle("Titulo 2");
        cv2.setCvDescription("Descripcion 2");
        User user2 = realm.createObject(User.class);
        user2.setId(1);
        user2.setName("Moni");
        user2.setSurname("Peletosa");
        user2.setAge(28);
        user2.setJob("Trabajadora");
        user2.setIdNumber("1234A");
        user2.setCv(1);
        realm.commitTransaction();
    }

    @Override
    public void loadMasterItems(final OnMasterItemListFetchedCallback callback) {
        final RealmResults<User> users = realm.where(User.class).findAllAsync();
        users.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> users, OrderedCollectionChangeSet changeSet) {
                changeSet.getInsertions();
                callback.setMasterItemList(users);
            }

        });
    }
}
