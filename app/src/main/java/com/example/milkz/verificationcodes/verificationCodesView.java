package com.example.milkz.verificationcodes;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.random;
import static java.lang.Math.subtractExact;

/**
 * Created by milkz on 17-11-27.
 */

public class verificationCodesView extends View{
    private boolean DBG = true;
    private String TAG = "verificationCodesView";
    private String codes_text;


    public verificationCodesView(Context context) {
        this(context,null);
    }

    public verificationCodesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public verificationCodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public verificationCodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.verificationCodesView, defStyleAttr, 0);
        codes_text = a.getString(0);

    }

    /**
     * Refresh the text of verification codes
     * and return the right result of calculating
     *
     * @return the right result of calculating
     */
    private int refreshCodes(){
        int num1 = (int) (random() % 10);
        int num2 = (int) (random() % 10);
        int num3 = (int) (random() % 3);
        switch(num3){
            case 0:{
                codes_text = "" + num1 + R.string.add_random + num2;
                postInvalidate();
                String sAlgorithm = "" + R.string.add_random_symbol;
                return calculate(num1,num2,sAlgorithm);
            }
            case 1:{
                codes_text = "" + num1 + R.string.minus_random + num2;
                postInvalidate();
                String sAlgorithm = "" + R.string.minus_random_symbol;
                return calculate(num1,num2,sAlgorithm);
            }
            case 2:{
                codes_text = "" + num1 + R.string.multiply_random + num2;
                postInvalidate();
                String sAlgorithm = "" + R.string.multiply_random_symbol;
                return calculate(num1,num2,sAlgorithm);
            }
        }
        return 0;
    }

    /**
     * Arithmetic of two numbers .
     *
     * @param num1 first number to calculate
     * @param num2 second number to calculate
     * @param sAlgorithm symbol of algorithm
     * @return result of calculate two numbers
     */
    private int calculate(int num1, int num2, String sAlgorithm){
        int sum = 0;
        switch(sAlgorithm){
            case "+" :{
                sum = addExact(num1,num2);
            }break;
            case "-" :{
                sum = subtractExact(num1,num2);
            }break;
            case "x" :{
                sum = multiplyExact(num1,num2);
            }break;
        }
        return sum;
    }
}
