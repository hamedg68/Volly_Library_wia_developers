package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import ir.hamedandroid.volly_library_wia_developers.App.AppController;
import ir.hamedandroid.volly_library_wia_developers.App.ImagePickerUtil;
import ir.hamedandroid.volly_library_wia_developers.App.RuntimePermissionsActivity;
import ir.hamedandroid.volly_library_wia_developers.App.Statics;
import ir.hamedandroid.volly_library_wia_developers.R;

public class ImageRequestActivity extends RuntimePermissionsActivity {
    private ImageNameHolder imageNameHolders;
    private ImageView imgView;
    private Button btnSendImageRequest;
    private ProgressDialog dialog;

    private Button btnSelectImage, btnSendImageRequest2;
    private ImageView imgPicFromFile, imgPicFromNet;
    private Uri avatarUri = null;
    public static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 11;
    public static final int REQUEST_CHOOSE_IMAGE_GALLERY = 10;
    private String codedImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);

        imgView = (ImageView) findViewById(R.id.imgView);
        btnSendImageRequest = (Button) findViewById(R.id.btnSendImageRequest);
        btnSelectImage = (Button) findViewById(R.id.btnSelectImage);
        btnSendImageRequest2 = (Button) findViewById(R.id.btnSendImageRequest2);
        imgPicFromFile = (ImageView) findViewById(R.id.imgPicFromFile);
        imgPicFromNet = (ImageView) findViewById(R.id.imgPicFromNet);


        dialog = Statics.progressDialog(this);


        btnSendImageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImageRequest();
            }
        });

        btnSendImageRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codedImage.equals(""))
                    Toast.makeText(ImageRequestActivity.this, "please select an image from your gallery", Toast.LENGTH_SHORT).show();
                else
                    uploadImage();
            }
        });

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRequestActivity.super.requestAppPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_REQUEST_CODE);

            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            getImageFromGallery();
        }
    }

    @Override
    public void onPermissionsDeny(int requestCode) {
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            Toast.makeText(this, "you can not select image from your gallery", Toast.LENGTH_SHORT).show();
        }
    }

    private void getImageFromGallery() {
        ImagePickerUtil.showImagePicker(ImageRequestActivity.this, REQUEST_CHOOSE_IMAGE_GALLERY);
    }

    private void sendImageRequest() {
        String url = "http://wiadevelopers.ir/api/volley/wiadevelopers.png";
        dialog.show();

        Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                dialog.dismiss();
                imgView.setImageBitmap(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(ImageRequestActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        };

        ImageRequest request = new ImageRequest(url, listener, 0, 0, null, null, errorListener);
                                                       //default 2500 ms
        request.setRetryPolicy(new DefaultRetryPolicy(90000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request, "image");


    }

    private void uploadImage() {

        //    String url = "http://wiadevelopers.ir/api/volley/imageVolley.php";
        //String url = "http://api.hamedandroid.ir/";
        String url = "http://185.94.99.41:8080/99D82073830C3B61CBDEE389332C0017DF5CB45D/F9QX776N2NTOP/PersonModel/avatar";
        dialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //downloadImage(response);
                dialog.dismiss();
                Toast.makeText(ImageRequestActivity.this, response, Toast.LENGTH_SHORT).show();
                Log.i("reshamed",response);

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(ImageRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            public byte[] getBody() throws AuthFailureError {
//                createDirectory(new File(DirectoryUris.PhotosDir));
//                avatarUri = Uri.fromFile(new File(DirectoryUris.PhotosDir, "AV_" + System.currentTimeMillis() + ".jpg"));
                ImageNameHolder imageNameHolder = new ImageNameHolder(0, String.valueOf(avatarUri));
                File file = new File(Uri.parse(imageNameHolder.getUrl()).getPath());
                byte[] fileData = null;
                try {
                    fileData = new byte[(int) file.length()];
                    FileInputStream in = new FileInputStream(file);
                    in.read(fileData);
                    in.close();

                    //entity = new ByteArrayEntity(fileData);
                } catch (IOException e) {
                    e.printStackTrace();
                  //  IMECTCrashReport.warnReport(e, activity);

                }
                return fileData;
            }

            //            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> map = new HashMap<>();
//                map.put("image", codedImage);
//                map.put("name", "myImage");
//
//                return map;
//            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(request);
    }
    private void downloadImage(String url) {

        dialog.show();
        Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgPicFromNet.setImageBitmap(response);
                dialog.dismiss();
                Toast.makeText(ImageRequestActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        };

        ImageRequest request = new ImageRequest(url, listener, 0, 0, null, null, errorListener);
        AppController.getInstance().addToRequestQueue(request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHOOSE_IMAGE_GALLERY && resultCode == RESULT_OK) {

            Bitmap bitmap = ImagePickerUtil.getBitmap(ImageRequestActivity.this, data.getData());
           // imgPicFromFile.setImageBitmap(bitmap);
            Glide.with(this).load(data.getData()).into(imgPicFromFile);
          //  codedImage = ImagePickerUtil.getStringImage(bitmap, bitmap.getWidth());
            codedImage = ImagePickerUtil.getStringImage(bitmap, 300);

            createDirectory(new File(DirectoryUris.PhotosDir));
            avatarUri = Uri.fromFile(new File(DirectoryUris.PhotosDir, "AV_" + System.currentTimeMillis() + ".jpg"));

            if (avatarUri!=null){
           //     Glide.with(this).load(avatarUri).into(imgPicFromFile);

            }
        }
    }


private void createDirectory(File filesDir){
    if (!filesDir.isDirectory()) {
        if (!filesDir.mkdirs()) {
            Log.e("cant Create Directory", filesDir.getAbsolutePath());
        }
    }
}

    @Override
    protected void onStop() {
        super.onStop();
        //AppController.getInstance().cancelPendingRequests();
        AppController.getInstance().cancelPendingRequests("image");
    }
}
