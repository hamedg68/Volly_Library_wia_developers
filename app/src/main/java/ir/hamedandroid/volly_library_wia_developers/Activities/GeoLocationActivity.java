package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ir.hamedandroid.volly_library_wia_developers.R;

public class GeoLocationActivity extends AppCompatActivity {

    EditText edt;
    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_location);

        edt = (EditText) findViewById(R.id.edt);
        btn = (Button) findViewById(R.id.btnLocation);
        txt = (TextView) findViewById(R.id.txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = edt.getText().toString();
                if (!cityName.equals("")) {
                    getLatLng(cityName, new API_CallBack() {
                        @Override
                        public void onGetData(String name, String lat, String lng) {
                            if (!name.equals("") && !lat.equals("") && !lng.equals(""))
                                txt.setText(name + "\n" + lat + "\n" + lng);
                            else
                                txt.setText("nothing to show!");
                        }
                    });
                } else
                    Toast.makeText(GeoLocationActivity.this, "enter city name", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getLatLng(String city, final API_CallBack api_callBack) {
        String URL = "https://api.opencagedata.com/geocode/v1/json?q=" + city + "&key=0917a6b048d4488987a4a16d03695189";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getData(response, api_callBack);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText("ERROR!");
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 2, 5f));
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void getData(JSONObject jsonObject, API_CallBack api_callBack) {
        String name = "", lat = "", lng = "";
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            JSONObject object = jsonArray.getJSONObject(0);
            name = object.getString("formatted");
            JSONObject object2 = object.getJSONObject("geometry");
            lat = object2.getString("lat");
            lng = object2.getString("lng");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        api_callBack.onGetData(name, lat, lng);
    }

    private interface API_CallBack {
        void onGetData(String name, String lat, String lng);
    }
}
