package com.example.zn.practicewriting.Board;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class DrawTool {
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private ImageView mImageView;

    public void setOwner(ImageView iv){
        mImageView = iv;
    }

    public void initPaint(){
        mPaint = new Paint();
        mPaint.setStrokeWidth(40);
        mPaint.setColor(Color.RED);
    }

    public View.OnTouchListener touch = new View.OnTouchListener() {

        float startX;
        float startY;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mBitmap == null) {
                        mBitmap = Bitmap.createBitmap(mImageView.getWidth(),
                                mImageView.getHeight(), Bitmap.Config.ARGB_8888);
                        mCanvas = new Canvas(mBitmap);
                    }
                    startX = event.getX();
                    startY = event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float stopX = event.getX();
                    float stopY = event.getY();

                    mCanvas.drawLine(startX, startY, stopX, stopY, mPaint);
                    startX = event.getX();
                    startY = event.getY();
                    mImageView.setImageBitmap(mBitmap);
                    break;
                default:
                    break;
            }
            return true;
        }
    };
    protected void resumeCanvas() {
        if (mBitmap != null) {
            mBitmap = Bitmap.createBitmap(mImageView.getWidth(),
                    mImageView.getHeight(), Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
            mImageView.setImageBitmap(mBitmap);
        }
    }
}
