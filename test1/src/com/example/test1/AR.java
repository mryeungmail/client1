package com.example.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.app.Application;
import android.location.Location;
import android.location.LocationManager;

public class AR extends Application {
	private final double D = 0.1; // distance
	private final double TRESHOLD = 500.0;
	private final int FOV = 60;
	private final int FOVHALF = FOV / 2;

	private List<PositionInfo> stops = null;
	private List<PositionInfo> relevant = new ArrayList<PositionInfo>();
	private List<PositionInfo> visible = new ArrayList<PositionInfo>();
	private Location currentLocation = null;
	private Location calcLocation = null;
	
	public int getFOV() {
		return FOV;
	}

	public int getFOVHALF() {
		return FOVHALF;
	}

	public double getTRESHOLD() {
		return TRESHOLD;
	}
	
	public double getD() {
		return D;
	}
	
	public List<PositionInfo> getStops() {
		return stops;
	}

	public void setStops(List<PositionInfo> stops) {
		this.stops = stops;
	}
	
	public List<PositionInfo> getRelevant() {
		return relevant;
	}
	
	public List<PositionInfo> getVisible() {
		return visible;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public Location getCalcLocation() {
		return calcLocation;
	}

	public void setCalcLocation(Location calcLocation) {
		this.calcLocation = calcLocation;
	}

	public void findRelevantStops() {
		relevant.clear();
		
		if(currentLocation != null) {
			for (PositionInfo p : stops) {
				if (p.getLat() >= (currentLocation.getLatitude() - D) &&
					p.getLat() <= (currentLocation.getLatitude() + D) &&
					p.getLon() >= (currentLocation.getLongitude() - D) &&
					p.getLon() <= (currentLocation.getLongitude() + D)) {
					relevant.add(p);
				}
			}
			
			calcLocation = currentLocation;
		}
	}
	
	public void findVisibleStops(double azimuth) {
		if(currentLocation == null || relevant.size() == 0)
			return;
		
		visible.clear();
		float[] distanceToVisible = new float[3];
		
		double currentX = currentLocation.getLongitude();
		double currentY = currentLocation.getLatitude();
		
		azimuth = 90 - azimuth;

		for(PositionInfo p : relevant) {
			double poiX = p.getLon();
			double poiY = p.getLat();
			
			double angle = Math.atan2(poiY - currentY, poiX - currentX);
			double degAngle = AngleUtil.rad2deg(angle);
			double poiAngle = AngleUtil.normalize(degAngle);

			double angleDiff = AngleUtil.angleDiff(azimuth, poiAngle);
			if(Math.abs(angleDiff) <= FOVHALF) {
				Location.distanceBetween(currentY, currentX, p.getLat(), p.getLon(), distanceToVisible);
				p.setVisible(true);
				p.setAngleDiff(angleDiff);
				p.setDistance((int) distanceToVisible[0]);
				visible.add(p);
			} else {
				p.setVisible(false);
			}
		}
	}

	public void sortByDistance() {
		Collections.sort(stops, new Comparator<PositionInfo>() {
			public int compare(PositionInfo a, PositionInfo b) {
				Location locA = new Location(LocationManager.PASSIVE_PROVIDER);
				locA.setLatitude(a.getLat());
				locA.setLongitude(a.getLon());

				Location locB = new Location(LocationManager.PASSIVE_PROVIDER);
				locB.setLatitude(b.getLat());
				locB.setLongitude(b.getLon());

				float distA = locA.distanceTo(currentLocation);
				float distB = locB.distanceTo(currentLocation);

				return Float.compare(distA, distB);
			}
		});
	}
}
