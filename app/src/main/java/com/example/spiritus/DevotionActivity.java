package com.example.spiritus;


import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;

public class DevotionActivity extends AppCompatActivity {

    List<DataAdapter> DataAdapterClassList;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    JsonArrayRequest jsonArrayRequest ;

    ArrayList<String> SubjectNames;

    RequestQueue requestQueue ;

    String HTTP_SERVER_URL = "https://christian-project.herokuapp.com/api/todaysword/today/";

    View ChildView ;

    int RecyclerViewClickedItemPOS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setTitle("Devotion");
        setContentView(R.layout.devotion);

        DataAdapterClassList = new ArrayList<>();

        SubjectNames = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        // JSON data web call function call from here.
        JSON_WEB_CALL();

        //RecyclerView Item click listener code starts from here.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(DevotionActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });


            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked item value.
                    RecyclerViewClickedItemPOS = Recyclerview.getChildAdapterPosition(ChildView);

                    //Printing RecyclerView Clicked item clicked value using Toast Message.
                   // Toast.makeText(DevotionActivity.this, SubjectNames.get(RecyclerViewClickedItemPOS), Toast.LENGTH_LONG).show();

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    public void JSON_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(HTTP_SERVER_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setId(json.getInt("id"));

                GetDataAdapter2.setTitle(json.getString("title"));

                //Adding subject name here to show on click event.
                SubjectNames.add(json.getString("title"));

                GetDataAdapter2.setDescription(json.getString("description"));

                GetDataAdapter2.setDate(json.getString("date"));

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }

            DataAdapterClassList.add(GetDataAdapter2);

        }

        progressBar.setVisibility(View.GONE);

        recyclerViewadapter = new RecyclerViewAdapter(DataAdapterClassList, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }

    public void Comment(View view  ) {
        String mPhoneNumber = "+254704333650";
        mPhoneNumber = mPhoneNumber.replaceAll("", "").replaceAll(" ", "").replaceAll("-","");
        String mMessage = "Comment";
        String mSendToWhatsApp = "https://wa.me/" + mPhoneNumber + "?text="+mMessage;
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(
                        mSendToWhatsApp
                )));

    }
    public  void  Share (View view  ) {
        String shareBody ="God's timing in our lives is perfect, and we will enjoy life much more if we believe that. He knows the exact right time to do the things we have requested of Him. Dont waste your time being upset about something that only God can change. If He withholds your desire for the time being, thank Him that He knows best. Time is a gift dont waste it being upset! Prayer Starter: Father God, please help me to trust Your timing in my life. Help me not to frustrate myself by trying to do what only You can do. In Jesus' name, Amen.";
        Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);

        sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, "Today's Devotion");
        startActivity(shareIntent);

    }


    public void Donate(View view) {
        Intent a = new Intent(getApplicationContext(),Donation.class);
        startActivity(a);
        //Toast.makeText(this, "Donated", Toast.LENGTH_LONG).show();
    }

    public void History(View view) {
        Intent a = new Intent(getApplicationContext(), HistoryActivity.class);
        startActivity(a);
        //Toast.makeText(this, "Donated", Toast.LENGTH_LONG).show();
    }
}


