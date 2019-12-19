package es.ulpgc.alexmoreno.usercv.detail;


public class DetailModel implements DetailContract.Model {

    public static String TAG = DetailModel.class.getSimpleName();

    public DetailModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
