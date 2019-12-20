package es.ulpgc.alexmoreno.usercv.newUser;


public class NewUserModel implements NewUserContract.Model {

    public static String TAG = NewUserModel.class.getSimpleName();

    public NewUserModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
