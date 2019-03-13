package com.example.zc.missingreports2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends Activity {

    EditText lemail,lpassword;
    Button loginBtn;
    TextView signup;
    RequestQueue requestQueue;
    private ProgressDialog progress;
    StringRequest request;
    //String URL= "http://192.168.43.185/blood/login.php";
    String email;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final int MY_PERMISSION_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpLocation();

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor =preferences.edit();

        progress = new ProgressDialog(this);

        lemail=(EditText)findViewById(R.id.dbloodgroup);
        lpassword=(EditText)findViewById(R.id.Lpassword);
        loginBtn=(Button)findViewById(R.id.Login);

        signup=(TextView) findViewById(R.id.signup);

        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if(lemail.getText().toString().equals("admin") && lpassword.getText().toString().equals("admin")) {
                            editor.putString("admin","admin");
                            editor.apply();
                            Intent i=new Intent(LoginActivity.this,OptionActivity.class);
                            i.putExtra("admin", "admin");


                            startActivity(i);

                        }
                        else {*/
                            validator();
                        //}

                    }
                }
        );

        signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(LoginActivity.this, SignUpForm.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
    }

    public void validator() {
        //Toast.makeText(LoginActivity.this, "inside validator", Toast.LENGTH_SHORT).show();
        progress.setMessage("Please Wait......");
        progress.show();
        request = new StringRequest(Request.Method.POST, UrlClass.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginActivity.this, "resp="+response, Toast.LENGTH_SHORT).show();
                try {
                    Toast.makeText(LoginActivity.this, "resp="+response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){
                        Toast.makeText(LoginActivity.this,"login successful ",Toast.LENGTH_SHORT).show();

                        email=lemail.getText().toString();
                        editor.putString("Username",email);
                        editor.putString("admin","user");
                        editor.apply();
                        //Tokens.email=email;

                        progress.dismiss();
                        Intent myIntent2 = new Intent(LoginActivity.this, OptionActivity.class);
                        //myIntent2.putExtra("Useremail",email );
                        myIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(myIntent2);

                    }
                    else if(jsonObject.names().get(0).equals("failure")){

                        Toast.makeText(LoginActivity.this,"login not successful ",Toast.LENGTH_SHORT).show();
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

                params.put("email", lemail.getText().toString());
                params.put("password", lpassword.getText().toString());

                return params;
            }

        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpLocation() {
        if(android.support.v4.app.ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && android.support.v4.app.ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            requestRuntimePermission();
        }
    }

    private void requestRuntimePermission()
    {
        android.support.v4.app.ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        },MY_PERMISSION_REQUEST_CODE);
    }
}
