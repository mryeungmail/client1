package com.example.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindID_activity extends Activity {
	private Button bt_setBirth;
	private Button bt_ok, bt_cancel;
	private TextView tv_birth;
	private TextView tv_showid;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	// String myId, myPWord, myResult;
	String myName, myBirth;
	String myResult = "";
	public Handler handle = new Handler();
	int Re;

	SharedPreferences prefs = null;
	String Logined_Check = "N";
	String MY_NAME, MY_BIRTH;
	
	ProgressDialog loading;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findid);
		// TODO Auto-generated method stub
		Log.e("Logined_check_FindID_activity 1", Logined_Check);
		prefs = getSharedPreferences("bst", MODE_PRIVATE);
		Logined_Check = prefs.getString("Logined_YN", "N");
		Log.e("Logined_check_findidctivity 2", Logined_Check);
		
		setCalendar();
		
		tv_birth = (TextView) findViewById(R.id.tv_findid_birth);
		tv_showid = (TextView) findViewById(R.id.tv_findid_showid);
		
		bt_setBirth = (Button) findViewById(R.id.bt_findid_setBirth);
		bt_setBirth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		bt_cancel = (Button)findViewById(R.id.bt_findid_cancel);
		bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		
		bt_ok = (Button) findViewById(R.id.bt_findid_submit);
		bt_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myName = ((EditText) (findViewById(R.id.et_findid_name))).getText().toString();
				if(mMonth >= 0 && mMonth <= 8) {
					if(mDay >= 1 && mDay <= 9) {
						myBirth = mYear+""+"0"+(mMonth+1)+""+"0"+mDay;
						
					}
					else {
						myBirth = mYear+""+"0"+(mMonth+1)+""+mDay;
					}
				}
				else {
					if(mDay >= 1 && mDay <= 9) {
						myBirth = mYear+""+(mMonth+1)+""+"0"+mDay;
					}
					else {
						myBirth = mYear+""+(mMonth+1)+""+mDay;
					}
				}
				
				if(!myName.equals("") && !myBirth.equals("")) {
					callLoadingDialogue();
				}
				else {
					Toast.makeText(FindID_activity.this, "이름과 생년월일을 설정하세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}// end of create

	private void setCalendar() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	private void updateDisplay() {
		if (mMonth >= 0 && mMonth <= 8) {
			if (mDay >= 1 && mDay <= 9) {
				myBirth = mYear + "" + "0" + (mMonth + 1) + "" + "0" + mDay;

			} else {
				myBirth = mYear + "" + "0" + (mMonth + 1) + "" + mDay;

			}
		} else {
			if (mDay >= 1 && mDay <= 9) {
				myBirth = mYear + "" + (mMonth + 1) + "" + "0" + mDay;
			} else {
				myBirth = mYear + "" + (mMonth + 1) + "" + mDay;
			}
		}
		tv_birth.setText(myBirth);
		// tv_birth.setText(new
		// StringBuilder().append(mYear).append(" ").append(mMonth+1).append(" ").append(mDay).append(""));
	}
	
	protected Dialog onCreateDialog(int id) {
		switch(id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
		}
		return null;
	}
	
	public void FindID() {
		try {
			// --------------------------
			// URL 설정하고 접속하기
			// --------------------------
			//URL url = new URL("http://192.168.0.15/member/findid.php"); // URL 
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
			buffer.append("name").append("=").append(myName).append("&"); // php 변수에 값 대입
			buffer.append("birth").append("=").append(myBirth).append("&"); // php 변수앞에 '$'붙이지 않는다
			

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
			myResult = builder.toString(); // 전송결과를 전역 변수에 저장
			
			
			Log.e("myResult1",""+builder);
			
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Check_ID(myResult);
					handle.post(new Runnable() { // main Thread에서 실행

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
	}//end of FindID
	
	public void callLoadingDialogue() {
		loading = ProgressDialog.show(this, "로딩화면", "로딩중", true,	false);
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FindID();
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
	
	public void Check_ID(String re) {
		Log.e("Check_ID -> re", re);
		if(re.charAt(1) == '2') {
			Re = 2;
			Log.e("일치하는정보가없다", ""+Re);
		}
		else {
			Re = 1;
			Log.e("일치하는정보가있다", ""+Re);
		}
	}
}
