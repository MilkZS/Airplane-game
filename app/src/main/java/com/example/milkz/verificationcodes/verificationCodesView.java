package com.example.milkz.verificationcodes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import static java.lang.Math.addExact;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.subtractExact;

/**
 * Created by milkz on 17-11-27.
 */

public class verificationCodesView extends View{
    private boolean DBG = true;
    private String TAG = "verificationCodesView";
    private String codes_text = "";
    private int codes_text_size ;
    private Paint paint ;
    private Random random = new Random();

    public verificationCodesView(Context context) {
        this(context,null);
    }

    public verificationCodesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public verificationCodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public verificationCodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.verificationCodesView, defStyleAttr, 0);
        codes_text = a.getString(0);

        int default_codes_text_size = getResources().getInteger(R.integer.default_codes_text_size);
        if(DBG) Log.d(TAG,"default_codes_text_size:" + default_codes_text_size);
        //it turns sp to px and set default value.
        codes_text_size = a.getDimensionPixelSize(a.getIndex(2),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,default_codes_text_size,
                        getResources().getDisplayMetrics()));
        a.recycle(); // always remember to recycle typedArray.
        refreshCodes();// refresh codes to show first verification codes.
        paint = new Paint();
        paint.setTextSize(codes_text_size);
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawText(codes_text,45,100,paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        refreshCodes();
        return super.onTouchEvent(event);
    }


    /**
     * Refresh the text of verification codes
     * and return the right result of calculating
     *
     * @return the right result of calculating
     */
    private int refreshCodes(){
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(3);
        if(DBG) Log.d(TAG,"num1:" + num1 + " | num2:" + num2 + " | num3:" + num3);
        switch(num3){
            case 0:{
                codes_text = num1 + "+" + num2;
                postInvalidate();
                String sAlgorithm = (String) getResources().getText(R.string.add_random_symbol);
                return calculate(num1,num2,sAlgorithm);
            }
            case 1:{
                codes_text = num1 + "-" + num2;
                postInvalidate();
                String sAlgorithm = (String) getResources().getText(R.string.minus_random_symbol);
                return calculate(num1,num2,sAlgorithm);
            }
            case 2:{
                codes_text = num1 + "x" + num2;
                postInvalidate();
                String sAlgorithm = (String) getResources().getText(R.string.multiply_random_symbol);
                return calculate(num1,num2,sAlgorithm);
            }
        }
        if(DBG) Log.e(TAG,"random is wrong");
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
