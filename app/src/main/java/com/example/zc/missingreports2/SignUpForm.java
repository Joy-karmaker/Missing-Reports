package com.example.zc.missingreports2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpForm extends AppCompatActivity {


    EditText Sname,Scity,Sphone,Semail,Spassword,Spin ;
    Button Ssubmit, upload;
    TextView click;
    private String name,city,phone,email,password;
    private ProgressDialog progress;
    ImageView Image;
    Bitmap bitmap;
    RequestQueue requestQueue;

    // private String URL = "http://192.168.43.185/blood/Uregistration.php";
    String randomNumber;
    //EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        progress = new ProgressDialog(this);

        Sname=(EditText)findViewById(R.id.sname);
        Scity=(EditText)findViewById(R.id.scity);
        Semail=(EditText)findViewById(R.id.semail);
        Spassword=(EditText)findViewById(R.id.spassword);
        Image= (ImageView) findViewById(R.id.user);
        upload=(Button)findViewById(R.id.upload);


        Ssubmit=(Button)findViewById(R.id.Submit);

        Ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(randomNumber.equals(Spin.getText().toString())){
                registration();
                // }
                //else {
                //  Toast.makeText(SignUpForm.this, "Incorrect Pin", Toast.LENGTH_SHORT).show();
                //}

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),999);
            }
        });

    }
    /*public void mailer(){
        final String email2 = Semail.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(SignUpForm.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,UrlClass.mailer,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", email2);
                params.put("rnd", randomNumber);

                return params;
            }

        };
        requestQueue.add(stringRequest);
    }*/

    void registration() {
        progress.setMessage("Please Wait......");
        progress.show();


        final String name=Sname.getText().toString();
        final String city=Scity.getText().toString();
        final String email=Semail.getText().toString();
        final String password=Spassword.getText().toString();
        final String image = getStringImage(bitmap);
        Toast.makeText(SignUpForm.this,"current email"+email,Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.registration,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            Toast.makeText(SignUpForm.this,response,Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){

                                //String user=jsonObject.getString("donor");
                                progress.dismiss();
                                Toast.makeText(SignUpForm.this,"Registration Successful "+email,Toast.LENGTH_SHORT).show();
                            }
                            else if(jsonObject.names().get(0).equals("notsuccess")){
                                progress.dismiss();
                                Toast.makeText(SignUpForm.this,"Email Already present ",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SignUpForm.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("name", name);
                params.put("city", city);
                params.put("email", email);
                params.put("password", password);
                params.put("image",image);

                return params;
            }

        };
        requestQueue= Volley.newRequestQueue(SignUpForm.this);
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode== RESULT_OK && data !=null){
            Uri filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                Image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getStringImage(Bitmap bm){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte,Base64.DEFAULT);
        return encode;
    }


}

