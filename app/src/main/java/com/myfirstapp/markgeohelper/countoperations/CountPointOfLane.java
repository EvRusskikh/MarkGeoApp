package com.myfirstapp.markgeohelper.countoperations;

import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;
import com.myfirstapp.markgeohelper.types.mydata.line.LinePoint;
import com.myfirstapp.markgeohelper.types.Point;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class CountPointOfLane extends Count{
    private static final int kv = 2;

    public List<LinePoint> getListPoints(BaseLine bl, List<Point> lanePoints){
        List<LinePoint> pointsWithParams = new ArrayList<>();
        for (Point p : lanePoints){
            LinePoint linePoint = (LinePoint)p;
            pointsWithParams.add(getPointWithParams(bl, linePoint));
        }
        return pointsWithParams;
    }
    public LinePoint getPointWithParams(BaseLine bl, LinePoint linePoint){
        linePoint.setRadius(getRadius(bl, linePoint));
        linePoint.setGorDev(getGorDeviation(bl, linePoint));
        linePoint.setGorPK(getGorLength(bl, linePoint));
        linePoint.setPk(getAbsoluteLength(bl, linePoint));
        linePoint.setVerDev(getVerDeviation(bl, linePoint));
        return linePoint;
    }
    private double getRadius(BaseLine bl, LinePoint p){
        Point pOne = bl.getPOne();
        Point pTwo = bl.getPTwo();
        return getRadius(pOne.getX(),pOne.getY(),pOne.getH(),
                pTwo.getX(),pTwo.getY(),pTwo.getH(),
                p.getX(),p.getY(),p.getH());
    }
    private double getGorDeviation(BaseLine bl, LinePoint p){
        Point pOne = bl.getPOne();
        Point pTwo = bl.getPTwo();
        return getGorDeviation(pOne.getX(),pOne.getY(),
                pTwo.getX(),pTwo.getY(),p.getX(),p.getY());
    }
    private double getGorLength(BaseLine bl, LinePoint p){
        Point pOne = bl.getPOne();
        Point pTwo = bl.getPTwo();
        return getGorLength(pOne.getX(),pOne.getY(),
                pTwo.getX(),pTwo.getY(),p.getX(),p.getY());
    }
    private double getAbsoluteLength(BaseLine bl, LinePoint p){
        Point pOne = bl.getPOne();
        Point pTwo = bl.getPTwo();
        return getAbsoluteLength(pOne.getX(),pOne.getY(),pOne.getH(),
                pTwo.getX(),pTwo.getY(),pTwo.getH(),
                p.getX(),p.getY(),p.getH());
    }
    private double getVerDeviation(BaseLine bl, LinePoint p){
        Point pOne = bl.getPOne();
        Point pTwo = bl.getPTwo();
        return getVerDeviation(pOne.getX(),pOne.getY(),pOne.getH(),
                pTwo.getX(),pTwo.getY(),pTwo.getH(),
                p.getX(),p.getY(),p.getH());
    }
    private double getRadius(double x1, double y1, double h1, double x2,
                             double y2, double h2, double x, double y,
                             double h) {

        double radius = Math.sqrt(Math.pow(((y1 - y) * (h2 - h1) - (h1 - h) * (y2 - y1)), kv)
                + Math.pow(((x1 - x) * (h2 - h1) - (h1 - h) * (x2 - x1)), kv)
                + Math.pow(((x1 - x) * (y2 - y1) - (y1 - y) * (x2 - x1)), kv))
                / Math.sqrt(Math.pow((x2 - x1), kv) + Math.pow((y2 - y1), kv)
                + Math.pow((h2 - h1), kv));
        return format(radius);
    }

    private double getGorDeviation(double x1, double y1, double x2,
                                   double y2, double x, double y) {
        double gorDeviation = ((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1) /
                sqrt(pow(y2 - y1, kv) + pow(x2 - x1, kv));
        return format(gorDeviation);
    }

    private double getGorLength(double x1, double y1, double x2,
                                double y2, double x, double y) {
        double gorLength = sqrt(pow(x - x1, kv) + pow(y - y1, kv) -
                pow(getGorDeviation(x1, y1, x2, y2, x, y), kv));
        return format(gorLength);
    }

    private double getAbsoluteLength(double x1, double y1, double h1, double x2,
                                     double y2, double h2, double x, double y,
                                     double h) {
        double absoluteLength = sqrt(pow(x - x1, kv) + pow(y - y1, kv) +
                pow(h - h1, kv) -
                pow(getRadius(x1, y1, h1, x2, y2, h2, x, y, h), kv));
        return format(absoluteLength);
    }
    private double getVerDeviation(double x1, double y1, double h1, double x2,
                                   double y2, double h2, double x, double y,
                                   double h){
        double verDeviation = h -(h1 + (h2-h1)*Math.sqrt(Math.pow(x-x1,kv)+Math.pow(y-y1,kv)-
                Math.pow(getGorDeviation(x1, y1, x2, y2, x, y),kv))
                /Math.sqrt(Math.pow(x-x1,kv)+Math.pow(y-y1,kv)));
        return format(verDeviation);
    }


}
