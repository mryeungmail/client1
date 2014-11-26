package com.example.test1;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class movieReservation_seat extends Activity {

	ToggleButton bt_seat[] = new ToggleButton[30];
	TextView tx_seat;

	TextView tv_seatNum;
	TextView tv_seatMoney;
	Button bt_seatPayment;
	//
	int seatMoney = 10000;
	int seatNum = 0;
	int sumOfseatMoney = 0;
	int idx = 0;
	//private ArrayList<Integer> seatNumArr = new ArrayList<Integer>();
	int seatNumarr[] = new int[30];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movieresrvation_seat);

		tv_seatNum = (TextView) findViewById(R.id.tv_seatNum);
		tv_seatMoney = (TextView) findViewById(R.id.tv_seatMoney);
		tv_seatNum.setText(seatNum + " 명");
		tv_seatMoney.setText(sumOfseatMoney + " 원");
		
		for (int i = 0; i < 30; i++) {// 버튼 30개
			seatNumarr[i] = 0;
			bt_seat[i] = (ToggleButton) findViewById(R.id.seat_11 + i);
			bt_seat[i].setBackgroundColor(Color.LTGRAY);
		}

		bt_seat[0].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[0].isChecked()) {
					bt_seat[0].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[0] = 1;//해당 자리 좌석 선택

				} else {
					bt_seat[0].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[0] = 0;//해당 자리 좌석 해제
				}
			}
		});
		bt_seat[1].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[1].isChecked()) {
					bt_seat[1].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[1] = 1;
				} else {
					bt_seat[1].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[1] = 0;
				}
			}
		});
		bt_seat[2].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[2].isChecked()) {
					bt_seat[2].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[2] = 1;
				} else {
					bt_seat[2].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[2] = 0;
				}
			}
		});
		bt_seat[3].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[3].isChecked()) {
					bt_seat[3].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[3] = 1;
				} else {
					bt_seat[3].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[3] = 0;
				}
			}
		});
		bt_seat[4].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[4].isChecked()) {
					bt_seat[4].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[4] = 1;
				} else {
					bt_seat[4].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[4] = 0;
				}
			}
		});
		bt_seat[5].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[5].isChecked()) {
					bt_seat[5].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[5] = 1;
				} else {
					bt_seat[5].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[5] = 0;
				}
			}
		});
		bt_seat[6].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[6].isChecked()) {
					bt_seat[6].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[6] = 1;
				} else {
					bt_seat[6].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[6] = 0;
				}
			}
		});
		bt_seat[7].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[7].isChecked()) {
					bt_seat[7].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[7] = 1;
				} else {
					bt_seat[7].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[7] = 0;
				}
			}
		});
		bt_seat[8].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[8].isChecked()) {
					bt_seat[8].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[8] = 1;
				} else {
					bt_seat[8].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[8] = 0;
				}
			}
		});
		bt_seat[9].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[9].isChecked()) {
					bt_seat[9].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[9] = 1;
				} else {
					bt_seat[9].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[9] = 0;
				}
			}
		});
		bt_seat[10].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[10].isChecked()) {
					bt_seat[10].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[10] = 1;
				} else {
					bt_seat[10].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[10] = 0;
				}
			}
		});
		bt_seat[11].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[11].isChecked()) {
					bt_seat[11].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[11] = 1;
				} else {
					bt_seat[11].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[11] = 0;
				}
			}
		});
		bt_seat[12].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[12].isChecked()) {
					bt_seat[12].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[12] = 1;
				} else {
					bt_seat[12].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[12] = 0;
				}
			}
		});
		bt_seat[13].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[13].isChecked()) {
					bt_seat[13].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[13] = 1;
				} else {
					bt_seat[13].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[13] = 0;
				}
			}
		});
		bt_seat[14].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[14].isChecked()) {
					bt_seat[14].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[14] = 1;
				} else {
					bt_seat[14].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[14] = 0;
				}
			}
		});
		bt_seat[15].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[15].isChecked()) {
					bt_seat[15].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[15] = 1;
				} else {
					bt_seat[15].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[15] = 0;
				}
			}
		});
		bt_seat[16].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[16].isChecked()) {
					bt_seat[16].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[16] = 1;
				} else {
					bt_seat[16].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[16] = 0;
				}
			}
		});
		bt_seat[17].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[17].isChecked()) {
					bt_seat[17].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[17] = 1;
				} else {
					bt_seat[17].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[17] = 0;
				}
			}
		});
		bt_seat[18].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[18].isChecked()) {
					bt_seat[18].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[18] = 1;
				} else {
					bt_seat[18].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[18] = 0;
				}
			}
		});
		bt_seat[19].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[19].isChecked()) {
					bt_seat[19].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[19] = 1;
				} else {
					bt_seat[19].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[19] = 0;
				}
			}
		});
		bt_seat[20].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[20].isChecked()) {
					bt_seat[20].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[20] = 1;
				} else {
					bt_seat[20].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[20] = 0;
				}
			}
		});
		bt_seat[21].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[21].isChecked()) {
					bt_seat[21].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[21] = 1;
				} else {
					bt_seat[21].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[21] = 0;
				}
			}
		});
		bt_seat[22].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[22].isChecked()) {
					bt_seat[22].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[22] = 1;
				} else {
					bt_seat[22].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[22] = 0;
				}
			}
		});
		bt_seat[23].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[23].isChecked()) {
					bt_seat[23].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[23] = 1;
				} else {
					bt_seat[23].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[23] = 0;
				}
			}
		});
		bt_seat[24].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[24].isChecked()) {
					bt_seat[24].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[24] = 1;
				} else {
					bt_seat[24].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[24] = 0;
				}
			}
		});
		bt_seat[25].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[25].isChecked()) {
					bt_seat[25].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[25] = 1;
				} else {
					bt_seat[25].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[25] = 0;
				}
			}
		});
		bt_seat[26].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[26].isChecked()) {
					bt_seat[26].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[26] = 1;
				} else {
					bt_seat[26].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[26] = 0;
				}
			}
		});
		bt_seat[27].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[27].isChecked()) {
					bt_seat[27].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[27] = 1;
				} else {
					bt_seat[27].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[27] = 0;
				}
			}
		});
		bt_seat[28].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[28].isChecked()) {
					bt_seat[28].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[28] = 1;
				} else {
					bt_seat[28].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[28] = 0;
				}
			}
		});
		bt_seat[29].setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (bt_seat[29].isChecked()) {
					bt_seat[29].setBackgroundColor(Color.GREEN);
					sumOfseatMoney += seatMoney;
					seatNum++;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[29] = 1;
				} else {
					bt_seat[29].setBackgroundColor(Color.LTGRAY);
					sumOfseatMoney -= seatMoney;
					seatNum--;
					tv_seatNum.setText(seatNum + " 명");
					tv_seatMoney.setText(sumOfseatMoney + " 원");
					seatNumarr[29] = 0;
				}
			}
		});
		
		bt_seatPayment = (Button)findViewById(R.id.bt_request_seatPayment);
		bt_seatPayment.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(movieReservation_seat.this, seatPayment_activity.class);
				t.putExtra("real_seatNum", seatNum);
				t.putExtra("real_seatMoney", sumOfseatMoney);
				//t.putExtra("real_seatNumArr", seatNumArr);
				for(int i=0; i<30; i++) {
					t.putExtra("real_seatNumArr"+i, seatNumarr[i]);
				}
				startActivity(t);
			}
		});
	}//end of Oncreate

}
