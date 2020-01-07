package es.ulpgc.alexmoreno.usercv.detail;


import es.ulpgc.alexmoreno.usercv.data.Curriculum;
import es.ulpgc.alexmoreno.usercv.data.User;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class DetailModel implements DetailContract.Model {

    public static String TAG = DetailModel.class.getSimpleName();
    private Realm realm;

    public DetailModel() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadDetailData(User item, final OnDetailDataFetchedCallback callback) {
        final RealmResults<Curriculum> resumes = realm.where(Curriculum.class)
                .equalTo("id",item.getCv()).findAllAsync();
        resumes.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Curriculum>>() {
            @Override
            public void onChange(RealmResults<Curriculum> resumes, OrderedCollectionChangeSet changeSet) {
                changeSet.getInsertions();
                if (!resumes.isEmpty()) {
                    callback.setDetailItemList(resumes.first());
                }
            }
        });
    }

    @Override
    public void deleteUser(final User userToDelete, final Curriculum curriculumToDelete, final OnDeleteUserCallback callback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> usuario = realm.where(User.class)
                        .equalTo("id", userToDelete.getId()).findAll();
                RealmResults<Curriculum> curriculum = realm.where(Curriculum.class)
                        .equalTo("id", curriculumToDelete.getId()).findAll();

                curriculum.deleteAllFromRealm();
                usuario.deleteAllFromRealm();
                callback.onUserDeleted();
            }
        });
    }
}
