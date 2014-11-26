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
					Toast.makeText(FindID_activity.this, "�̸��� ��������� �����ϼ���.", Toast.LENGTH_SHORT).show();
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
			// URL �����ϰ� �����ϱ�
			// --------------------------
			//URL url = new URL("http://192.168.0.15/member/findid.php"); // URL 
			URL url = new URL("http://johnnase.cafe24.com/client1_moon/findid.php"); // URL 
			
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
			http.setRequestProperty("content-type",	"application/x-www-form-urlencoded");
			// --------------------------
			// ������ �� ����
			// --------------------------
			StringBuffer buffer = new StringBuffer();
			buffer.append("name").append("=").append(myName).append("&"); // php ������ �� ����
			buffer.append("birth").append("=").append(myBirth).append("&"); // php �����տ� '$'������ �ʴ´�
			

			OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
			//Log.e("check1", "" + myId);
			PrintWriter writer = new PrintWriter(outStream);
			
			writer.write(buffer.toString());
			//Log.e("check2", "" + buffer.toString());
			writer.flush();
			//Log.e("check3","���ۿϷ�");
			
			
			
			// --------------------------
			// �������� ���۹ޱ�
			// --------------------------
			InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
			BufferedReader reader = new BufferedReader(tmp);
			StringBuilder builder = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) { // �������� ���δ����� ������ ���̹Ƿ�
														// ���δ����� �д´�
				builder.append(str + "\n"); // View�� ǥ���ϱ� ���� ���� ������ �߰�
			}
			myResult = builder.toString(); // ���۰���� ���� ������ ����
			
			
			Log.e("myResult1",""+builder);
			
			new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					// check
					Check_ID(myResult);
					handle.post(new Runnable() { // main Thread���� ����

						public void run() {
							Log.e("FindID -> Re", ""+Re);
							if(Re == 2) {
								//tv_showid.setText("��ġ�ϴ� ������ �����ϴ�.");
								Toast.makeText(FindID_activity.this, "��ġ�ϴ� ������ �����ϴ�.", Toast.LENGTH_SHORT).show();
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
		loading = ProgressDialog.show(this, "�ε�ȭ��", "�ε���", true,	false);
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
			Log.e("��ġ�ϴ�����������", ""+Re);
		}
		else {
			Re = 1;
			Log.e("��ġ�ϴ��������ִ�", ""+Re);
		}
	}
}
