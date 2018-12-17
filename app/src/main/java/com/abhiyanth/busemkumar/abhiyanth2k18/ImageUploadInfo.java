package com.abhiyanth.busemkumar.abhiyanth2k18;

public class ImageUploadInfo {

    public String imageName;
    public String myname;
    public String imageURL;
    public String myemail;
    public String key;
    public String likecount;
    public String date;
    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String key,String date,String likecount, String myemal, String mynam, String name, String url) {
        this.myname=mynam;
        this.imageName = name;
        this.imageURL= url;
        this.myemail=myemal;
        this.date=date;
        this.key=key;
        this.likecount=likecount;

    }

    public String getDate() {
        return date;
    }

    public ImageUploadInfo(String likecount) {
        this.likecount = likecount;
    }

    public String getLikecount() {
        return likecount;
    }

    public String getKey() {
        return key;
    }

    public String getMyname() {
        return myname;
    }

    public String getMyemail() {
        return myemail;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}