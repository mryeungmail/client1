package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class myPage_activity extends Activity {
	
	Button loginNlogout;
	Button checkReservation;
	Button recommendMovie;
	Button manageFavoriteGenre;
	Button modifyMemberInfo;
	

	SharedPreferences prefs = null;

	String Logined_YN = "N";
	private SharedPreferences pref = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mypage_layout);

		pref = getSharedPreferences("bst", MODE_PRIVATE);
		Logined_YN = pref.getString("Logined_YN", "N");

		loginNlogout = (Button) findViewById(R.id.bt_loginNlogout);
		if (Logined_YN == "Y") {
			loginNlogout.setText("로그아웃");
			loginNlogout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					prefs = getSharedPreferences("bst", MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putString("Logined_YN", "N");
					editor.commit();
					Toast.makeText(myPage_activity.this, "로그아웃이되었습니다.",
							Toast.LENGTH_SHORT).show();
					finish();
				}
			});

		} else if (Logined_YN == "N") {
			loginNlogout.setText("로그인");
			loginNlogout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent t = new Intent(myPage_activity.this,
							loginNlogout_activity.class);
					startActivity(t);
				}
			});
		}
		checkReservation = (Button) findViewById(R.id.bt_checkReservation);
		checkReservation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(myPage_activity.this,
						checkReservation_activity.class);
				startActivity(t);
			}
		});
		recommendMovie = (Button) findViewById(R.id.bt_myPage_recommendMovie);
		recommendMovie.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(myPage_activity.this,
						recommendMovieNVOD.class);
				startActivity(t);
			}
		});
		manageFavoriteGenre = (Button) findViewById(R.id.bt_manageFavoriteGenre);
		manageFavoriteGenre.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(myPage_activity.this, manageFavoriteGenre_activity.class);
				startActivity(t);
			}
		});
		modifyMemberInfo = (Button) findViewById(R.id.bt_modifyMemberInfo);
		modifyMemberInfo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent t = new Intent(myPage_activity.this,	checkReservation_activity.class);
				//startActivity(t);
			}
		});
	}

}
