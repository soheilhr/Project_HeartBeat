package com.example.admin.heartbeat;//package com.example.androidsurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import java.lang.Math;

public class radarGraph extends SurfaceView {

    private SurfaceHolder surfaceHolder;
    private Bitmap bmpAxes;

    public radarGraph(Context context) {
        super(context);
        init();
    }

    public radarGraph(Context context,
                      AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public radarGraph(Context context,
                      AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        surfaceHolder = getHolder();
        bmpAxes = BitmapFactory.decodeResource(getResources(),
                R.drawable.diagram);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmpAxes,1200,1200,true);
        bmpAxes.recycle();
        bmpAxes = scaledBitmap;
        surfaceHolder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas(null);
                drawBackground(canvas);
                drawPlot(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub

            }});
    }

    protected void drawBackground(Canvas canvas) {
        // Set background color
        canvas.drawColor(Color.WHITE);
        // Set background graphic
        // Get start position for background image
        int x_origin = getWidth()/2 - bmpAxes.getWidth()/2;
        int y_origin = getHeight()/2 - bmpAxes.getHeight()/2;
        canvas.drawBitmap(bmpAxes,x_origin, y_origin, null);
    }

    protected void drawPlot(Canvas canvas) {
        // Get score TODO: Setup scoring framework
        double[] score;
        score = new double[6];
        score[0]=475;score[1]=280;score[2]=250;score[3]=220;score[4]=270;score[5]=250;

        int x_centre = (int) getWidth()/2;
        int y_centre = (int) getHeight()/2;
        // Calculate vertices
        int vertices[][];
        vertices = new int[6][2];

        double deg30 = Math.PI/6;
        double deg60 = Math.PI/3;

        vertices[0][0]= x_centre + (int)( score[0] * (Math.cos(deg30+1*deg60)) );
        vertices[0][1]= y_centre + (int)( score[0] * (Math.sin(deg30+1*deg60)) );
        vertices[1][0]= x_centre + (int)( score[1] * (Math.cos(deg30+0*deg60)) );
        vertices[1][1]= y_centre + (int)( score[1] * (Math.sin(deg30+0*deg60)) );
        vertices[2][0]= x_centre + (int)( score[2] * (Math.cos(deg30+5*deg60)) );
        vertices[2][1]= y_centre + (int)( score[2] * (Math.sin(deg30+5*deg60)) );
        vertices[3][0]= x_centre + (int)( score[3] * (Math.cos(deg30+4*deg60)) );
        vertices[3][1]= y_centre + (int)( score[3] * (Math.sin(deg30+4*deg60)) );
        vertices[4][0]= x_centre + (int)( score[4] * (Math.cos(deg30+3*deg60)) );
        vertices[4][1]= y_centre + (int)( score[4] * (Math.sin(deg30+3*deg60)) );
        vertices[5][0]= x_centre + (int)( score[5] * (Math.cos(deg30+2*deg60)) );
        vertices[5][1]= y_centre + (int)( score[5] * (Math.sin(deg30+2*deg60)) );
        // Draw score shape
        Paint fill = new Paint();
        fill.setColor(getResources().getColor(R.color.navy_blue));
        fill.setStyle(Style.FILL);
        Path wallpath = new Path();
        wallpath.reset();
        wallpath.moveTo(vertices[0][0],vertices[0][1]);
        wallpath.lineTo(vertices[1][0],vertices[1][1]);
        wallpath.lineTo(vertices[2][0],vertices[2][1]);
        wallpath.lineTo(vertices[3][0],vertices[3][1]);
        wallpath.lineTo(vertices[4][0],vertices[4][1]);
        wallpath.lineTo(vertices[5][0],vertices[5][1]);
        wallpath.close();
        canvas.drawPath(wallpath,fill);
        // Draw score outline
        //Paint paint = new Paint();
        //paint.setStyle(Style.STROKE);
        //paint.setColor(getResources().getColor(R.color.navyblue));
        //paint.setStrokeWidth(10);
        //canvas.drawLine(vertices[0][0],vertices[0][1],vertices[1][0],vertices[1][1], paint);
        //canvas.drawLine(vertices[1][0],vertices[1][1],vertices[2][0],vertices[2][1], paint);
        //canvas.drawLine(vertices[2][0],vertices[2][1],vertices[3][0],vertices[3][1], paint);
        //canvas.drawLine(vertices[3][0],vertices[3][1],vertices[4][0],vertices[4][1], paint);
        //canvas.drawLine(vertices[4][0],vertices[4][1],vertices[5][0],vertices[5][1], paint);
        //canvas.drawLine(vertices[5][0],vertices[5][1],vertices[0][0],vertices[0][1], paint);
    }

}



//http://www.jayway.com/2012/07/03/creating-custom-android-views-part-2-how-padding-works-and-how-to-draw-your-own-content/