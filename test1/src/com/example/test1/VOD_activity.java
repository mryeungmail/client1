package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VOD_activity extends Activity {

	Button watchAllVOD;
	Button watchVODAgain;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.vod_layout);
	    
		watchAllVOD = (Button)findViewById(R.id.bt_watchAllVOD);
		watchAllVOD.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(VOD_activity.this, watchAllVOD_activity.class);
				startActivity(t);
			}
		});
		
		watchVODAgain = (Button)findViewById(R.id.bt_watchVODAgain);
		watchVODAgain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(VOD_activity.this, watchVODAgain_activity.class);
				startActivity(t);
			}
		});
	    
	}

}
