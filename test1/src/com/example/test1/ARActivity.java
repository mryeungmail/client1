package com.example.test1;

import com.example.test1.AR;
import com.example.test1.Parser;
import com.example.test1.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;

public class ARActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(android.R.anim.slide_in_left, 0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_ar);

		new LoadingTask().execute();
	}

	private class LoadingTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			AR gApp = ((AR) getApplicationContext());
			Parser xml = new Parser(getApplicationContext());
			gApp.setStops(xml.parse());

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Intent cameraActivity = new Intent(getApplicationContext(), CameraActivity.class);
			startActivity(cameraActivity);
			finish();
		}
	}
}
