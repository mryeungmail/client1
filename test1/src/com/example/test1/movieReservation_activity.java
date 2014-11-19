package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class movieReservation_activity extends Activity {


	
	Spinner sp_city;
	Spinner sp_gu;
	Spinner sp_cinema;
	
	Spinner sp_movie;
	Spinner sp_time;
	
	Button to_seat;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moviereservation_layout);
		
		
		
		sp_city = (Spinner) findViewById(R.id.moviereservation_sp_city);
		sp_city.setPrompt("도시");
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.category_mr_city, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_city.setAdapter(adapter);
		sp_city.setOnItemSelectedListener(new MyOnItemSelectedListener_city());
		
		
		sp_gu = (Spinner) findViewById(R.id.moviereservation_sp_gu);
		sp_gu.setPrompt("지역");
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.category_mr_gu, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_gu.setAdapter(adapter2);
		sp_gu.setOnItemSelectedListener(new MyOnItemSelectedListener_gu());
		
		
		sp_cinema = (Spinner) findViewById(R.id.moviereservation_sp_cinema);
		sp_cinema.setPrompt("영화관");
		ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.category_mr_cinema, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_cinema.setAdapter(adapter3);
		sp_cinema.setOnItemSelectedListener(new MyOnItemSelectedListener_cinema());
		
		/*
		/////////////////////////////////////
		///영화 & 상영시간 //
		sp_movie = (Spinner) findViewById(R.id.moviereservation_sp_movie);
		sp_movie.setPrompt("영화");
		//ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this,R.array.category_mr_gu, android.R.layout.simple_spinner_item);
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//sp_movie.setAdapter(adapter4);
		//sp_movie.setOnItemSelectedListener(new MyOnItemSelectedListener_gu());
		
		
		sp_time = (Spinner) findViewById(R.id.moviereservation_sp_time);
		sp_time.setPrompt("상영시간");
		//ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this,R.array.category_mr_cinema, android.R.layout.simple_spinner_item);
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//sp_time.setAdapter(adapter5);
		//sp_time.setOnItemSelectedListener(new MyOnItemSelectedListener_cinema());
		/////////////////////////////////////
		*/
		to_seat = (Button)findViewById(R.id.bt_to_seat);
		if(true) //선택됬을때
		{
			
			to_seat.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent t = new Intent(movieReservation_activity.this, movieReservation_seat.class);
					//어떤 영화, 시간 선택인지 보낼것
					
					
					startActivity(t);
					finish();
				}
			});
		}
	}
	
	
	
	public class MyOnItemSelectedListener_city implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// Toast.makeText(parent.getContext(), "선택한 행성은 " +
			// parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
			Log.e("선택", ""+parent.getItemAtPosition(position));

			if (parent.getItemAtPosition(position).toString().charAt(0) == '고') {
			
				
			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '1'&& parent.getItemAtPosition(position).toString().charAt(1) == '.') {
				


			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '2') {

			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '3') {

			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// Do nothing
		}
	}

	
	public class MyOnItemSelectedListener_gu implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// Toast.makeText(parent.getContext(), "선택한 행성은 " +
			// parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
			Log.e("선택", ""+parent.getItemAtPosition(position));

			if (parent.getItemAtPosition(position).toString().charAt(0) == '고') {
			
				
			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '1'&& parent.getItemAtPosition(position).toString().charAt(1) == '.') {
				


			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '2') {

			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '3') {

			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// Do nothing
		}
	}

	
	public class MyOnItemSelectedListener_cinema implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// Toast.makeText(parent.getContext(), "선택한 행성은 " +
			// parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
			Log.e("선택", ""+parent.getItemAtPosition(position));

			if (parent.getItemAtPosition(position).toString().charAt(0) == '고') {
			
				
			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '1'&& parent.getItemAtPosition(position).toString().charAt(1) == '.') {
				


			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '2') {

			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '3') {

			}

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// Do nothing
		}
	}

	
	
	
}
