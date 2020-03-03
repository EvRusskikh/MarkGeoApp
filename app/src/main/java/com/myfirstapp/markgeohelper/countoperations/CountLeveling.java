package com.myfirstapp.markgeohelper.countoperations;


public class CountLeveling extends Count {
    public double getHeight(double benchMarkLevel, double bmStaff, double pStaff) {

        return format(benchMarkLevel + bmStaff/1000 - pStaff/1000);
    }

    public double getStaff(double benchMarkLevel, double bmStaff, double pLevel) {
        return format((benchMarkLevel + bmStaff/1000 - pLevel)*1000);
    }

    public double getDeviation(double benchMarkLevel, double bmStaff,
                               double pLevel, double pStaff) {
        return format((pStaff/1000 - (benchMarkLevel + bmStaff/1000 - pLevel))*1000);
    }
}
