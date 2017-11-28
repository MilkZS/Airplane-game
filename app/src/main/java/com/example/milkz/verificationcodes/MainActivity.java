package com.example.milkz.verificationcodes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // verificationCodesView codesView = new verificationCodesView(this);
        setContentView(R.layout.codesview);
    }
}
