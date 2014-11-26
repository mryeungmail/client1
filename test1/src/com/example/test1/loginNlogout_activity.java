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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class loginNlogout_activity extends Activity {
	String myId, myPWord, myResult;
	public Handler handle = new Handler();
	int Re;

	SharedPreferences prefs = null;
	String Logined_Check = "N";

	//String MY_ID, MY_PW, MY_NAME, MY_NICKNAME;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginnlogout_layout);
		// TODO Auto-generated method stub

		Log.e("Logined_check_loginactivity 1", Logined_Check);
		prefs = getSharedPreferences("bst", MODE_PRIVATE);
		Logined_Check = prefs.getString("Logined_YN", "N");
		Log.e("Logined_check_loginactivity 2", Logined_Check);
		
		
		Button bt_gotoSignUp = (Button)findViewById(R.id.bt_gotoSignUp);
		bt_gotoSignUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(loginNlogout_activity.this, signUp_activity.class);
				startActivity(t);
			}
		});
		
		Button bt_find_id = (Button)findViewById(R.id.bt_findID);
		bt_find_id.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(loginNlogout_activity.this, FindID_activity.class);
				startActivity(t);
			}
		});
		
		Button bt_find_pw = (Button)findViewById(R.id.bt_findPW);
		bt_find_pw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(loginNlogout_activity.this, FindPW_activity.class);
				startActivity(t);
			}
		});
		
		
		Button bt_login = (Button) findViewById(R.id.bt_login_login);
		bt_login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				myId = ((EditText) (findViewById(R.id.et_login_id))).getText()
						.toString();
				myPWord = ((EditText) (findViewById(R.id.et_login_pw)))
						.getText().toString();

				if (!(myId.equals("") && myPWord.equals(""))) {
					new Thread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							// check
							Login();
							handle.post(new Runnable() { // main Thread에서 실행

								public void run() {

								}
							});
						}
					}).start();

				} else {
					Toast.makeText(loginNlogout_activity.this,
							"ID 와 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}//end of OnCreate

	public void Login() {
		try {
			// --------------------------
			// URL 설정하고 접속하기
			// --------------------------
			URL url = new URL("http://johnnase.cafe24.com/client1_moon/login_post.php"); // URL
																				// -
																				// 원래
																				// 이걸로
																				// 하면
																				// 되야되는데
																				// 이
																				// 젠장

			// URL url = new URL("http://192.168.1.37/lesson01.php"); //URL
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
			http.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			// --------------------------
			// 서버로 값 전송
			// --------------------------
			StringBuffer buffer = new StringBuffer();
			buffer.append("user_id").append("=").append(myId).append("&"); // php
																			// 변수에
																			// 값
																			// 대입
			buffer.append("pword").append("=").append(myPWord).append("&"); // php
																			// 변수앞에
																			// '$'붙이지
																			// 않는다
			// buffer.append("name").append("=").append(myName).append("&"); //
			// 변수구분은'&'사용
			// buffer.append("nick_name").append("=").append(mynick_name).append("&");
			// buffer.append("birth").append("=").append(myBirth).append("&");
			// buffer.append("sex").append("=").append("일단 남자");

			OutputStreamWriter outStream = new OutputStreamWriter(
					http.getOutputStream(), "EUC-KR");
			// Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);

			writer.write(buffer.toString());
			// Log.e("check2", "" + buffer.toString());
			writer.flush();
			// Log.e("check3","전송완료");

			// --------------------------
			// 서버에서 전송받기
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(
					http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { // 서버에서 라인단위로 보내줄 것이므로
														// 라인단위로 읽는다
				builder.append(str + "\n"); // View에 표시하기 위해 라인 구분자 추가
			}
			myResult = builder.toString(); // 전송결과를 전역 변수에 저장

			Log.e("myResult1", "" + builder);

			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Check_Login(myResult);
					handle.post(new Runnable() { // main Thread에서 실행

						public void run() {

							if (Re == 1) {
								Toast.makeText(loginNlogout_activity.this,
										"잘못되거나 없는 Id 입니다", Toast.LENGTH_SHORT)
										.show();
								// Log.e("Id_no들어옴","Id_no들어옴");
								// Re = 1;
							} else if (Re == 2) {
								Toast.makeText(loginNlogout_activity.this,
										"잘못된 비밀번호 입니다", Toast.LENGTH_SHORT)
										.show();
							} else if (Re == 3) {

								Intent it = new Intent(loginNlogout_activity.this, myPage_activity.class);
								it.putExtra("Logined_YN", "Y");
								startActivity(it);
								finish();
								// ////
								// 여기 지금까지의 엑티비티들 다 종료하는 코드 필요함

								// ////
								prefs = getSharedPreferences("bst",	MODE_PRIVATE);
								SharedPreferences.Editor editor = prefs.edit();
								editor.putString("Logined_YN", "Y");
								editor.putString("MY_ID", myId);
								//editor.putString("MY_NAME", MY_NAME);
								//editor.putString("MY_NICKNAME", MY_NICKNAME);
								//editor.putString("MY_PW", MY_PW);

								editor.commit();
								Toast.makeText(loginNlogout_activity.this, "로그인 되었습니다", Toast.LENGTH_SHORT).show();
							}
						}
					});
				}
			}).start();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // try
	} // HttpPostData

	void Check_Login(String re) {
		Log.e("check_login", re);
		if (re.equals("1\n")) {
			// Toast.makeText(LoginActivity.this, "잘못되거나 없는 Id 입니다",
			// Toast.LENGTH_SHORT).show();
			Log.e("Id_no들어옴", "Id_no들어옴");
			Re = 1;
		} else if (re.equals("2\n")) {
			// Toast.makeText(LoginActivity.this, "잘못된 비밀번호 입니다",
			// Toast.LENGTH_SHORT).show();
			Log.e("pw_no들어옴", "pw_no들어옴");
			Re = 2;
		} else {
			Log.e("success들어옴", "success들어옴");
			/*
			int count = 0;
			for (int j = 0; j < re.length(); j++) {
				// String re = freeboard_row.get(i);

				if (re.charAt(j) == '#') {
					count++;
				}
				if (count == 0)
					MY_ID += re.charAt(j);
				else if (count == 1) {
					MY_NAME += re.charAt(j);
				} else if (count == 2) {
					MY_NICKNAME += re.charAt(j);
				} else if (count == 3) {
					MY_PW += re.charAt(j);
				}
			}
			Log.e("MY_ID",MY_ID);
			Log.e("MY_ID",MY_NAME);
			Log.e("MY_ID",MY_NICKNAME);
			Log.e("MY_ID",MY_PW);

			MY_ID = MY_ID.substring(4, MY_ID.length());
			MY_NAME = MY_NAME.substring(5, MY_NAME.length());
			MY_NICKNAME = MY_NICKNAME.substring(5, MY_NICKNAME.length());
			MY_PW = MY_PW.substring(5, MY_PW.length() - 2);

			Log.e("sss", "" + MY_ID + "//" + MY_NAME + "//" + MY_NICKNAME + "//" + MY_PW);

			// Toast.makeText(LoginActivity.this, "로그인 되었습니다",
			// Toast.LENGTH_SHORT).show();
			*/
			Re = 3;
		}
	}
	
	
	
	public boolean onKeyDown( int KeyCode, KeyEvent event )
	{
	 
		if( event.getAction() == KeyEvent.ACTION_DOWN ){
			// 이 부분은 특정 키를 눌렀을때 실행 된다.
			// 만약 뒤로 버튼을 눌럿을때 할 행동을 지정하고 싶다면
	 
				if( KeyCode == KeyEvent.KEYCODE_BACK ){
					//여기에 뒤로 버튼을 눌렀을때 해야할 행동을 지정한다
											
					Intent it = new Intent(loginNlogout_activity.this, myPage_activity.class);
					it.putExtra("Logined_YN", "N");
					startActivity(it);
					finish();
					// 여기서 리턴값이 중요한데; 리턴값이 true 이냐 false 이냐에 따라 행동이 달라진다.
					// true 일경우 back 버튼의 기본동작인 종료를 실행하게 된다.
					// 하지만 false 일 경우 back 버튼의 기본동작을 하지 않는다.
					// back 버튼을 눌렀을때 종료되지 않게 하고 싶다면 여기서 false 를 리턴하면 된다.
					// back 버튼의 기본동작을 막으면 어플리케이션을 종료할 방법이 없기때문에
					// 따로 종료하는 방법을 마련해야한다.
				}
	 
		}
		return super.onKeyDown( KeyCode, event );
	}
	
	
}
