package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

import ir.hamedandroid.volly_library_wia_developers.App.AppController;
import ir.hamedandroid.volly_library_wia_developers.App.CustomRequest;
import ir.hamedandroid.volly_library_wia_developers.App.Statics;
import ir.hamedandroid.volly_library_wia_developers.Models.WebsiteInfo;
import ir.hamedandroid.volly_library_wia_developers.R;

public class CustomRequestActivity extends AppCompatActivity {

    Button btnCustomRequest;
    private TextView txtResult;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_request);

        btnCustomRequest = findViewById(R.id.btnSendCustomRequest);
        txtResult = findViewById(R.id.txtResult);

        dialog = Statics.progressDialog(this);

        btnCustomRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCustomRequest();
            }
        });
    }

    private void sendCustomRequest() {
        String url = "http://wiadevelopers.ir/api/volley/customRequest.php";
        dialog.show();
        Response.Listener<WebsiteInfo> listener = new Response.Listener<WebsiteInfo>() {
            @Override
            public void onResponse(WebsiteInfo response) {

                dialog.dismiss();

                if (response != null)
                    txtResult.setText(response.toString());
                else
                    txtResult.setText("Nothing!!!");
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Toast.makeText(CustomRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        CustomRequest request = new CustomRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", "1");
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(request);
    }
}
