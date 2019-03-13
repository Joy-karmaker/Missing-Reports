package com.example.zc.missingreports2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomCardview2 {

    private String notification_by;
    private String notification_to;
    private String post_id;
    private String name;
    private String time;
    private String match_percentage;

    public CustomCardview2( String notification_by, String notification_to, String post_id, String name, String match_percentage, String time) {
        this.notification_by = notification_by;
        this.notification_to = notification_to;
        this.post_id = post_id;
        this.name = name;
        this.match_percentage=match_percentage;
        this.time=time;

    }

    public String getNotification_by() {
        return notification_by;
    }
    public String getNotification_to() {
        return notification_to;
    }
    public String getPost_id() {
        return post_id;
    }
    public String getName() {
        return name;
    }
    public String getMatch_percentage() {
        return match_percentage;
    }
    public String getTime() {
        return time;
    }
}
