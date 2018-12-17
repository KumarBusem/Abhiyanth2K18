package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class reg_popup extends Dialog implements android.view.View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg_popup2);
    }
    public Activity activity;

    public reg_popup() {
        super(null);
    }
    public reg_popup(Activity activity) {
        super(activity);
        this.activity = activity;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
        dismiss();
    }
}
