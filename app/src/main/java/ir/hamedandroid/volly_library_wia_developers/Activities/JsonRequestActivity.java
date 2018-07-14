package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ir.hamedandroid.volly_library_wia_developers.Adapters.JSONRequestAdapter;
import ir.hamedandroid.volly_library_wia_developers.App.AppController;
import ir.hamedandroid.volly_library_wia_developers.App.Statics;
import ir.hamedandroid.volly_library_wia_developers.Models.Contact;
import ir.hamedandroid.volly_library_wia_developers.R;

public class JsonRequestActivity extends AppCompatActivity {

    Button btnSendJsonObjectRequest, btnSendJsonArrayRequest, btnSendJsonObjectRequest2, btnSendJsonArrayRequest2;
    RecyclerView rclContacts;
    LinearLayoutManager manager;
    private JSONRequestAdapter JSONRequestAdapter;
    private ArrayList<Contact> contacts;
    private ProgressDialog dialog;

    private CardView card;
    private TextView txtJsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_request);

        initView();

        contacts = new ArrayList<>();

        card.setVisibility(View.GONE);

        dialog = Statics.progressDialog(this);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rclContacts.setLayoutManager(manager);

        JSONRequestAdapter = new JSONRequestAdapter(contacts, this);
        rclContacts.setAdapter(JSONRequestAdapter);


        btnSendJsonObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.clear();
                card.setVisibility(View.GONE);
                sendGSONObjectRequest();

            }
        });

        btnSendJsonArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.clear();
                card.setVisibility(View.GONE);
                sendGSONArrayRequest();

            }
        });

        btnSendJsonObjectRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONRequestAdapter.clear();
                sendGSONObjectRequest2();

            }
        });

        btnSendJsonArrayRequest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONRequestAdapter.clear();
                sendGSONArrayRequest2();
            }
        });
    }


    private void sendGSONObjectRequest() {
        String url = "http://wiadevelopers.ir/api/volley/contactJsonObject.json";
        dialog.show();


        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();

                try {
                    String name = response.getString("name");
                    String phone = response.getString("phone");
                    String email = response.getString("email");


                    contacts.add(new Contact(name, phone, email));

                    JSONRequestAdapter.addData(contacts);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JsonRequestActivity.this, "error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        };

        //for method post
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void sendGSONArrayRequest() {
        String url = "http://wiadevelopers.ir/api/volley/contactJsonArray.json";
        dialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String phone = jsonObject.getString("phone");
                        String email = jsonObject.getString("email");

                        contacts.add(new Contact(name, phone, email));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JSONRequestAdapter.addData(contacts);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(JsonRequestActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    private void sendGSONObjectRequest2() {
        String url = "http://wiadevelopers.ir/api/volley/jsonObject.php";
        dialog.show();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("siteName", "www.hamedandroid.ir");
            jsonObject.put("field", "android learning");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();
                card.setVisibility(View.VISIBLE);
                try {
                    txtJsonResult.setText(response.getString("websiteInfo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(JsonRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, listener, errorListener);
        AppController.getInstance().addToRequestQueue(request);

    }

    private void sendGSONArrayRequest2() {
        String url = "http://wiadevelopers.ir/api/volley/JsonArray.php";
        dialog.show();

        JSONArray jsonArray = new JSONArray();

        try {
            jsonArray.put(generateJsonObject("hamed", "boss"));
            jsonArray.put(generateJsonObject("ali", "coworker"));
            jsonArray.put(generateJsonObject("reza", "secretary"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();
                card.setVisibility(View.VISIBLE);
                String result = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        result += response.getJSONObject(i).getString("officerInfo") + "\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                txtJsonResult.setText(result);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(JsonRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, listener, errorListener);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    private JSONObject generateJsonObject(String name, String position) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", name);
        jsonObject.put("position", position);

        return jsonObject;
    }


    private void initView() {
        btnSendJsonObjectRequest = findViewById(R.id.btnSendJsonObjectRequest);
        btnSendJsonArrayRequest = findViewById(R.id.btnSendJsonArrayRequest);
        btnSendJsonObjectRequest2 = findViewById(R.id.btnSendJsonObjectRequest2);
        btnSendJsonArrayRequest2 = findViewById(R.id.btnSendJsonArrayRequest2);
        rclContacts = findViewById(R.id.rclContacts);
        card = findViewById(R.id.card);
        txtJsonResult = findViewById(R.id.txtJsonResult);
    }
}
