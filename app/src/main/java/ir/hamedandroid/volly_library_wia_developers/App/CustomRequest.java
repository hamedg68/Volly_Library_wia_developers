package ir.hamedandroid.volly_library_wia_developers.App;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ir.hamedandroid.volly_library_wia_developers.Models.WebsiteInfo;

/**
 * Created by hamed on 3/2/18.
 */

public class CustomRequest extends Request<WebsiteInfo> {

    private Response.Listener<WebsiteInfo> listener;


    public CustomRequest(int method, String url, Response.Listener<WebsiteInfo> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        this.listener = listener;
    }

    @Override
    protected Response<WebsiteInfo> parseNetworkResponse(NetworkResponse response) {

        WebsiteInfo websiteInfo = new WebsiteInfo();
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            JSONObject jsonObject = new JSONObject(json);
            websiteInfo.setWebSite(jsonObject.getString("website"));
            websiteInfo.setTitle(jsonObject.getString("title"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            websiteInfo = null;
        } catch (JSONException e) {
            e.printStackTrace();
            websiteInfo = null;
        }

        return Response.success(websiteInfo, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(WebsiteInfo response) {
        listener.onResponse(response);
    }
}
