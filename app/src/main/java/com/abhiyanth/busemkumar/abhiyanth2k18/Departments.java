package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Departments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        this.setTitle("Department Sites");
    }
    public void nav_web1(View view){
        startActivity(new Intent(this,workshops_1.class));}


    public void nav_web2(View view){
        startActivity(new Intent(this,workshops_2.class));}
    public void nav_web3(View view){
        startActivity(new Intent(this,workshops_3.class));}
    public void nav_web4(View view){
        startActivity(new Intent(this,workshops_4.class));}
    public void nav_web5(View view){
        startActivity(new Intent(this,workshops_5.class));}
    public void nav_web6(View view){
        startActivity(new Intent(this,workshops_6.class));}
}
