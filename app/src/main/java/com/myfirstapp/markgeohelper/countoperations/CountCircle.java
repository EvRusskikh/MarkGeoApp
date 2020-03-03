package com.myfirstapp.markgeohelper.countoperations;

import android.util.Log;

import com.myfirstapp.markgeohelper.exceptions.count_exception.CountRoundCenterException;
import com.myfirstapp.markgeohelper.types.mydata.circle.Circle;
import com.myfirstapp.markgeohelper.types.Point;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundCenter;
import com.myfirstapp.markgeohelper.types.mydata.circle.RoundPoint;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CountCircle extends Count{
    public Circle getCircle(List<RoundPoint> points){
        Circle circle = new Circle();
        List<RoundCenter> centers = getCenters(points);
        RoundCenter midCenter = getMidCenter(centers);
        List<Double> radiuses = getRadiuses(points,midCenter);
        double midRadius = getRound(radiuses);
        setRadiuses(points,midCenter);
        circle.setPoints(points);
        circle.setCenters(centers);
        circle.setMidCenter(midCenter);
        circle.setMidRadius(midRadius);

        return circle;
    }
    public List<Double> getRadiuses(List<RoundPoint> points, RoundCenter center){
        List<Double> radiuses = new ArrayList<>();
        for(RoundPoint point : points){
            radiuses.add(getRadius(center,point));
        }
        return radiuses;
    }
    public double getRadius(RoundCenter center, RoundPoint point){
        return getRadius(center.getX(),center.getY(),point.getX(),point.getY());
    }
    public RoundCenter getMidCenter(List<RoundCenter> centers){
            double x = getRoundX(centers);
            double y = getRoundY(centers);
            RoundCenter center = new RoundCenter();
            center.setX(x);
            center.setY(y);
            return center;
    }
    public List<RoundCenter> getCenters(List<RoundPoint> points){
        List<RoundCenter> centres = new ArrayList<>();
        for (int i = 0; i < points.size()-2; i++){
            for (int j = i+1; j < points.size()-1;j++){
                for (int k = j+1; k < points.size(); k++){
                    try{
                        RoundCenter center = getCenter(points.get(i),points.get(j),points.get(k));
                        centres.add(center);
                    } catch (CountRoundCenterException ex){
                        Log.d(TAG, "Coordinates are perpendikular",ex);
                    }
                }
            }
        }
        return centres;
    }
    public RoundCenter getCenter(Point p1, Point p2, Point p3){
        RoundCenter center = new RoundCenter();
        double[] centerCoords = countCenter(p1.getX(),p1.getY(),
                p2.getX(),p2.getY(),p3.getX(),p3.getY());
        center.setX(centerCoords[0]);
        center.setY(centerCoords[1]);
        return center;

    }
    public void setRadiuses(List<RoundPoint> points, RoundCenter center){
        for(RoundPoint point : points){
            point.setRadius(getRadius(center,point));
        }
    }
    private double getRoundX(List<RoundCenter> centers){
        double countX = 0;
        int count = 0;
        for (RoundCenter center : centers){
            countX += center.getX();
            count++;
        }
        return count == 0 ? 0 : format(countX/count);
    }
    private double getRoundY(List<RoundCenter> centers){
        double countY = 0;
        int count = 0;
        for (RoundCenter center : centers){
            countY += center.getY();
            count++;
        }
        return count == 0 ? 0 : format(countY/count);
    }
    private double[] countCenter(double x1, double y1, double x2, double y2, double x3, double y3){
        double A = x2 - x1;
        double B = y2 - y1;
        double C = x3 - x1;
        double D = y3 - y1;
        double E = A * (x1 + x2) + B * (y1 + y2);
        double F = C * (x1 + x3) + D * (y1 + y3);
        double G = 2 * (A * (y3 - y2) - B * (x3 - x2));
        double[] coords = new double[2];
        if (G == 0){
            throw new CountRoundCenterException("Точки перпендикулярны");
        }else {
            coords[0] = format((D * E - B * F) / G);
            coords[1] = format((A * F - C * E) / G);
        }
        return coords;
    }
    private double getRadius(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
