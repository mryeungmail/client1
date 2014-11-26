package com.example.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindPW_activity extends Activity {
	//private Button bt_setBirth;
		private Button bt_ok;
		private Button bt_cancel;
		//private TextView tv_birth;
		private TextView tv_showpw;

		String myID, myName, myOnlypw;
		String myResult = "";
		public Handler handle = new Handler();
		int Re;

		SharedPreferences prefs = null;
		String Logined_Check = "N";
		String MY_ID, MY_NAME, MY_ONLYPW;
		
		ProgressDialog loading;
		
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.findpw);
			// TODO Auto-generated method stub
			Log.e("Logined_check_findidactivity 1", Logined_Check);
			prefs = getSharedPreferences("bst", MODE_PRIVATE);
			Logined_Check = prefs.getString("Logined_YN", "N");
			Log.e("Logined_check_findidctivity 2", Logined_Check);
				
			tv_showpw = (TextView) findViewById(R.id.tv_findpw_showpw);
			
			bt_cancel = (Button)findViewById(R.id.bt_findpw_cancel);
			bt_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			
			
			bt_ok = (Button) findViewById(R.id.bt_findpw_submit);
			bt_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					myID = ((EditText) findViewById(R.id.et_findpw_id)).getText().toString();
					myName = ((EditText) findViewById(R.id.et_findpw_name)).getText().toString();
					myOnlypw = ((EditText) findViewById(R.id.et_findpw_onlypw)).getText().toString();
					
					if( !myID.equals("") && !myName.equals("") && !myOnlypw.equals("")) {
						callLoadingDialogue();
					}
					else {
						Toast.makeText(FindPW_activity.this, "전부 입력하세요.", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}// end of create
		
		public void FindPW() {
			try {
				// --------------------------
				// URL 설정하고 접속하기
				// --------------------------
				URL url = new URL("http://johnnase.cafe24.com/client1_moon/findpw.php"); // URL 
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
				buffer.append("user_id").append("=").append(myID).append("&"); // php 변수에 값 대입
				buffer.append("name").append("=").append(myName).append("&"); // php 변수앞에 '$'붙이지 않는다
				buffer.append("only_pw").append("=").append(myOnlypw).append("&");

				OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
				PrintWriter writer = new PrintWriter(outStream);
				
				writer.write(buffer.toString());
				Log.e("buffer.toString()", "" + buffer.toString());
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
				myResult = builder.toString(); // 전송결과를 전역 변수에 저장
				
				new Thread(new Runnable() {
					public void run() {
						// TODO Auto-generated method stub
						// check
						Check_PW(myResult);
						handle.post(new Runnable() { // main Thread에서 실행

							public void run() {
								Log.e("FindPW -> Re", ""+Re);
								if(Re == 1) {
									Toast.makeText(FindPW_activity.this, "고유번호불일치", Toast.LENGTH_SHORT).show();
								}
								else if (Re == 2) {
									Toast.makeText(FindPW_activity.this, "이름불일치", Toast.LENGTH_SHORT).show();
								}
								else if (Re == 3) {
									Toast.makeText(FindPW_activity.this, "이름,고유번호불일치", Toast.LENGTH_SHORT).show();
								}
								else if (Re == 4) {
									Toast.makeText(FindPW_activity.this, "아이디불일치", Toast.LENGTH_SHORT).show();
								}
								else if (Re == 5) {
									Toast.makeText(FindPW_activity.this, "아이디,고유번호불일치", Toast.LENGTH_SHORT).show();
								}
								else if (Re == 6) {
									Toast.makeText(FindPW_activity.this, "아이디,이름불일치", Toast.LENGTH_SHORT).show();
								}
								else if (Re == 7) {
									Toast.makeText(FindPW_activity.this, "아이디,이름,고유번호불일치", Toast.LENGTH_SHORT).show();
								}
								else {
									tv_showpw.setText(myResult);
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
		}//end of FindID
		
		public void callLoadingDialogue() {
			loading = ProgressDialog.show(this, "로딩화면", "로딩중", true,	false);
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					FindPW();
					handler.sendEmptyMessage(0);
				}
			});
			thread.start();
		}
		private Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				loading.dismiss();
			}
		};
		
		public void Check_PW(String re) {
			Log.e("Check_PW -> re", re);
			if(re.equals("1\n\n")) {
				Re = 1;
				Log.e("", ""+Re);
			}
			else if(re.equals("2\n\n")){
				Re = 2;
				Log.e("", ""+Re);
			}
			else if(re.equals("3\n\n")){
				Re = 3;
				Log.e("", ""+Re);
			}
			else if(re.equals("4\n\n")){
				Re = 4;
				Log.e("", ""+Re);
			}
			else if(re.equals("5\n\n")){
				Re = 5;
				Log.e("", ""+Re);
			}
			else if(re.equals("6\n\n")){
				Re = 6;
				Log.e("", ""+Re);
			}
			else if(re.equals("7\n\n")){
				Re = 7;
				Log.e("", ""+Re);
			}
			
			else {
				Re = 8;
			}
		}
	}
