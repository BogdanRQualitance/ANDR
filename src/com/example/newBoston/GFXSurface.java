package com.example.newBoston;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * @author brata@tremend.ro
 * @since 25.03.2013
 */
public class GFXSurface extends Activity implements View.OnTouchListener {
    MyBringBackSurface ourSurfaceView;
    float x;
    float y;
    float sY;
    float sX;
    float fX;
    float fY;
    float dY;
    float dX;
    float aniX;
    float aniY;
    float scaledX;
    float scaledY;
    Bitmap test;
    Bitmap plus;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        sY = 0;
        sX = 0;
        fX = 0;
        fY = 0;
        dY = 0;
        dX = 0;
        aniX = 0;
        aniY = 0;
        scaledX = 0;
        scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause () {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume () {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch (View view, MotionEvent motionEvent) {
        x = motionEvent.getX();
        y = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sX = motionEvent.getX();
                sY = motionEvent.getY();
                dX = 0;
                dY = 0;
                aniX = 0;
                aniY = 0;
                scaledX = 0;
                scaledY = 0;
                fX = 0;
                fY = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = motionEvent.getX();
                fY = motionEvent.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX / 30;
                scaledY = dY / 30;
                x = y = 0;
                break;
        }

        return true;
    }

    class MyBringBackSurface extends SurfaceView implements Runnable {
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface (Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause () {
            isRunning = false;
            while (true) {
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                break;
            }
            ourThread = null;
        }

        public void resume () {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run () {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid()) continue;
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2, 2, 150);
                if (x != 0 && y != 0) {
                    canvas.drawBitmap(test, x - (test.getWidth() / 2), y - (test.getHeight() / 2), null);
                }
                if (sX != 0 && sY != 0) {
                    canvas.drawBitmap(plus, sX - (plus.getWidth() / 2), sY - (plus.getHeight() / 2), null);
                }
                if (fX != 0 && fY != 0) {
                    canvas.drawBitmap(test, fX - (test.getWidth() / 2) - aniX, fY - (test.getHeight() / 2) - aniY, null);
                    canvas.drawBitmap(plus, fX - (plus.getWidth() / 2), fY - (plus.getHeight() / 2), null);

                }
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
