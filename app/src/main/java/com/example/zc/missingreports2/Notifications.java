package com.example.zc.missingreports2;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notifications extends AppCompatActivity {

    RequestQueue rq;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog progress;
    private List<CustomCardview2> listitems;
    ArrayList<String> post_id,category,match_percentage,time;
    String category1,match_percentage1,time1;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    StringRequest request;
    private JSONArray jsonArray=null;
    String Useremail,classname;
    String missingreports;
    String CheckOption;
    ImageView nfhomebutton, nfnewsfeed, nfnotification, nfchat;
    Toolbar toolbar;
    TextView title;
    View childView;
    int clickedPos;
    ImageView Nofnhomebutton, Nofnnewsfeed, Nofnnotification, Nofnchat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        toolbar = (Toolbar)findViewById(R.id.toolbar30);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor =preferences.edit();

        recyclerView = (RecyclerView)findViewById(R.id.recycle2);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progress = new ProgressDialog(this);
        listitems = new ArrayList<>();
        post_id = new ArrayList<>();
        category = new ArrayList<>();
        time = new ArrayList<>();
        match_percentage = new ArrayList<>();


        Useremail=getIntent().getStringExtra("Useremail");
        //Useremail=getIntent().getStringExtra("Useremail");

        Nofnhomebutton = (ImageView)findViewById(R.id.Nofn);
        Nofnnewsfeed = (ImageView)findViewById(R.id.Nofn1);
        Nofnnotification = (ImageView)findViewById(R.id.Nofn2);
        Nofnchat = (ImageView)findViewById(R.id.Nofn3);

        jsonwork();

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                childView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(childView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting RecyclerView Clicked Item value.
                    clickedPos = Recyclerview.getChildAdapterPosition(childView);
                    // Showing RecyclerView Clicked Item value using Toast.
                    //Toast.makeText(getApplicationContext(), ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition),
                    //  Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Notifications.this, category.get(clickedPos), Toast.LENGTH_SHORT).show();
                    category1=category.get(clickedPos);
                    time1=time.get(clickedPos);
                    match_percentage1=match_percentage.get(clickedPos);
                    if(category1.equals("person")){
                        Intent intent = new Intent(getApplicationContext(), MatchingResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));

                        startActivity(intent);
                    }
                    else if(category1.equals("nid")){
                        Intent intent = new Intent(getApplicationContext(), NidResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));
                        startActivity(intent);
                    }
                    else if(category1.equals("passport")){
                        Intent intent = new Intent(getApplicationContext(), PassportResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));

                        startActivity(intent);
                    }
                    else if(category1.equals("mobile")){
                        Intent intent = new Intent(getApplicationContext(), MobileResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));

                        startActivity(intent);
                    }
                    else if(category1.equals("others")){
                        Intent intent = new Intent(getApplicationContext(), OthersResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));

                        startActivity(intent);
                    }
                    else if(category1.equals("bike")){
                        Intent intent = new Intent(getApplicationContext(), VehicleResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));

                        startActivity(intent);
                    }
                    else if(category1.equals("wallet")){
                        Intent intent = new Intent(getApplicationContext(), WalletResult.class);
                        intent.putExtra("Useremail", Useremail);
                        intent.putExtra("post_id", post_id.get(clickedPos));
                        intent.putExtra("time", time.get(clickedPos));
                        intent.putExtra("match_percentage", match_percentage.get(clickedPos));

                        startActivity(intent);
                    }



                    //intent.putExtra("email", email.get(clickedPos));

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

        Nofnhomebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Notifications.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
        Nofnnewsfeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Notifications.this, Newsfeed.class);
                        intent1.putExtra("Useremail", Useremail);

                        startActivity(intent1);
                    }
                }
        );
        Nofnnotification.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Notifications.this, Notifications.class);
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
        Nofnchat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Notifications.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
    }

    public void jsonwork() {
        progress.setMessage("Please Wait......");
        progress.show();
        //String url= "http://192.168.43.223/blood/show.php";
        request=new StringRequest(Request.Method.POST, UrlClass.notification, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try {
                    Toast.makeText(Notifications.this, "res="+response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject=new JSONObject(response);
                    jsonArray=jsonObject.getJSONArray("result");
                    if(jsonArray.length()==0)
                    {
                        Toast.makeText(Notifications.this, "no data", Toast.LENGTH_SHORT).show();
                    } else {
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject res=jsonArray.getJSONObject(i);
                            //String splitter[]=res.getString("Rdate").split("/");
                            //int year=Integer.parseInt(splitter[0]);
                            //int month=Integer.parseInt(splitter[1]);
                            //int day=Integer.parseInt(splitter[2]);
                            //if(jsonArray.length()>0)

                            //{
                            //post_id=res.getString("email");
                            String a = res.getString("time");
                            String b = res.getString("match_percentage");
                            Toast.makeText(Notifications.this, "time"+a+" match b ="+b+"b", Toast.LENGTH_SHORT).show();

                            post_id.add(res.getString("post_id"));
                            category.add(res.getString("category"));

                            time.add(res.getString("time"));
                            match_percentage.add(res.getString("match_percentage"));
                            //category=res.getString("category");


                              //Toast.makeText(Notifications.this, category+" previous", Toast.LENGTH_SHORT).show();
                            // Toast.makeText(BloodRequestWall.this, "email is ="+res.getString("Remail"), Toast.LENGTH_SHORT).show();
                            CustomCardview2 listItem1 = new CustomCardview2(res.getString("notification_by"), res.getString("notification_to"), res.getString("post_id"), res.getString("name"),res.getString("match_percentage"),res.getString("time"));

                            listitems.add(listItem1);
                            //Toast.makeText(BloodRequestWall.this, "email is ="+res.getString("Remail"), Toast.LENGTH_SHORT).show();
                            //email.add(res.getString("email"));

                        }
                        adapter = new RecyclerAdapter2(listitems, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        //progress.dismiss();

                    }

                } catch (JSONException e) {
                    progress.dismiss();
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", Useremail);
                return params;
            }
        };


        rq= Volley.newRequestQueue(this);
        rq.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.UpdateProfile) {

            startActivity(new Intent(Notifications.this, LoginActivity.class));
        }
        if(item.getItemId() == R.id.myreports) {

            Intent intent1 = new Intent(Notifications.this, Newsfeed.class);
            intent1.putExtra("Useremail", Useremail);
            intent1.putExtra("myreports", "myreports");
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.logout) {

            editor.putString("Username",null);
            //editor.putString("admin","user");
            editor.apply();

            Intent intent1 = new Intent(Notifications.this, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.feedback) {

            startActivity(new Intent(Notifications.this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
