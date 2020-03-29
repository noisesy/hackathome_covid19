package org.hackathome.covid19.rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.hackathome.covid19.model.Misurazione;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestManager {
    private static final String TAG = "RestManager";

    private static RestManager instance = null;
    private static RequestQueue queue = null;
    private Context _ctx;
    protected RestManager(Context ctx) {
        _ctx = ctx;
    }
    public static RestManager getInstance(Context ctx) {
        if(instance == null) {
            instance = new RestManager(ctx);
        }
        return instance;
    }

    public class GsonRequest<T> extends Request<T> {
        private final Gson gson = new Gson();
        private final Class<T> clazz;
        private final Map<String, String> headers;
        private final Map<String, String> params;
        private final Response.Listener<T> listener;
        private final String body;

        public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,Map<String, String> params, String body,
                           Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super( method, url, errorListener );
            this.setRetryPolicy(new DefaultRetryPolicy(
                    20000,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            this.clazz = clazz;
            this.headers = headers;
            this.params = params;
            this.listener = listener;
            this.body = body;
            Log.i(TAG, url);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers != null ? headers : super.getHeaders();
        }

        @Override
        public Map<String, String> getParams() throws AuthFailureError {
            return params != null ? params : super.getParams();
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            return this.body != null ? this.body.getBytes() : super.getBody();
        }

        @Override
        public String getBodyContentType() {
            return "application/json";
        }

        @Override
        protected void deliverResponse(T response) {
            listener.onResponse(response);
        }


        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            try {
                int statusCode = response.statusCode;
                Log.i(TAG, "Response code + " + statusCode);
                String json = new String(
                        response.data,
                        HttpHeaderParser.parseCharset(response.headers));

                Log.i(TAG, "Response string: " + json);
                return Response.success(
                        gson.fromJson(json, clazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JsonSyntaxException e) {
                return Response.error(new ParseError(e));
            }
        }
    }

    private Map<String, String> getHeaders() {
        Map<String, String>  params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        return params;
    }

    public Request getMisurazioni(String id, Response.Listener<Misurazione[]> listener, Response.ErrorListener errorListener) {
        Log.i(TAG, "get Misurazioni for: " + id);
        if (queue == null)
            queue = Volley.newRequestQueue(_ctx);
        GsonRequest<Misurazione[]> request_plants = new GsonRequest<>(
                Request.Method.GET,
                "https://tomcat.milosmagicworld.eu/webapp-coronastudio/api/misurazioni/" + id,
                Misurazione[].class,
                getHeaders(),
                null,
                null,
                listener,
                errorListener);
        return queue.add(request_plants);
    }

    public Request postMisurazione(Misurazione misurazione, Response.Listener<Misurazione> listener,
                               Response.ErrorListener errorListener) {
        Log.i(TAG, "BODY " + misurazione.toString());
        if (queue == null)
            queue = Volley.newRequestQueue(_ctx);
        GsonRequest<Misurazione> request_post_misurazione = new GsonRequest<>(
                Request.Method.PUT,
                "https://tomcat.milosmagicworld.eu/webapp-coronastudio/api/misurazioni/",
                Misurazione.class,
                getHeaders(),
                null,
                misurazione.toString(),
                listener,
                errorListener);
        return queue.add(request_post_misurazione);
    }

}
