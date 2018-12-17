package com.abhiyanth.busemkumar.abhiyanth2k18;

/**
 * Created by Busem Kumar on 2/19/2018.
 */

public class Notification_Blog {
    String Description,Time,Title;

    public Notification_Blog(String description, String time, String title) {
        Description = description;
        Time = time;
        Title = title;
    }

    public Notification_Blog() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
