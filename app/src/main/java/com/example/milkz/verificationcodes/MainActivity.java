package com.example.milkz.verificationcodes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button_enter = findViewById(R.id.button_enter);
        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(judgeLogin()){
                    // jump to the main activity
                }else{
                    Toast.makeText(MainActivity.this,
                            getResources().getText(R.string.verification_false_toast),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        setContentView(R.layout.login);
    }

    private boolean judgeLogin(){
        EditText userText = findViewById(R.id.verification_codes);
      //  int sUserText = userText.get();

        return false;
    }
}
