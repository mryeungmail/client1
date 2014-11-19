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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginNlogout_activity extends Activity {
	String myId, myPWord, myResult;
	public Handler handle = new Handler();
	int Re;

	SharedPreferences prefs = null;
	String Logined_Check = "N";

	String MY_ID, MY_PW, MY_NAME, MY_NICKNAME;

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
							handle.post(new Runnable() { // main Thread���� ����

								public void run() {

								}
							});
						}
					}).start();

				} else {
					Toast.makeText(loginNlogout_activity.this,
							"ID �� ��й�ȣ�� �Է��ϼ���", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}//end of OnCreate

	public void Login() {
		try {
			// --------------------------
			// URL �����ϰ� �����ϱ�
			// --------------------------
			URL url = new URL(
					"http://tkffkels00.dothome.co.kr/member/login_post.php"); // URL
																				// -
																				// ����
																				// �̰ɷ�
																				// �ϸ�
																				// �ǾߵǴµ�
																				// ��
																				// ����

			// URL url = new URL("http://192.168.1.37/lesson01.php"); //URL
			// ����
			HttpURLConnection http = (HttpURLConnection) url.openConnection(); // ����
			Log.e("http����", "" + http);
			// --------------------------
			// ���� ��� ���� - �⺻���� �����̴�
			// --------------------------
			http.setDefaultUseCaches(false);
			http.setDoInput(true); // �������� �б� ��� ����
			http.setDoOutput(true); // ������ ���� ��� ����
			http.setRequestMethod("POST"); // ���� ����� POST

			// �������� ������ <Form>���� ���� �Ѿ�� �Ͱ� ���� ������� ó���϶�� �� �˷��ش�
			http.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			// --------------------------
			// ������ �� ����
			// --------------------------
			StringBuffer buffer = new StringBuffer();
			buffer.append("user_id").append("=").append(myId).append("&"); // php
																			// ������
																			// ��
																			// ����
			buffer.append("pword").append("=").append(myPWord).append("&"); // php
																			// �����տ�
																			// '$'������
																			// �ʴ´�
			// buffer.append("name").append("=").append(myName).append("&"); //
			// ����������'&'���
			// buffer.append("nick_name").append("=").append(mynick_name).append("&");
			// buffer.append("birth").append("=").append(myBirth).append("&");
			// buffer.append("sex").append("=").append("�ϴ� ����");

			OutputStreamWriter outStream = new OutputStreamWriter(
					http.getOutputStream(), "EUC-KR");
			// Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);

			writer.write(buffer.toString());
			// Log.e("check2", "" + buffer.toString());
			writer.flush();
			// Log.e("check3","���ۿϷ�");

			// --------------------------
			// �������� ���۹ޱ�
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(
					http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { // �������� ���δ����� ������ ���̹Ƿ�
														// ���δ����� �д´�
				builder.append(str + "\n"); // View�� ǥ���ϱ� ���� ���� ������ �߰�
			}
			myResult = builder.toString(); // ���۰���� ���� ������ ����

			Log.e("myResult1", "" + builder);

			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Check_Login(myResult);
					handle.post(new Runnable() { // main Thread���� ����

						public void run() {

							if (Re == 1) {
								Toast.makeText(loginNlogout_activity.this,
										"�߸��ǰų� ���� Id �Դϴ�", Toast.LENGTH_SHORT)
										.show();
								// Log.e("Id_no����","Id_no����");
								// Re = 1;
							} else if (Re == 2) {
								Toast.makeText(loginNlogout_activity.this,
										"�߸��� ��й�ȣ �Դϴ�", Toast.LENGTH_SHORT)
										.show();
							} else if (Re == 3) {

								Intent it = new Intent(
										loginNlogout_activity.this,
										MainActivity.class);
								it.putExtra("Logined_YN", "Y");
								startActivity(it);
								finish();
								// ////
								// ���� ���ݱ����� ��Ƽ��Ƽ�� �� �����ϴ� �ڵ� �ʿ���

								// ////
								prefs = getSharedPreferences("bst",	MODE_PRIVATE);
								SharedPreferences.Editor editor = prefs.edit();
								editor.putString("Logined_YN", "Y");
								editor.putString("MY_ID", MY_ID);
								editor.putString("MY_NAME", MY_NAME);
								editor.putString("MY_NICKNAME", MY_NICKNAME);
								editor.putString("MY_PW", MY_PW);

								editor.commit();
								Toast.makeText(loginNlogout_activity.this, "�α��� �Ǿ����ϴ�", Toast.LENGTH_SHORT).show();
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
			// Toast.makeText(LoginActivity.this, "�߸��ǰų� ���� Id �Դϴ�",
			// Toast.LENGTH_SHORT).show();
			Log.e("Id_no����", "Id_no����");
			Re = 1;
		} else if (re.equals("2\n")) {
			// Toast.makeText(LoginActivity.this, "�߸��� ��й�ȣ �Դϴ�",
			// Toast.LENGTH_SHORT).show();
			Log.e("pw_no����", "pw_no����");
			Re = 2;
		} else {
			Log.e("success����", "success����");
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
			MY_ID = MY_ID.substring(4, MY_ID.length());
			MY_NAME = MY_NAME.substring(5, MY_NAME.length());
			MY_NICKNAME = MY_NICKNAME.substring(5, MY_NICKNAME.length());
			MY_PW = MY_PW.substring(5, MY_PW.length() - 2);

			Log.e("sss", "" + MY_ID + "//" + MY_NAME + "//" + MY_NICKNAME + "//" + MY_PW);

			// Toast.makeText(LoginActivity.this, "�α��� �Ǿ����ϴ�",
			// Toast.LENGTH_SHORT).show();
			Re = 3;
		}
	}
}
