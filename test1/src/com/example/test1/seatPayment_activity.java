package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class seatPayment_activity extends Activity {
	TextView tv_payment_seatNum;
	TextView tv_payment_seatMoney;
	Button bt_payment_seatPayment;
	TextView tv_seatIdxList;
	TextView tv_seatIdx;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.seatpayment_layout);
	    tv_payment_seatNum = (TextView)findViewById(R.id.tv_payment_seatNum);
	    tv_payment_seatMoney = (TextView)findViewById(R.id.tv_payment_seatMoney);
	    bt_payment_seatPayment = (Button)findViewById(R.id.bt_payment_seatPayment);
	    tv_seatIdxList = (TextView)findViewById(R.id.tv_seatIdxList);
	    tv_seatIdx = (TextView)findViewById(R.id.tv_seatIdx);
	    
	    Intent t = getIntent();
	    int real_seatNum = t.getExtras().getInt("real_seatNum");
	    int real_seatMoney = t.getExtras().getInt("real_seatMoney"); 
	    int real_seatNumArr[] = new int[30];
	    for(int i=0; i<30; i++) {
	    	real_seatNumArr[i] = t.getExtras().getInt("real_seatNumArr"+i);
	    }
	    
	    tv_payment_seatNum.setText(real_seatNum+" 명");
	    tv_payment_seatMoney.setText(real_seatMoney+" 원");
	    for(int i=0; i<30; i++) {
	    	  tv_seatIdxList.append(""+real_seatNumArr[i]);
	    	  if(real_seatNumArr[i] == 1) {
	    		  tv_seatIdx.append(i+" ");
	    	  }
	    }
	    
	    bt_payment_seatPayment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(seatPayment_activity.this, MainActivity.class);
				startActivity(t);
				finish();
				Toast.makeText(seatPayment_activity.this, "결제완료", Toast.LENGTH_SHORT).show();
			}
		});
	    // TODO Auto-generated method stub
	}

}
