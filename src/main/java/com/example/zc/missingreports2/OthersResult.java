package com.example.zc.missingreports2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class OthersResult extends AppCompatActivity {

    TextView resultperson, itemtype, itemname, itemcolor, resultlocation,resultmatch_percentage,resulttime;
    ImageView user;
    RequestQueue rq;
    Button chat;

    StringRequest request;
    private ProgressDialog progress;
    private JSONArray jsonArray = null;
    String post_id,Useremail ;
    String match_percentage;
    String time;
    String userid1,chatwithid1,chatwithname1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_result);

        post_id=getIntent().getStringExtra("post_id");
        Useremail=getIntent().getStringExtra("Useremail");
        match_percentage=getIntent().getStringExtra("match_percentage");
        time=getIntent().getStringExtra("time");

        progress = new ProgressDialog(this);

        resultperson = (TextView) findViewById(R.id.oresultperson);
        itemtype = (TextView) findViewById(R.id.oitemtype);
        itemname = (TextView) findViewById(R.id.oitemname);
        itemcolor = (TextView) findViewById(R.id.oitemcolor);
        resultlocation = (TextView) findViewById(R.id.oresultlocation);
        resultmatch_percentage = (TextView) findViewById(R.id.match_percent3);
        // resulttime = (TextView) findViewById(R.id.time);
        chat = (Button)  findViewById(R.id.chatting);
        setter();
    }

    public void setter() {
        progress.setMessage("Please Wait......");
        progress.show();
        Toast.makeText(OthersResult.this, "post_id=" + post_id, Toast.LENGTH_SHORT).show();

        //Toast.makeText(ProfileActivity.this, "inside setter", Toast.LENGTH_SHORT).show();
        //String url= "http://192.168.43.223/blood/profileuser.php";
        // String url= "https://joykarmakerfirebase.000webhostapp.com/profileuser.php";
        request = new StringRequest(Request.Method.POST, UrlClass.matchingothers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Toast.makeText(DonorProfile.this, "re="+response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray("result");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(OthersResult.this, "no data", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject res = jsonArray.getJSONObject(i);
                            resultperson.setText("Mr. "+res.getString("pname")+" has "+res.getString("option")+" something.");
                            itemtype.setText("Which Type of Item: "+res.getString("type"));
                            itemname.setText("Item Name: "+res.getString("name"));
                            itemcolor.setText("Item Color: "+res.getString("color"));
                            resultlocation.setText("Missing/Found Location: "+res.getString("location"));
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
                                        Intent intent1 = new Intent(OthersResult.this, ChatActivity.class);
                                        intent1.putExtra("Useremail", Useremail);
                                        intent1.putExtra("userid", userid1);
                                        intent1.putExtra("chatwithid", chatwithid1);
                                        intent1.putExtra("chatwithname", chatwithname1);
                                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post_id", post_id);
                params.put("email",Useremail);
                return params;
            }

        };
        rq = Volley.newRequestQueue(this);
        rq.add(request);

    }
}
