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
}
