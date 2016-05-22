package com.example.admin.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2016/5/22.
 */
public class ToggleView extends View {


    private Bitmap switchBackground;
    private Bitmap slideButtonBackground;
    private boolean toggleState = false;
    private boolean isSliding = false;
    private  int currentX;

    private OnToggleStateChangeListener mOnToggleStateChangeListener;
    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwitchBackgroundID(int switchBackgroundID){
        switchBackground = BitmapFactory.decodeResource(getResources(),switchBackgroundID);
    }

    public void setSlideButtonBackgroundID(int slideButtonBackgroundID){
        slideButtonBackground = BitmapFactory.decodeResource(getResources(),slideButtonBackgroundID);
    }

    public void setToggleState(boolean b){
        toggleState = b;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(switchBackground.getWidth(),switchBackground.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(switchBackground,0,0,null);

        if(isSliding){

            int left = currentX - slideButtonBackground.getWidth()/2;
            if(left < 0){
                left = 0;
            }else if(left > switchBackground.getWidth() - slideButtonBackground.getWidth()){
                left = switchBackground.getWidth() - slideButtonBackground.getWidth();
            }
            canvas.drawBitmap(slideButtonBackground,left,0,null);
        }else {
            if (toggleState){
                int left = switchBackground.getWidth() - slideButtonBackground.getWidth();
                canvas.drawBitmap(slideButtonBackground,left,0,null);
            }else {
                canvas.drawBitmap(slideButtonBackground,0,0,null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                currentX =  (int) event.getX();
                isSliding = true;
            break;
            case  MotionEvent.ACTION_UP:
                currentX = (int) event.getX();
                isSliding = false;
                boolean state = currentX > switchBackground.getWidth() /2;
                if (state != toggleState && mOnToggleStateChangeListener!= null){
                    mOnToggleStateChangeListener.onToggleStateChange(state);
                }
                toggleState = state;
                break;
            case  MotionEvent.ACTION_MOVE:
                currentX = (int) event.getX();
                break;
        }
        invalidate();
        return true;
    }
    public void setOnToggleStateChangeListener(OnToggleStateChangeListener listener){
        mOnToggleStateChangeListener = listener;
    }
}
