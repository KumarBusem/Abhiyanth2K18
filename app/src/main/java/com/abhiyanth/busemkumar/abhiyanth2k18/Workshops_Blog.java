package com.abhiyanth.busemkumar.abhiyanth2k18;

/**
 * Created by Busem Kumar on 2/19/2018.
 */

public class Workshops_Blog {
    String workshopname,workshoptitle,workshopurl;

    public Workshops_Blog() {
    }

    public Workshops_Blog(String workshopname, String workshoptitle, String workshopurl) {
        this.workshopname = workshopname;
        this.workshoptitle = workshoptitle;
        this.workshopurl = workshopurl;
    }

    public String getWorkshopname() {
        return workshopname;
    }

    public void setWorkshopname(String workshopname) {
        this.workshopname = workshopname;
    }

    public String getWorkshoptitle() {
        return workshoptitle;
    }

    public void setWorkshoptitle(String workshoptitle) {
        this.workshoptitle = workshoptitle;
    }

    public String getWorkshopurl() {
        return workshopurl;
    }

    public void setWorkshopurl(String workshopurl) {
        this.workshopurl = workshopurl;
    }
}
