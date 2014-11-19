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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUp_activity extends Activity {

	SharedPreferences prefs = null;

	private CheckBox cb_man;
	private CheckBox cb_woman;

	private Button bt_setBirth;
	private TextView tv_birth;

	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	boolean birth_set = false;
	boolean sex_check = false;

	String myId, myPWord, myName, myBirth, mySex, mynick_name, myPWord_check, myOnly_pw;

	String Result_id_check;
	int Re = 0;

	public Handler handle = new Handler();
	public Handler handle2 = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_layout);

		findViewById(R.id.bt_signup_submit).setOnClickListener(buttonClick);

		findViewById(R.id.bt_signup_idOverlapCheck).setOnClickListener(
				bt_id_check);

		cb_man = (CheckBox) findViewById(R.id.cb_signup_man);
		cb_woman = (CheckBox) findViewById(R.id.cb_signup_woman);

		tv_birth = (TextView) findViewById(R.id.tv_signup_birth);
		bt_setBirth = (Button) findViewById(R.id.bt_signup_setBirth);
		bt_setBirth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		setCalendar();
	}// end of onCreate

	public void onCheckboxClicked(View v) {
		switch(v.getId()) {
		case 0x7f07013d : //(sss)
		//case R.id.cb_signup_man:
			mySex ="M";
			Log.e("mySex",mySex);
			if(cb_woman.isChecked()) {
				cb_woman.setChecked(false);
			}
			break;
		case 0x7f07013e : //(sssb)
		//case R.id.cb_signup_woman:
			mySex="F";
			Log.e("mySex",mySex);
			if(cb_man.isChecked()) {
				cb_man.setChecked(false);	
			}
			break;
		}
	}

	private void setCalendar() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	}

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

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	// ------------------------------
	// button Click
	// ------------------------------
	Button.OnClickListener buttonClick = new Button.OnClickListener() {// 확인버튼눌렀을때
		public void onClick(View v) {
			// ����ڰ� �Է��� ������ ����� �����Ѵ�
			myId = ((EditText) (findViewById(R.id.et_signup_id))).getText()
					.toString();
			myPWord = ((EditText) (findViewById(R.id.et_signup_pw))).getText()
					.toString();
			myName = ((EditText) (findViewById(R.id.et_signup_name))).getText()
					.toString();
			mynick_name = ((EditText) (findViewById(R.id.et_signup_nickname)))
					.getText().toString();
			// myBirth = ""+mYear+""+mMonth+""+mDay;
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
			myPWord_check = ((EditText) (findViewById(R.id.et_signup_pw_check)))
					.getText().toString();

			if (Re == 0) {
				Toast.makeText(signUp_activity.this, "아이디 중복체크를 하세요.",
						Toast.LENGTH_SHORT).show();
			} else if (!myPWord.equals(myPWord_check)) {
				Toast.makeText(signUp_activity.this, "비밀번호가 같지 않습니다.",
						Toast.LENGTH_SHORT).show();
			} else if (myPWord.length() < 5) {
				Toast.makeText(signUp_activity.this, "비밀번호는 5글자 이상으로 해주세요.",
						Toast.LENGTH_SHORT).show();
			} else if (Re == 2 && !(myId.equals("")) && !(myPWord.equals(""))
					&& !(myPWord_check.equals("")) && !(myName.equals(""))
					&& !(mynick_name.equals("")) && !(mySex.equals(""))
					&& !(myBirth.equals(""))) {
				new Thread(new Runnable() {
					public void run() {
						// TODO Auto-generated method stub
						// check
						Signup_Submit();
						handle.post(new Runnable() { // main Thread���� ����

							public void run() {

								/*
								GMailSender sender = new GMailSender("kdjiz6885@gmail.com", "ehdwns7407");
								// gmail���̵� , gmail�н�����
								try {
									Log.e("in_mail", "in_mail");
									sender.sendMail("안녕하세요 BST 임시비밀번호입니다","비밀번호를 분실할 경우를 대비하여 고유번호를 드립니다. 절대 지우지 마세요 "+ myOnly_pw,"tkffkels00@nate.com", myId);
									Log.e("in_mail2", "in_mail2");
									// 보내는사람이름, 내 용 , 보내는사람메일주소 , 받는사람메일주소

								} catch (Exception e) {
									Log.e("mail_fail", "mail_fail");

								}

								prefs = getSharedPreferences("bst",
										MODE_PRIVATE);
								SharedPreferences.Editor editor = prefs.edit();
								editor.putString("Logined_YN", "Y");
								editor.putString("MY_ID", myId);
								editor.putString("MY_NAME", myName);
								editor.putString("MY_NICKNAME", mynick_name);
								editor.putString("MY_PW", myPWord);

								Toast.makeText(SignUpActivity.this,	"" + myName + "님 회원가입 되었습니다",Toast.LENGTH_SHORT).show();
										*/
								finish();
							}
						});
					}
				}).start();

			} else {
				Toast.makeText(signUp_activity.this, "제대로 입력을 다하세요.",
						Toast.LENGTH_SHORT).show();
			}

		}
	};

	Button.OnClickListener bt_id_check = new Button.OnClickListener() {// 중복체크
																		// 버튼
																		// 눌렀을때
		public void onClick(View v) {
			// ����ڰ� �Է��� ������ ����� �����Ѵ�
			myId = ((EditText) (findViewById(R.id.et_signup_id))).getText()
					.toString();

			if (!(myId.equals(""))) {
				// /////////
				int k = 0;
				boolean Check_ID = false;
				for (int i = 0; i < myId.length(); i++) {
					if (myId.charAt(i) == '@') {
						k++;
						Check_ID = true;
					}
				}
				if (k == 1 && Check_ID == true) {
					new Thread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							// check
							Id_check_HTTPPOST();
							handle2.post(new Runnable() { // main Thread����
															// ����

								public void run() {

								}
							});
						}
					}).start();
				} else
					Toast.makeText(signUp_activity.this,
							"제대로 된 e-mail 주소를 입력하세요", Toast.LENGTH_SHORT)
							.show();

			} else
				Toast.makeText(signUp_activity.this, "ID를 입력하세요",
						Toast.LENGTH_SHORT).show();
		}
	};

	// ------------------------------
	// Http Post�� �ְ� �ޱ�
	// ------------------------------
	public void Signup_Submit() {
		try {

			URL url = new URL("http://192.168.0.15/member/sign_up.php"); // URL
			HttpURLConnection http = (HttpURLConnection) url.openConnection(); // ����
			Log.e("http ", "" + http);

			http.setDefaultUseCaches(false);
			http.setDoInput(true); // �������� �б� ��� ����
			http.setDoOutput(true); // ������ ���� ��� ����
			http.setRequestMethod("POST"); // ��� ����� POST

			http.setRequestProperty("content-type",	"application/x-www-form-urlencoded");
			StringBuffer buffer = new StringBuffer();
			buffer.append("user_id").append("=").append(myId).append("&"); 
			buffer.append("pword").append("=").append(myPWord).append("&");
			buffer.append("name").append("=").append(myName).append("&"); 
			buffer.append("nick_name").append("=").append(mynick_name).append("&");
			buffer.append("birth").append("=").append(myBirth).append("&");
			buffer.append("sex").append("=").append(mySex).append("&");
			//buffer.append("only_pw").append("=").append(myOnly_pw).append("&");

			OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
			Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);

			writer.write(buffer.toString());
			Log.e("check2", "" + buffer.toString());
			writer.flush();

			// --------------------------
			// �������� ��۹ޱ�
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { 
				builder.append(str + "\n"); 
			}
			// myResult = builder.toString(); // ��۰�� �� ������ ����
			Log.e("myResult_submit", builder.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // try
	} // HttpPostData

	public void Id_check_HTTPPOST() {
		try {
			// --------------------------
			// URL �����ϰ� �����ϱ�
			// --------------------------
			URL url = new URL("http://192.168.0.15/member/id_check.php"); 
			HttpURLConnection http = (HttpURLConnection) url.openConnection(); // ����
			Log.e("http접속_id_check", "" + http);
			http.setDefaultUseCaches(false);
			http.setDoInput(true); // �������� �б� ��� ����
			http.setDoOutput(true); // ������ ���� ��� ����
			http.setRequestMethod("POST"); // ��� ����� POST

			http.setRequestProperty("content-type",	"application/x-www-form-urlencoded");
			StringBuffer buffer = new StringBuffer();
			buffer.append("user_id").append("=").append(myId).append("&"); 

			OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
			Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);

			writer.write(buffer.toString());
			Log.e("check2", "" + buffer.toString());
			writer.flush();
			Log.e("check3", "전송완료");

			InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { 
				builder.append(str + "\n"); 
			}
			// myResult = builder.toString(); // ��۰�� �� ������ ����
			Log.e("myResult_id_check", builder.toString());
			Result_id_check = builder.toString();
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Id_Check(Result_id_check);
					handle2.post(new Runnable() { // main Thread���� ����

						public void run() {

							if (Re == 1) // id�� �ߺ��ɶ�
							{
								Toast.makeText(signUp_activity.this,
										"이미 사용중인 ID입니다.", Toast.LENGTH_SHORT)
										.show();
							} else if (Re == 2) // id�� �ߺ��ȵɶ�,
							{
								Toast.makeText(signUp_activity.this,
										"사용가능한 ID입니다.", Toast.LENGTH_SHORT)
										.show();
								myOnly_pw = ""
										+ (int) ((Math.random() * 9000) + 1000);
								Log.e("myOnly_pw", myOnly_pw);
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
	} // Id_check

	void Id_Check(String re) {

		Log.e("id_check", re);
		if (re.equals(" 1\n")) // id �� �ߺ��ɶ�, 1\n �տ� ���� �ʿ���
		{
			Re = 1;
		} else if (re.equals(" 2\n")) // id �� �ߺ��ȵɶ�, 2\n �տ� ���� �ʿ���
		{
			Re = 2;
		}
	}
}
