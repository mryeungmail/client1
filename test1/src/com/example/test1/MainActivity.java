package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button beScheduledMovie;
	Button movieReservation; 
	Button vod;
	Button reviewBoard;
	Button myPage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		beScheduledMovie = (Button)findViewById(R.id.bt_beScheduledMovie);
		beScheduledMovie.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(MainActivity.this, beScheduledMovie_activity.class);
				startActivity(t);
			}
		});
		movieReservation = (Button)findViewById(R.id.bt_movieReservation);
		movieReservation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(MainActivity.this, movieReservation_activity.class);
				startActivity(t);
			}
		});

		vod  = (Button)findViewById(R.id.bt_vod);
		vod.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(MainActivity.this, VOD_activity.class);
				startActivity(t);
			}
		});

		reviewBoard = (Button)findViewById(R.id.bt_reviewBoard);
		reviewBoard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(MainActivity.this, reviewBoard_activity.class);
				startActivity(t);
			}
		});
		
		myPage = (Button)findViewById(R.id.bt_myPage);
		myPage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(MainActivity.this, myPage_activity.class);
				startActivity(t);
			}
		});
	}
}
