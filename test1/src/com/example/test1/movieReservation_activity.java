package com.example.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class movieReservation_activity extends Activity {


	
	//Spinner sp_city;
	Spinner sp_cinema;
	//Spinner sp_cinema;
	
	Spinner sp_movie;
	Spinner sp_time;
	
	Button to_seat;
	
	int Selected_cinema;
	String myResult_Selected_cinema = "";
	public Handler handle_Selected_cinema = new Handler();
	
	int Selected_movie;
	String myResult_Selected_movie = "";
	public Handler handle_Selected_movie = new Handler();
	
	int Selected_time;
	String myResult_Selected_time = "";
	public Handler handle_Selected_time = new Handler();
	
	
	ArrayList<Movie_Data> arraylist_Movie_Data;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moviereservation_layout);
		
		
		/*
		sp_city = (Spinner) findViewById(R.id.moviereservation_sp_city);
		sp_city.setPrompt("도시");
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.category_mr_city, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_city.setAdapter(adapter);
		sp_city.setOnItemSelectedListener(new MyOnItemSelectedListener_city());
		*/
		
		sp_cinema = (Spinner) findViewById(R.id.moviereservation_sp_gu);
		sp_cinema.setPrompt("지역");
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.category_mr_gu, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_cinema.setAdapter(adapter2);
		sp_cinema.setOnItemSelectedListener(new MyOnItemSelectedListener_gu());
		
		
		arraylist_Movie_Data = new ArrayList<Movie_Data>();
		
		
		/*
		sp_cinema = (Spinner) findViewById(R.id.moviereservation_sp_cinema);
		sp_cinema.setPrompt("영화관");
		ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.category_mr_cinema, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_cinema.setAdapter(adapter3);
		sp_cinema.setOnItemSelectedListener(new MyOnItemSelectedListener_cinema());
		*/
		
		
		
		/////////////////////////////////////
		///영화 & 상영시간 //
		sp_movie = (Spinner) findViewById(R.id.moviereservation_sp_movie);
		sp_movie.setPrompt("영화");
		ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this,R.array.category_mr_gu, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_movie.setAdapter(adapter4);
		sp_movie.setOnItemSelectedListener(new MyOnItemSelectedListener_gu());
		
		
		sp_time = (Spinner) findViewById(R.id.moviereservation_sp_time);
		sp_time.setPrompt("상영시간");
		//ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this,R.array.category_mr_cinema, android.R.layout.simple_spinner_item);
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//sp_time.setAdapter(adapter5);
		//sp_time.setOnItemSelectedListener(new MyOnItemSelectedListener_cinema());
		/////////////////////////////////////
		
		
		to_seat = (Button)findViewById(R.id.bt_to_seat);
		if(true) //선택됬을때
		{
			
			to_seat.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent t = new Intent(movieReservation_activity.this, movieReservation_seat.class);
					//어떤 영화, 시간 선택인지 보낼것
					t.putExtra("Selected_cinema", Selected_cinema);
					t.putExtra("Selected_movie", Selected_movie);
					t.putExtra("Selected_time", Selected_time);
					
					
					startActivity(t);
					finish();
				}
			});
		}
	}
	
	
	/*
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
*/
	
	public class MyOnItemSelectedListener_gu implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// Toast.makeText(parent.getContext(), "선택한 행성은 " +
			// parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
			Log.e("선택 영화관", ""+parent.getItemAtPosition(position));

			if (parent.getItemAtPosition(position).toString().charAt(0) == '고') {
			
				
			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '1'&& parent.getItemAtPosition(position).toString().charAt(1) == '.') {
				Selected_cinema = 1; // 부산대점 선택
			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '2') {
				Selected_cinema = 2;
			} else if (parent.getItemAtPosition(position).toString().charAt(0) == '3') {
				Selected_cinema = 3;
			}

			
			
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// Do nothing
		}
	}

	/*
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
*/
	
	/*
	public void MR_Find_Movie() {
		try {
 
			URL url = new URL("http://johnnase.cafe24.com/client1_moon/findid.php"); // URL 
			
			// 설정
			HttpURLConnection http = (HttpURLConnection) url.openConnection(); // 접속
			Log.e("http접속", "" + http);
			// --------------------------
			// 전송 모드 설정 - 기본적인 설정이다
			// --------------------------
			http.setDefaultUseCaches(false);
			http.setDoInput(true); // 서버에서 읽기 모드 지정
			http.setDoOutput(true); // 서버로 쓰기 모드 지정
			http.setRequestMethod("POST"); // 전송 방식은 POST

			// 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
			http.setRequestProperty("content-type",	"application/x-www-form-urlencoded");
			// --------------------------
			// 서버로 값 전송
			// --------------------------
			StringBuffer buffer = new StringBuffer();
			buffer.append("which_cinema").append("=").append(Selected_cinema).append("&"); // php 변수에 값 대입
			//buffer.append("birth").append("=").append(myBirth).append("&"); // php 변수앞에 '$'붙이지 않는다
			

			OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
			//Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);
			
			writer.write(buffer.toString());
			//Log.e("check2", "" + buffer.toString());
			writer.flush();
			//Log.e("check3","전송완료");
			
						
			// --------------------------
			// 서버에서 전송받기
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { // 서버에서 라인단위로 보내줄 것이므로
														// 라인단위로 읽는다
				builder.append(str + "\n"); // View에 표시하기 위해 라인 구분자 추가
			}
			myResult_Selected_cinema = builder.toString(); // 전송결과를 전역 변수에 저장
			
			
			Log.e("myResult_Selected_cinema",""+builder);
			
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Check_ID(myResult_Selected_cinema);
					handle_Selected_cinema.post(new Runnable() { // main Thread에서 실행

						public void run() {
							Log.e("FindID -> Re", ""+Re);
							if(Re == 2) {
								//tv_showid.setText("일치하는 정보가 없습니다.");
								Toast.makeText(FindID_activity.this, "일치하는 정보가 없습니다.", Toast.LENGTH_SHORT).show();
							}
							else {
								tv_showid.setText(myResult);
							}
						}
					});
				}
			}).start();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end of Find_movie

	
	public void MR_Find_movieTime() {
		try {
 
			URL url = new URL("http://johnnase.cafe24.com/client1_moon/findid.php"); // URL 
			
			// 설정
			HttpURLConnection http = (HttpURLConnection) url.openConnection(); // 접속
			Log.e("http접속", "" + http);
			// --------------------------
			// 전송 모드 설정 - 기본적인 설정이다
			// --------------------------
			http.setDefaultUseCaches(false);
			http.setDoInput(true); // 서버에서 읽기 모드 지정
			http.setDoOutput(true); // 서버로 쓰기 모드 지정
			http.setRequestMethod("POST"); // 전송 방식은 POST

			// 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
			http.setRequestProperty("content-type",	"application/x-www-form-urlencoded");
			// --------------------------
			// 서버로 값 전송
			// --------------------------
			StringBuffer buffer = new StringBuffer();
			buffer.append("which_movie").append("=").append(Selected_movie).append("&"); // php 변수에 값 대입
			//buffer.append("birth").append("=").append(myBirth).append("&"); // php 변수앞에 '$'붙이지 않는다
			

			OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
			//Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);
			
			writer.write(buffer.toString());
			//Log.e("check2", "" + buffer.toString());
			writer.flush();
			//Log.e("check3","전송완료");
			
						
			// --------------------------
			// 서버에서 전송받기
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { // 서버에서 라인단위로 보내줄 것이므로
														// 라인단위로 읽는다
				builder.append(str + "\n"); // View에 표시하기 위해 라인 구분자 추가
			}
			myResult_Selected_movie = builder.toString(); // 전송결과를 전역 변수에 저장
			
			
			Log.e("myResult_Selected_movie",""+builder);
			
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Check_ID(myResult_Selected_movie);
					handle_Selected_movie.post(new Runnable() { // main Thread에서 실행

						public void run() {
							Log.e("FindID -> Re", ""+Re);
							if(Re == 2) {
								//tv_showid.setText("일치하는 정보가 없습니다.");
								Toast.makeText(FindID_activity.this, "일치하는 정보가 없습니다.", Toast.LENGTH_SHORT).show();
							}
							else {
								tv_showid.setText(myResult);
							}
						}
					});
				}
			}).start();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end of Find_time
	
	*/
	
		
}
