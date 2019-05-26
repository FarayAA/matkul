package com.example.tester;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tester.Network.AppControl;
import com.example.tester.Network.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> mItem = new ArrayList<>();
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        listView = (ListView) findViewById(R.id.listData);

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItem);

        loadJson();
    }

    private void loadJson() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Mengambil Data");
        pd.setCancelable(true);
        pd.show();
        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.Lihat_Data, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("volley", "response : " + response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);

                                mItem.add(data.getString("username"));
                                mItem.add(data.getString("password"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        listView.setAdapter(listAdapter);
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });
        AppControl.getInstance().addToRequestQueue(reqData);

    }
}