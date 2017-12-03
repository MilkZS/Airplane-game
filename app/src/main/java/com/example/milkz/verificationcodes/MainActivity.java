package com.example.milkz.verificationcodes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

     private boolean DBG = true;
     private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button_enter = findViewById(R.id.button_enter);
        button_enter.setOnClickListener(new ButtonEnterOnClickListener());

    }


    class ButtonEnterOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (DBG) Log.d(TAG,"enter button is clicked");
            if (judgeLogin()){

            }else{

            }
        }
    }

    /**
     * compare the result of verification codes calculate
     * and text user input
     *
     * @return if it can enter the next UI
     */
    private boolean judgeLogin(){
        EditText userText = findViewById(R.id.verification_codes);
        String sUserText = userText.getText().toString();
        if (verificationCodesView.verification_codes_result != Integer.MAX_VALUE){
            String sVerificationCodes =
                    String.valueOf(verificationCodesView.verification_codes_result);
            if (sUserText.equals(sVerificationCodes)){
                return true;
            }
        }
        return false;
    }
}
