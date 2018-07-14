package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import ir.hamedandroid.volly_library_wia_developers.App.AppController;
import ir.hamedandroid.volly_library_wia_developers.App.Statics;
import ir.hamedandroid.volly_library_wia_developers.R;

public class StringRequestActivity extends AppCompatActivity {

    private CardView crdHoldResult;
    Button btnSendStringRequest, btnSendStringRequest2;
    private TextView txtResult;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_request);


        crdHoldResult = findViewById(R.id.crdHoldResult);
        btnSendStringRequest = findViewById(R.id.btnSendStringRequest);
        btnSendStringRequest2 = findViewById(R.id.btnSendStringRequest2);
        txtResult = findViewById(R.id.txtResult);

        dialog = Statics.progressDialog(this);

        crdHoldResult.setVisibility(View.GONE);


        btnSendStringRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendStringRequest();
            }
        });

        btnSendStringRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendStringRequest2();
            }
        });

    }


    private void sendStringRequest() {//دریافت متن از سرور

        String url = "http://wiadevelopers.ir/api/volley/stringRequest.php";

        dialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                crdHoldResult.setVisibility(View.VISIBLE);
                txtResult.setText(response);
                dialog.dismiss();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("parohk", error.toString());
                Toast.makeText(StringRequestActivity.this, "error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        };


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);

        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    //advanced
    private void sendStringRequest2() {
        String url = "http://wiadevelopers.ir/api/volley/stringVolley.php";
        dialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                crdHoldResult.setVisibility(View.VISIBLE);
                txtResult.setText(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(StringRequestActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        };


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name", "hamed");
                return map;
            }
        };


        AppController.getInstance().addToRequestQueue(stringRequest);
    }


}
