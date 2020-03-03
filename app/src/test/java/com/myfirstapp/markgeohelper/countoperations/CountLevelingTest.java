package com.myfirstapp.markgeohelper.countoperations;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class CountLevelingTest {
    CountLeveling leveling;
    double delta = 0.0003;

    @Before
    public void setUp() throws Exception {
        leveling = new CountLeveling();
    }

    @Test
    public void testGetHeight() {

        double[] benchMarks = new double[200];
        double[] benchStafs = new double[200];
        double[] pointStafs = new double[200];
        List<Double> expected = new ArrayList<>();
        List<Double> actual = new ArrayList<>();
        for (int i = 0; i < benchMarks.length; i++) {
            Random r = new Random();
            benchMarks[i] = r.nextDouble();
            benchStafs[i] = r.nextInt(7000);
            pointStafs[i] = r.nextInt(7000);
            expected.add(benchMarks[i] + benchStafs[i] / 1000 - pointStafs[i] / 1000);
            actual.add(leveling.getHeight(benchMarks[i], benchStafs[i], pointStafs[i]));
        }
        assertEquals(expected, actual);

    }

    @Test
    public void testGetStaff() {
        double bmHeight = 32.313;
        double bmStaff = 3200;
        double pointEl = 32.313;
        assertEquals(bmStaff, leveling.getStaff(bmHeight,bmStaff,pointEl),delta);
    }

    @Test
    public void testGetDeviation() {
        double[] benchMarks = new double[200];
        double[] benchStafs = new double[200];
        double[] pointHeights = new double[200];
        double[] pointStafs = new double[200];
        List<Double> expected = new ArrayList<>();
        List<Double> actual = new ArrayList<>();
        for (int i = 0; i < benchMarks.length; i++) {
            Random r = new Random();
            benchMarks[i] = r.nextDouble();
            benchStafs[i] = r.nextInt(7000);
            pointHeights[i] = r.nextDouble();
            pointStafs[i] = r.nextInt(7000);
            expected.add(pointStafs[i] / 1000 - (benchMarks[i] + benchStafs[i] / 1000 - pointHeights[i]));
            actual.add(leveling.getDeviation(benchMarks[i], benchStafs[i], pointHeights[i],pointStafs[i]));
        }
        assertEquals(expected, actual);

    }
}
