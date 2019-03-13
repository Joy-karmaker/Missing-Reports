package com.example.zc.missingreports2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DocumentActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    ImageView documentback;
    TextView dtitle;
    String CheckOption, Useremail;
    ImageView nid,passport;
    ImageView documenthomebutton, documentnewsfeed, documentnotification, documentchat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor =preferences.edit();

        dtitle = (TextView)  findViewById(R.id.dtitle);

        documentback = (ImageView) findViewById(R.id.documentback);

        CheckOption=getIntent().getStringExtra("Check");
        if(CheckOption.equals("lost")){
            dtitle.setText("Missing-Document");
        }
        else{
            dtitle.setText("Found-Document");
        }
        Useremail = getIntent().getStringExtra("Useremail");
        nid = (ImageView)findViewById(R.id.nid);
        passport = (ImageView)findViewById(R.id.passport);

        documenthomebutton = (ImageView)findViewById(R.id.document);
        documentnewsfeed = (ImageView)findViewById(R.id.document1);
        documentnotification = (ImageView)findViewById(R.id.document2);
        documentchat = (ImageView)findViewById(R.id.document3);

        documenthomebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(DocumentActivity.this, MissingActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        documentnewsfeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(DocumentActivity.this, Newsfeed.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        documentnotification.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(DocumentActivity.this, Notifications.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );
        documentchat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(DocumentActivity.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }
                }
        );


        documentback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DocumentActivity.this, MissingActivity.class);
                intent1.putExtra("Check", CheckOption);
                intent1.putExtra("Useremail", Useremail);
                //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });

        nid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DocumentActivity.this, NIDActivity.class);
                intent1.putExtra("Check", CheckOption);
                intent1.putExtra("Useremail", Useremail);
                //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });
        passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DocumentActivity.this, PassportActivity.class);
                intent1.putExtra("Check", CheckOption);
                intent1.putExtra("Useremail", Useremail);
                //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.UpdateProfile) {

            startActivity(new Intent(DocumentActivity.this, LoginActivity.class));
        }
        if(item.getItemId() == R.id.myreports) {

            Intent intent1 = new Intent(DocumentActivity.this, Newsfeed.class);
            intent1.putExtra("Useremail", Useremail);
            intent1.putExtra("myreports", "myreports");
            //intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.logout) {

            editor.putString("Username",null);
            //editor.putString("admin","user");
            editor.apply();

            Intent intent1 = new Intent(DocumentActivity.this, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.feedback) {

            startActivity(new Intent(DocumentActivity.this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
    }

