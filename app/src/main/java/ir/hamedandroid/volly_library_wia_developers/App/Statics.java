package ir.hamedandroid.volly_library_wia_developers.App;

import android.app.Activity;
import android.app.ProgressDialog;

import ir.hamedandroid.volly_library_wia_developers.Activities.JsonRequestActivity;

/**
 * Created by hamed on 3/2/18.
 */

public class Statics {

    public static ProgressDialog progressDialog(Activity activity) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);

        return progressDialog;

    }

    //    String url = "http://wiadevelopers.ir/api/volley/imageVolley.php";
    //String url = "http://api.hamedandroid.ir/";
    // String url = "http://185.94.99.41:8080/99D82073830C3B61CBDEE389332C0017DF5CB45D/F9QX776N2NTOP/PersonModel/avatar";
}
