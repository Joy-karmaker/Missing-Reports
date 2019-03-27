package com.example.zc.missingreports2;

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

public class Newsfeed extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    RequestQueue rq;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog progress;
    private List<CustomCardview> listitems;
    ArrayList<String> email;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    View childView;
    int clickedPos;
    StringRequest request;
    private JSONArray jsonArray=null;
    String Useremail;
    String missingreports;
    ImageView nfhomebutton, nfnewsfeed, nfnotification, nfchat;
    Toolbar toolbar;
    String CheckOption;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        progress = new ProgressDialog(this);

        Useremail=getIntent().getStringExtra("Useremail");
        CheckOption=getIntent().getStringExtra("Check");
        //post_id=getIntent().getStringExtra("post_id");

        nfhomebutton = (ImageView)findViewById(R.id.nf);
        nfnewsfeed = (ImageView)findViewById(R.id.nf1);
        nfnotification = (ImageView)findViewById(R.id.nf2);
        nfchat = (ImageView)findViewById(R.id.nf3);

        missingreports=getIntent().getStringExtra("myreports");
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor =preferences.edit();

        title = (TextView)findViewById(R.id.title);

        if(missingreports==null){
            missingreports="dvdfvd";
            title.setText("NEWS FEED");

        }
        else{
            title.setText("MY REPORTS");
        }
        Toast.makeText(Newsfeed.this, "res="+missingreports, Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();
        email = new ArrayList<>();
        jsonwork();

        /*recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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

                    Intent intent = new Intent(getApplicationContext(), RequestDescription.class);
                    intent.putExtra("email", email.get(clickedPos));
                    //intent.putExtra("email", post_id);
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/

        nfhomebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Newsfeed.this, OptionActivity.class);
                        intent1.putExtra("Check", CheckOption);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        nfnewsfeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Newsfeed.this, Newsfeed.class);
                        intent1.putExtra("Useremail", Useremail);
                        intent1.putExtra("Check", CheckOption);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        nfnotification.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Newsfeed.this, Notifications.class);
                        intent1.putExtra("Useremail", Useremail);
                        intent1.putExtra("Check", CheckOption);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        nfchat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Newsfeed.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        intent1.putExtra("Check", CheckOption);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
    }

    public void jsonwork() {
        progress.setMessage("Please Wait......");
        progress.show();
        //String url= "http://192.168.43.223/blood/show.php";
        request=new StringRequest(Request.Method.POST, UrlClass.newsfeed, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                try {
                    //Toast.makeText(Newsfeed.this, "res="+response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject=new JSONObject(response);
                    jsonArray=jsonObject.getJSONArray("result");
                    if(jsonArray.length()==0)
                    {
                        Toast.makeText(Newsfeed.this, "no data", Toast.LENGTH_SHORT).show();
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
                            //if(Useremail == res.getString("email") || Useremail == res.getString("name")) {
                                //  Toast.makeText(BloodRequestWall.this, "reached inside jsonobject", Toast.LENGTH_SHORT).show();
                                // Toast.makeText(BloodRequestWall.this, "email is ="+res.getString("Remail"), Toast.LENGTH_SHORT).show();
                                CustomCardview listItem = new CustomCardview(res.getString("name"), res.getString("pname"), res.getString("age"), res.getString("gender"), res.getString("height"), res.getString("phone"), res.getString("skin"),
                                        res.getString("location"), res.getString("option")


                                );
                                listitems.add(listItem);
                                //Toast.makeText(BloodRequestWall.this, "email is ="+res.getString("Remail"), Toast.LENGTH_SHORT).show();
                                //email.add(res.getString("email"));
                            //}
                        }
                        adapter = new RecyclerAdapter(listitems, getApplicationContext());
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
            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", Useremail);
                params.put("myreports", missingreports);

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

            startActivity(new Intent(Newsfeed.this, LoginActivity.class));
        }
        if(item.getItemId() == R.id.myreports) {

            Intent intent1 = new Intent(Newsfeed.this, Newsfeed.class);
            intent1.putExtra("Useremail", Useremail);
            intent1.putExtra("myreports", "myreports");
            //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.logout) {

            editor.putString("Username",null);
            //editor.putString("admin","user");
            editor.apply();

            Intent intent1 = new Intent(Newsfeed.this, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.feedback) {

            startActivity(new Intent(Newsfeed.this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
