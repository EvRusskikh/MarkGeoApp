package com.myfirstapp.markgeohelper.countoperations;

import com.myfirstapp.markgeohelper.types.mydata.line.BaseLine;
import com.myfirstapp.markgeohelper.types.mydata.line.LinePoint;
import com.myfirstapp.markgeohelper.types.Point;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CountLanePointOfLineTest {
    private CountPointOfLane countPoint;
    private BaseLine baselineSST;

    private LinePoint linePoint1;
    private LinePoint linePoint2;
    private double delta = 0.0005;
    private LinePoint lp1;
    private LinePoint lp2;

    LinePoint testPoint;
    LinePoint testPoint2;
    @Before
    public void setUp() throws Exception {
        countPoint = new CountPointOfLane();
        Point pOne = new Point();
        pOne.setX(3847.237);
        pOne.setY(695.405);
        pOne.setH(34.226);
        Point pTwo = new Point();
        pTwo.setX(3677.572);
        pTwo.setY(864.560);
        pTwo.setH(34.945);

        baselineSST = new BaseLine();
        baselineSST.setPOne(pOne);
        baselineSST.setPTwo(pTwo);

        linePoint1 = new LinePoint();
        linePoint1.setX(3741.435);
        linePoint1.setY(804.802);
        linePoint1.setH(31.988);
        linePoint2 = new LinePoint();
        linePoint2.setX(3742.204);
        linePoint2.setY(805.545);
        linePoint2.setH(34.879);

        lp1 = new LinePoint();
        lp1.setX(3741.435);
        lp1.setY(804.802);
        lp1.setH(31.988);
        lp1.setRadius(3.865);
        lp1.setGorDev(2.771);
        lp1.setPk(152.157);
        lp1.setGorPK(152.165);
        lp1.setVerDev(-2.957);

        lp2 = new LinePoint();
        lp2.setX(3742.204);
        lp2.setY(805.545);
        lp2.setH(34.879);
        lp2.setRadius(3.845);
        lp2.setGorDev(3.840);
        lp2.setPk(152.146);
        lp2.setGorPK(152.145);
        lp2.setVerDev(-0.066);

        testPoint = countPoint.getPointWithParams(baselineSST, linePoint1);
        testPoint2 = countPoint.getPointWithParams(baselineSST, linePoint2);
    }

    @Test
    public void testGetListPoints() {
        List<LinePoint> expected = new ArrayList<>();
        expected.add(lp1);
        expected.add(lp2);
        List<Point> data = new ArrayList<>();
        data.add(linePoint1);
        data.add(linePoint2);
        List<LinePoint> actual = countPoint.getListPoints(baselineSST,data);
        assertEquals(expected,actual);
    }

    @Test
    public void testGetPointWithParams() {

        assertEquals(lp1.getRadius(),testPoint.getRadius(),delta);
        assertEquals(lp1.getGorDev(),testPoint.getGorDev(),delta);
        assertEquals(lp1.getPk(),testPoint.getPk(),delta);
        assertEquals(lp1.getGorPK(),testPoint.getGorPK(),delta);
        assertEquals(lp1.getVerDev(),testPoint.getVerDev(),delta);


        assertEquals(lp2.getRadius(),testPoint2.getRadius(),delta);
        assertEquals(lp2.getGorDev(),testPoint2.getGorDev(),delta);
        assertEquals(lp2.getPk(),testPoint2.getPk(),delta);
        assertEquals(lp2.getGorPK(),testPoint2.getGorPK(),delta);
        assertEquals(lp2.getVerDev(),testPoint2.getVerDev(),delta);
    }
}