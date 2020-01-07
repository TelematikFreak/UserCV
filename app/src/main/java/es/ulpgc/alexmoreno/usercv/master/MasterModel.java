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
        cv1.setCvDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic typesetting," +
                " remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.");
        User user1 = realm.createObject(User.class);
        user1.setId(0);
        user1.setName("Manuel");
        user1.setSurname("Salvador");
        user1.setAge(33);
        user1.setJob("Estudiante");
        user1.setIdNumber("1234A");
        user1.setCv(0);
        Curriculum cv2 = realm.createObject(Curriculum.class);
        cv2.setId(1);
        cv2.setTitle("Titulo 2");
        cv2.setCvDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic typesetting," +
                " remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing" +
                " software like Aldus PageMaker including versions of Lorem Ipsum.");
        User user2 = realm.createObject(User.class);
        user2.setId(1);
        user2.setName("Prueba");
        user2.setSurname("Apellido");
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
