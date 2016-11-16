package com.david.practice.event;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);//设置为空心
        paint.setAntiAlias(false);//抗锯齿
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawCircle(500,1000,400,paint);
        canvas.drawLine(500,1000,500,650,paint);
        super.onDraw(canvas);
    }

    public DrawPoint matchTagPoint(DrawPoint startPoint,float radius,double corners){
        double radians = Math.toRadians(corners);//将角度转换为弧度
        double sinValue = Math.sin(radians);
        double cosValue = Math.cos(radians);
        double lengthX = sinValue*radius;//sin对应边的长度
        double lengthY = cosValue*radius;
        DrawPoint endPoint = new DrawPoint();
        endPoint.setX(startPoint.x+lengthX);
        endPoint.setY(startPoint.y-lengthY);
        return endPoint;
    }

    public class DrawPoint{
        double x;
        double y;

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            DrawPoint drawPoint = new DrawPoint();
            drawPoint.setX(500);
            drawPoint.setY(1000);
            DrawPoint endPoint = matchTagPoint(drawPoint,500,6*msg.what);
//            viewDrawDemo.setXandY(endPoint.getX(),endPoint.getY());
            super.handleMessage(msg);
        }
    };
}
