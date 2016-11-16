package com.david.practice.event;

import android.app.Activity;
import android.os.Bundle;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MyViewPractice extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_practice);
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
}
