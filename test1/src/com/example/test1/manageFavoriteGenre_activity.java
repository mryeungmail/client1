package com.example.test1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class manageFavoriteGenre_activity extends Activity implements OnClickListener {
	CheckBox cb1;
	CheckBox cb2;
	CheckBox cb3;
	CheckBox cb4;
	CheckBox cb5;
	CheckBox cb6;
	CheckBox cb7;
	CheckBox cb8;
	CheckBox cb9;
	CheckBox cb10;
	
	Button bt_genreSave;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.managefavoritegenre_layout);
	    cb1 = (CheckBox)findViewById(R.id.cb_action);
	    cb2 = (CheckBox)findViewById(R.id.cb_mello);
	    cb3 = (CheckBox)findViewById(R.id.cb_thrill);
	    cb4 = (CheckBox)findViewById(R.id.cb_horror);
	    cb5 = (CheckBox)findViewById(R.id.cb_hero);
	    cb6 = (CheckBox)findViewById(R.id.cb_fantasy);
	    cb7 = (CheckBox)findViewById(R.id.cb_comedy);
	    cb8 = (CheckBox)findViewById(R.id.cb_sf);
	    cb9 = (CheckBox)findViewById(R.id.cb_sfx);
	    cb10 = (CheckBox)findViewById(R.id.cb_adult);
	    
	    cb1.setText("액션");
	    cb2.setText("멜로");
	    cb3.setText("스릴러");
	    cb4.setText("공포");
	    cb5.setText("무협");
	    cb6.setText("판타지");
	    cb7.setText("코미디");
	    cb8.setText("SF");
	    cb9.setText("SFX");
	    cb10.setText("성인");
	    
	    bt_genreSave = (Button)findViewById(R.id.bt_genreSave);
	    bt_genreSave.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

}
