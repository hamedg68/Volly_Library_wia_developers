package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.os.Environment;

/**
 * Created by hamed on 6/23/18.
 */

public class DirectoryUris {

    public static String PhotosDir = Environment
            .getExternalStorageDirectory() + "/Android/data/" + "ir.hamedandroid.volly_library_wia_developers" + "/user/";

    public static String TempImageDir = Environment
            .getExternalStorageDirectory() + "/Android/data/" + "ir.hamedandroid.volly_library_wia_developers" + "/tempImage/";

    public static String APKFileDir = Environment.getExternalStorageDirectory() + "/download/";
}
