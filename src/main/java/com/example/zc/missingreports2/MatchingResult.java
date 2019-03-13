package com.example.zc.missingreports2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class MatchingResult extends AppCompatActivity {
    Toolbar toolbar;

    TextView resultperson, resultname, resultage, resultgender,resultheight, resultphone,resultskin,resultlocation,title,resultmatch_percentage,resulttime;
    ImageView user,matchback;
    RequestQueue rq;
    StringRequest request1;
    Button chat;
    private ProgressDialog progress;
    private JSONArray jsonArray = null;
    String post_id1 ;
    String Useremail1;
    String match_percentage;
    String time;
    String saved;
    String userid1,chatwithid1,chatwithname1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_result);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        title = (TextView)  findViewById(R.id.title);
        title.setText("MATCHING RESULT");

        post_id1=getIntent().getStringExtra("post_id");
        match_percentage=getIntent().getStringExtra("match_percentage");
        time=getIntent().getStringExtra("time");
        //Toast.makeText(MatchingResult.this, time+" "+match_percentage, Toast.LENGTH_SHORT).show();

        progress = new ProgressDialog(this);

        resultperson = (TextView) findViewById(R.id.resultperson);
        resultname = (TextView) findViewById(R.id.resultname);
        resultage = (TextView) findViewById(R.id.resultage);
        resultgender = (TextView) findViewById(R.id.resultgender);
        resultheight = (TextView) findViewById(R.id.resultheight);
        resultphone = (TextView) findViewById(R.id.resultphone);
        resultskin = (TextView) findViewById(R.id.resultskin);
        resultlocation = (TextView) findViewById(R.id.resultlocation);
        resultmatch_percentage = (TextView) findViewById(R.id.match_percent);
       // resulttime = (TextView) findViewById(R.id.time);
        chat = (Button)  findViewById(R.id.chatting);
        Useremail1=getIntent().getStringExtra("Useremail");
        matchback = (ImageView)  findViewById(R.id.matchback);
        matchback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MatchingResult.this, Notifications.class);
                        intent1.putExtra("Useremail", Useremail1);

                        startActivity(intent1);
                    }
                }
        );

        setter();
    }

    public void setter() {
        progress.setMessage("Please Wait......");
        progress.show();
        Toast.makeText(MatchingResult.this, "post_id=" + post_id1, Toast.LENGTH_SHORT).show();

        //Toast.makeText(ProfileActivity.this, "inside setter", Toast.LENGTH_SHORT).show();
        //String url= "http://192.168.43.223/blood/profileuser.php";
        // String url= "https://joykarmakerfirebase.000webhostapp.com/profileuser.php";
        request1 = new StringRequest(Request.Method.POST, UrlClass.matchingresult, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(MatchingResult.this, "re="+response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("result");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(MatchingResult.this, "no data", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject res = jsonArray.getJSONObject(i);
                            resultperson.setText("Mr. "+res.getString("pname")+" has "+res.getString("option")+" a person.");
                            resultname.setText("Name: "+res.getString("name"));
                            resultage.setText("Age: "+res.getString("age"));
                            resultgender.setText("Gender: "+res.getString("gender"));
                            resultheight.setText("Height: "+res.getString("height")+" ft");
                            resultphone.setText("Contact No: "+res.getString("phone"));
                            resultskin.setText("Skin Color: "+res.getString("skin"));
                            resultlocation.setText("Missing/Found Location: "+res.getString("location"));
                            Toast.makeText(MatchingResult.this, time+" "+match_percentage, Toast.LENGTH_SHORT).show();
                            resultmatch_percentage.setText("Match_Percentage:  "+match_percentage+"%");
                            //resulttime.setText("Time : "+time);

                            //user.setImageBitmap("image");
                            userid1=res.getString("userid");
                            chatwithid1=res.getString("chatwithid");
                            chatwithname1=res.getString("pname");



                        }
                        chat.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent1 = new Intent(MatchingResult.this, ChatActivity.class);
                                        intent1.putExtra("Useremail", Useremail1);
                                        intent1.putExtra("userid", userid1);
                                        intent1.putExtra("chatwithid", chatwithid1);
                                        intent1.putExtra("chatwithname", chatwithname1);
                                        startActivity(intent1);
                                    }
                                }
                        );
                        progress.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchingResult.this, "error in connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post_id", post_id1);
                params.put("email",Useremail1);
                return params;
            }

        };
        rq = Volley.newRequestQueue(this);
        rq.add(request1);

    }
}
