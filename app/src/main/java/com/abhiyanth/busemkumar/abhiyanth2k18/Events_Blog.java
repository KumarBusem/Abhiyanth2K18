package com.abhiyanth.busemkumar.abhiyanth2k18;

/**
 * Created by Busem Kumar on 2/19/2018.
 */

public class Events_Blog {
    private String Name,id,url;

    public Events_Blog() {
    }

    public Events_Blog(String name, String id, String url) {
        this.Name = name;
        this.id = id;
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
