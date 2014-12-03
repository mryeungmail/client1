package com.example.test1;

public class AngleUtil {

	public static double rad2deg(double rad) {
		return rad * 180 / Math.PI;
	}
	
	public static double normalize(double angle) {
		if(angle >= 0) {
			return angle;
		} else {
			return angle + 360;
		}
	}
	
	public static double angleDiff(double a, double b) {
		double d = a - b;
		if (d > 180)
			d -= 360;
		if (d < -180)
			d += 360;
		return d;
	}
}
