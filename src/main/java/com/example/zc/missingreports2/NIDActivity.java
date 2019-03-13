package com.example.zc.missingreports2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NIDActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String gender, skin, location, Useremail, CheckOption ;
    Button nidsubmit;

    EditText nidname, nidage, nidnumber;
    ImageView nidhomebutton, nidnewsfeed, nidnotification, nidchat,nidback;

    Toolbar toolbar;
    TextView title;

    RequestQueue requestQueue;
    //private ProgressDialog progress;
    StringRequest request;

    ArrayAdapter<CharSequence> adapterBG;
    ArrayAdapter<CharSequence> adapterAvailability;

    String Gender[] = {"Select", "Male", "Female", "Other"};
    String Location[] = {"Select", "Savar", "Shahbag", "Kalabagan", "Gulshan", "Mohakhali", "Tejgaon", "Mirpur", "Mohammadpur", "Motijheel", "Newmarket", "Uttora", "Paltan"};

    List<String> listsource = new ArrayList<>();

    List<String> listsource3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nid);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor =preferences.edit();

        title = (TextView)findViewById(R.id.title);
        title.setText("MISSING NID");

        nidback = (ImageView) findViewById(R.id.nidback);

        nidsubmit=(Button)findViewById(R.id.nidsubmit);

        nidname = (EditText)findViewById(R.id.nidname);
        nidage = (EditText)findViewById(R.id.nidage);
        nidnumber = (EditText)findViewById(R.id.nidnumber);

        nidhomebutton = (ImageView)findViewById(R.id.nid);
        nidnewsfeed = (ImageView)findViewById(R.id.nid1);
        nidnotification = (ImageView)findViewById(R.id.nid2);
        nidchat = (ImageView)findViewById(R.id.nid3);

        //progress = new ProgressDialog(this);
        CheckOption=getIntent().getStringExtra("Check");
        if(CheckOption.equals("found")){
            title.setText("FOUND NID");
        }
        Useremail = getIntent().getStringExtra("Useremail");

        Toast.makeText(NIDActivity.this, Useremail, Toast.LENGTH_SHORT).show();
        Toast.makeText(NIDActivity.this, CheckOption, Toast.LENGTH_SHORT).show();

        generateData();
        generateData2();

        Spinner spin = (Spinner) findViewById(R.id.nidspinner);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(this);


        Spinner spin3 = (Spinner) findViewById(R.id.nidlocation);
        ArrayAdapter<String> cc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Location);
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(cc);
        spin3.setOnItemSelectedListener(this);

        nidsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addpost();
            }
        });

        nidback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(NIDActivity.this, DocumentActivity.class);
                intent1.putExtra("Check", CheckOption);
                intent1.putExtra("Useremail", Useremail);
                //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });

        nidhomebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(NIDActivity.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        nidnewsfeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(NIDActivity.this, Newsfeed.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        nidnotification.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(NIDActivity.this, Notifications.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        nidchat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(NIDActivity.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
    }

    private void generateData() {
        for (int i = 0; i < 4; i++) {
            listsource.add(Gender[i]);
        }
    }

    private void generateData2() {
        for (int i = 0; i < 13; i++) {
            listsource3.add(Location[i]);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long Id) {


        Spinner spinner = (Spinner) parent;
        Spinner spinner3 = (Spinner) parent;



        if (spinner.getId() == R.id.nidspinner) {
            gender = parent.getItemAtPosition(position).toString();
            //Log.d("Blood_gp",Blood_gp);
        }

        else if (spinner3.getId() == R.id.nidlocation) {
            location = parent.getItemAtPosition(position).toString();
            //Toast.makeText(AddDonor.this,availability,Toast.LENGTH_SHORT).show();
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.UpdateProfile) {

            startActivity(new Intent(NIDActivity.this, OptionActivity.class));
        }
        if(item.getItemId() == R.id.myreports) {

            Intent intent1 = new Intent(NIDActivity.this, Newsfeed.class);
            intent1.putExtra("Useremail", Useremail);
            intent1.putExtra("myreports", "myreports");
            //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.logout) {

            editor.putString("Username",null);
            //editor.putString("admin","user");
            editor.apply();

            Intent intent1 = new Intent(NIDActivity.this, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.feedback) {

            startActivity(new Intent(NIDActivity.this, OptionActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void addpost() {
        //progress.setMessage("Please Wait......");
        //progress.show();

        //Toast.makeText(AddDonor.this,bloodgroup+gender+availability+"here we go",Toast.LENGTH_SHORT).show();
        request = new StringRequest(Request.Method.POST, UrlClass.missingnid, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {

                    // Toast.makeText(AddDonor.this,response,Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject=new JSONObject(response);
                    String testing = jsonObject.getString("count");
                    Toast.makeText(NIDActivity.this,testing+" possible match found",Toast.LENGTH_SHORT).show();
                    if(jsonObject.names().get(0).equals("success")){
                        //progress.dismiss();
                        //String user=jsonObject.getString("donor");
                        Toast.makeText(NIDActivity.this,"Your post Added "+Useremail,Toast.LENGTH_SHORT).show();
                    }
                    else if(jsonObject.names().get(0).equals("notsuccess")){
                        //progress.dismiss();
                        Toast.makeText(NIDActivity.this,"Already present ",Toast.LENGTH_SHORT).show();
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

                params.put("name",nidname.getText().toString());
                params.put("age",nidage.getText().toString());
                params.put("nid",nidnumber.getText().toString());
                params.put("email", Useremail);
                params.put("gender",gender);
                params.put("location",location);
                params.put("CheckOption",CheckOption);

                return params;
            }

        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    }

