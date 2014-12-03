package com.example.test1;

import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.test1.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class CameraActivity extends Activity implements LocationListener, SensorEventListener {
	private final float OUT_OF_SCREEN = Float.MIN_VALUE;
	
	private FrameLayout frameLayout;
	private CameraView cameraView;
	private PoiView poiView;
	private LocationManager mLocationManager;
	private SensorManager mSensorManager;
	private AR mDistance;
	
	private List<PositionInfo> visible = null;
	private List<PositionInfo> relevant = null;
	private double lat = 0, lon = 0;
	private float[] accelerometerValues = new float[3];
	private float[] magneticFieldValues = new float[3];
	private float[] orientation = new float[3];
	private float filteredOrientation = Float.NaN;
	boolean firstFix = true;
	
	private Paint mPaint;
	private Bitmap bitmap;
	private Bitmap mIconBitmap;
	private int mapSize = 400;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mDistance = ((AR)getApplicationContext());
        
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
    }
	
	@SuppressWarnings("deprecation")
	private void LayoutSetup() {
		Camera camera = Camera.open();
		
	    frameLayout = new FrameLayout(this);
	    frameLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    
	    cameraView = new CameraView(this, camera);
	    frameLayout.addView(cameraView);
	    
	    poiView = new PoiView(this);
	    frameLayout.addView(poiView);
	    
	    setContentView(frameLayout);
	}
	
	protected void onPause() {
		super.onPause();
		
		if(cameraView != null) {
			cameraView.onPause();
			cameraView = null;
		}
		
		mLocationManager.removeUpdates(this);
		mSensorManager.unregisterListener(this);
	}
	
	protected void onResume() {
		super.onResume();
		
		LayoutSetup();
		
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
		
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);

        Location lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastKnownLocation == null) {
        	lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        	if(lastKnownLocation != null) {
        		updateLocation(lastKnownLocation, true);
        	}
        } else {
        	updateLocation(lastKnownLocation, true);
        }
	}
	
	private void updateLocation(Location location, boolean updateRelevant) {
		mDistance.setCurrentLocation(location);
		
		if(updateRelevant) {
			mDistance.findRelevantStops();
			relevant = mDistance.getRelevant();
		}
		
		lat = location.getLatitude();
		lon = location.getLongitude();
		
		String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%s,%s&size=%dx%d&sensor=false&maptype=roadmap&zoom=13",
		Double.toString(lat).replace(',', '.'), Double.toString(lon).replace(',', '.'), mapSize, mapSize);

		new DownloadImageTask().execute(url);
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		protected Bitmap doInBackground(String... urls) {
			String url = urls[0];
			Bitmap bitmap = null;
			try {
				InputStream in = new java.net.URL(url).openStream();
				bitmap = BitmapFactory.decodeStream(in);
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}
		
		protected void onPostExecute(Bitmap result) {
			bitmap = result;
		}
	}
	
	public void onLocationChanged(Location location) {
		if(mDistance.getCurrentLocation() == null || firstFix == true) {
			updateLocation(location, true);	
			firstFix = false;
		} else if(location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
			if(mDistance.getCurrentLocation().getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
				updateLocation(location, true);
			} else {
				if(location.distanceTo(mDistance.getCalcLocation()) >= mDistance.getTRESHOLD()) {
					updateLocation(location, true);
				} else {
					updateLocation(location, false);
				}
			}
			
		} else if(location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
			if(mDistance.getCurrentLocation().getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
				updateLocation(location, true);
			}
		}
		
		poiView.invalidate();
	}

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			System.arraycopy(event.values, 0, accelerometerValues, 0, event.values.length);
		}
		if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			System.arraycopy(event.values, 0, magneticFieldValues, 0, event.values.length);
		}
		
		float[] R = new float[9];
		
		SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
		SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_Z, SensorManager.AXIS_MINUS_X, R);
		SensorManager.getOrientation(R, orientation);

		float calcOrientation = orientation[0];
		float alpha = 0.02f;

		if (Float.isNaN(filteredOrientation))
			filteredOrientation = calcOrientation;

		float diffOrientation = calcOrientation - filteredOrientation;
		if (diffOrientation > Math.PI)
			diffOrientation -= 2 * Math.PI;
		filteredOrientation = (float) (alpha * diffOrientation + filteredOrientation);

		mDistance.findVisibleStops(AngleUtil.rad2deg(filteredOrientation));
		visible = mDistance.getVisible();
		
		poiView.invalidate();
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	
	private class PoiView extends SurfaceView {
		private	NinePatchDrawable tBox = (NinePatchDrawable)getResources().getDrawable(R.drawable.ultimate);
		
		private boolean drawExtraInfo = false;
		private PositionInfo poiExtraInfo = null;
		private float xTouch = OUT_OF_SCREEN;
		private float yTouch = OUT_OF_SCREEN;

		public PoiView(Context context) {
			super(context);
			setWillNotDraw(false);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			if(mDistance.getCurrentLocation() != null) drawHud(canvas);
			drawStops(canvas);
			if(drawExtraInfo) drawExtraInfo(canvas);
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if(event.getAction() == MotionEvent.ACTION_DOWN) {
				if(drawExtraInfo) {
					drawExtraInfo = false;
					poiExtraInfo = null;
					xTouch = OUT_OF_SCREEN;
					yTouch = OUT_OF_SCREEN;
				} else {
					drawExtraInfo = true;
					xTouch = event.getX();
					yTouch = event.getY();
				}
			}
			
			return true;
		}
		
		private void drawStops(Canvas canvas) {
			if(visible == null || visible.size() == 0)
				return;
			
			Collections.sort(visible, new Comparator<PositionInfo>() {
				public int compare(PositionInfo a, PositionInfo b) {
					if (a.getDistance() > b.getDistance()) return 1;
					if (a.getDistance() < b.getDistance()) return -1;
					return 0;
				}
			});

			Set<String> duplicates = new HashSet<String>(visible.size());
			int i = 0;
			while (i < visible.size()) {
				PositionInfo p = visible.get(i);
				if (duplicates.contains(p.getName()) == false) {
					duplicates.add(p.getName());
					i++;
				} else {
					if (p.getDistance() <= 250) {
						i++;
					} else {
						visible.remove(i);
					}
				}
			}
			
			Collections.reverse(visible);
			
			float textSize = 28f;
			float textSizeDiff = 32f;
			TextPaint textPaint = new TextPaint();
			textPaint.setARGB(255, 255, 255, 255);
			textPaint.setAntiAlias(true);
			
			int cw = canvas.getWidth();
			double widthFOV = (double) cw / (double) mDistance.getFOV();
			
			int ch = canvas.getHeight();
			Location currentLocation = mDistance.getCurrentLocation();
			float[] distanceBetween = new float[3];
			Location.distanceBetween(currentLocation.getLatitude(), currentLocation.getLongitude(), visible.get(0).getLat(), visible.get(0).getLon(), distanceBetween);
			double heightDistance = (double) ch / (double) distanceBetween[0];

			for(PositionInfo p : visible) {
				double angleDiff = p.getAngleDiff() + (double) mDistance.getFOVHALF();
				
				int left = (int) (widthFOV * angleDiff);
				int top;
				if(visible.size() != 1) {
					top = ch - (int) (heightDistance * p.getDistance());
				} else {
					top = (ch / 2) - (((int)textSize + (int)textSize + 10) / 2);
				}
				textSize += textSizeDiff * (float)top / (float)ch;
				textPaint.setTextSize(textSize);
				int right = left + 20;
				int bottom = top + (int)textSize + (int)textSize + 10;
				
				int initialRight;
				Rect tBoxBounds = new Rect();
				StringBuilder sb = new StringBuilder(String.valueOf(p.getDistance()));
				
				textPaint.getTextBounds(p.getName(), 0, p.getName().length(), tBoxBounds);
				initialRight = tBoxBounds.right;
				sb.append("m");
				textPaint.getTextBounds(sb.toString(), 0, sb.length(), tBoxBounds);
				
				if(initialRight > tBoxBounds.right)
					tBoxBounds.right = initialRight;
				
				tBoxBounds.left = left;
				tBoxBounds.top = top;
				tBoxBounds.right += right;
				tBoxBounds.bottom = bottom;
				
				int shiftLeft = tBoxBounds.width() / 2;
				tBoxBounds.left -= shiftLeft;
				tBoxBounds.right -= shiftLeft;
				
				int shiftUp = 0;
				if(tBoxBounds.bottom > ch) {
					shiftUp = tBoxBounds.bottom - ch;
					tBoxBounds.top -= shiftUp;
					tBoxBounds.bottom -= shiftUp;
				}
				
				if(drawExtraInfo == true) {
					if(tBoxBounds.left <= xTouch &&
					   tBoxBounds.right >= xTouch &&
					   tBoxBounds.top <= yTouch &&
					   tBoxBounds.bottom >= yTouch) {
						poiExtraInfo = p;
					}
				}
					
				// draw Bitmap
				mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
				mPaint.setColor(Color.rgb(238, 229, 222));
				mPaint.setTextSize(25);
				if(p.getName().equals("Lotte Cinema")){
					mIconBitmap = BitmapFactory.decodeResource(getResources(),
							R.drawable.lotte);
					mIconBitmap = Bitmap.createScaledBitmap(mIconBitmap, 150,
							150, true);
				}
				else if(p.getName().equals("CGV")){
					mIconBitmap = BitmapFactory.decodeResource(getResources(),
							R.drawable.cgv);
					mIconBitmap = Bitmap.createScaledBitmap(mIconBitmap, 150,
							150, true);
				}
				else if(p.getName().equals("Megabox")){
					mIconBitmap = BitmapFactory.decodeResource(getResources(),
							R.drawable.mega);
					mIconBitmap = Bitmap.createScaledBitmap(mIconBitmap, 150,
							150, true);
				}
				else{
					mIconBitmap = BitmapFactory.decodeResource(getResources(),
							R.drawable.etc);
					mIconBitmap = Bitmap.createScaledBitmap(mIconBitmap, 150,
							150, true);
				}
				
				canvas.drawBitmap(mIconBitmap, left - shiftLeft, top - shiftUp, mPaint);
				
				// draw text
				canvas.drawText(p.getName(), left + 20 - shiftLeft, top + textSize + 150 - shiftUp, textPaint);
				canvas.drawText(String.valueOf(p.getDistance()) + "m", left + 20 - shiftLeft, top + textSize + 150 + textSize - shiftUp, textPaint);
			}
			
			xTouch = OUT_OF_SCREEN;
			yTouch = OUT_OF_SCREEN;
		}
		
		private void drawExtraInfo(Canvas canvas) {	
			if(poiExtraInfo == null)
				return;
			
			int x = 0;
			int y = 0;
			int width = 1350;
			int height = 220;
			int textSize = 50;
			
			Rect tBoxBounds = new Rect(x, y, x + width, y + height);
			tBox.setBounds(tBoxBounds);
			tBox.draw(canvas);
			
			TextPaint textPaint = new TextPaint();
			textPaint.setARGB(255, 255, 255, 255);
			textPaint.setTextSize(textSize);
			textPaint.setAntiAlias(true);
			
			canvas.drawText(poiExtraInfo.getName() + " (" + poiExtraInfo.getDistance() +"m)", x + 10, y + 5 + 50, textPaint);
			canvas.drawText(poiExtraInfo.getType(), x + 10, y + 5 + 110, textPaint);
			canvas.drawText("Homepage: " + poiExtraInfo.getDescription(), x + 10, y + 5 + 170, textPaint);
		}
		
		@SuppressLint({ "FloatMath", "FloatMath" })
		private void drawHud(Canvas canvas) {
			relevant = mDistance.getRelevant();
			Location cloc = mDistance.getCalcLocation();
			
			Paint paintBg = new Paint();
			paintBg.setARGB(255, 0, 0, 0);
			Paint paintGreen = new Paint();
			paintGreen.setARGB(255, 0, 255, 0);
			paintGreen.setStrokeWidth(3);
			Paint paintRed = new Paint();
			paintRed.setARGB(255, 255, 0, 0);
			paintRed.setStrokeWidth(3);
			Paint paintBlue = new Paint();
			paintBlue.setARGB(255, 0, 0, 255);
			paintBlue.setStrokeWidth(2);
			Paint paintBlack = new Paint();
			paintBlack.setARGB(255, 0, 0, 0);
			paintBlack.setStrokeWidth(3);
			
			float fromLeft = canvas.getWidth() - (mapSize / 2);
			float fromTop = mapSize / 2;
			
			canvas.drawRect(canvas.getWidth() - (mapSize + 2), 0, canvas.getWidth(), (mapSize + 2), paintBg);

			paintBg.setARGB(255, 255, 255, 255);
			if(bitmap == null) {
				canvas.drawRect(canvas.getWidth() - mapSize, 0, canvas.getWidth(), mapSize, paintBg);
			} else {
				canvas.drawBitmap(bitmap, canvas.getWidth() - mapSize, 0, paintBg);
			}
			
			// draw direction			
			float angleRad = (float) ((Math.PI/2.0) - filteredOrientation);
			float userX = (float) Math.cos(angleRad);
			float userY = (float) -Math.sin(angleRad);
			canvas.drawLine(fromLeft, fromTop, userX * (mapSize / 2) + fromLeft, userY * (mapSize / 2) + fromTop, paintBlack);
			
			if(relevant != null && cloc != null) {
				for(PositionInfo p : relevant) {
					float pX = (float) ((p.getLon() - cloc.getLongitude()) / mDistance.getD() * 70);
					float pY = (float) -((p.getLat() - cloc.getLatitude()) / mDistance.getD() * 85);
					
					if(p.isVisible() == true) {
						canvas.drawPoint(pX + fromLeft, pY + fromTop, paintGreen);
					} else {
						canvas.drawPoint(pX + fromLeft, pY + fromTop, paintRed);
					}
				}
			}
		}
	}
}