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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {
    Toolbar toolbar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String Useremail, Userlat, Userlong;
    Button missing,found;
    ImageView homebutton, newsfeed, notification, chat;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        title = (TextView)findViewById(R.id.title);
        title.setText("MISSING REPORTS");

        //getSupportActionBar().setTitle("option");

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor =preferences.edit();

        Useremail=preferences.getString("Username","defaultValue");

        if(Useremail.equals("defaultValue")) {
            Intent intent1 = new Intent(OptionActivity.this, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);

        }
        Toast.makeText(OptionActivity.this, Useremail, Toast.LENGTH_SHORT).show();

        missing=(Button)findViewById(R.id.missing);
        found=(Button)findViewById(R.id.found);
        homebutton = (ImageView)findViewById(R.id.homebutton);
        newsfeed = (ImageView)findViewById(R.id.newsfeed);
        notification = (ImageView)findViewById(R.id.notification);
        chat = (ImageView)findViewById(R.id.chat);

        missing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(OptionActivity.this, MissingActivity.class);
                        intent1.putExtra("Check", "lost");
                        intent1.putExtra("Useremail", Useremail);

                        startActivity(intent1);
                    }
                }
        );
        found.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(OptionActivity.this, MissingActivity.class);
                        intent1.putExtra("Check", "found");
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
        homebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(OptionActivity.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
        newsfeed.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(OptionActivity.this, Newsfeed.class);
                        intent1.putExtra("Useremail", Useremail);

                        startActivity(intent1);
                    }
                }
        );
        notification.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(OptionActivity.this, Notifications.class);
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
        chat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(OptionActivity.this, OptionActivity.class);
                        intent1.putExtra("Useremail", Useremail);
                        startActivity(intent1);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.UpdateProfile) {

            startActivity(new Intent(OptionActivity.this, LoginActivity.class));
        }
        if(item.getItemId() == R.id.myreports) {

            Intent intent1 = new Intent(OptionActivity.this, Newsfeed.class);
            intent1.putExtra("Useremail", Useremail);
            intent1.putExtra("myreports", "myreports");
            startActivity(intent1);
        }
        if(item.getItemId() == R.id.logout) {

            editor.putString("Username",null);
            //editor.putString("admin","user");
            editor.apply();

            Intent intent1 = new Intent(OptionActivity.this, LoginActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);

        }
        if(item.getItemId() == R.id.feedback) {

            startActivity(new Intent(OptionActivity.this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
