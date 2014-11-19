package com.example.test1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class checkReservation_activity extends Activity {

	/** Called when the activity is first created. */

	private ListView listview;
	DataAdapter adapter;
	ArrayList<movieData> movieList;
	movieData d;
	movieData data;
	Cursor cursor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkreservation_layout);
		// TODO Auto-generated method stub
		listview = (ListView) findViewById(R.id.listView1);
		movieList = new ArrayList<movieData>();
	}// end of create

	private class DataAdapter extends ArrayAdapter<movieData> {
		private LayoutInflater mInflater;

		public DataAdapter(Context context, ArrayList<movieData> object) {
			super(context, 0, object);
			mInflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		}

		public View getView(int position, View v, ViewGroup parent) {
			View view = null;
			if (v == null) {
				view = mInflater.inflate(R.layout.moviereservation_item, null);
			} else {
				view = v;
			}
			data = this.getItem(position);
			if (data != null) {
				view.setTag(data.getid());
				// Id.add(data.getid());
				TextView tv_movieName = (TextView) view
						.findViewById(R.id.tv_moviereservation_item_movieName);
				TextView tv_movieGrade = (TextView) view
						.findViewById(R.id.tv_moviereservation_item_movieGrade);
				TextView tv_movieRating = (TextView) view
						.findViewById(R.id.tv_moviereservation_item_movieRating);
				tv_movieName.setText(" " + data.getname());
				tv_movieGrade.setText(data.getsimple_info());
				tv_movieRating.setText(data.getsimple_info());
				// tv_infoname2.setTextColor(Color.RED);

				selectImage(view, data.getid());

			}
			return view;
		}
	}// end of DateAdapter

	public void selectImage(View v, int data) {

		Log.e("data_id", "" + data);
		// ImageView iv = (ImageView) v.findViewById(R.id.image_tourinfo_list);
		// iv.setImageResource(R.drawable.p1_1 + (data * 3 - 3));

	}

	AdapterView.OnItemClickListener mListViewItemClickLister = new AdapterView.OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {

			Log.e("view.getTag", "" + view.getTag());

			Intent intent = new Intent(checkReservation_activity.this, movieInfoNmovieReservation_activity.class);
			intent.putExtra("data_id", view.getTag().toString());
			intent.putExtra("from_where", 1);
			startActivity(intent);
		}
	};

	class movieData {
		private String simple_info;
		private int stamp_course;
		private int id;
		private String name;
		private String addr;
		private String info;
		private double lat;
		private double lng;
		private String phone;
		private String pic1;
		private String pic2;
		private String pic3;
		private int family;
		private int date;
		private int nature;
		private int history;
		private int culture;
		private int citytour;
		private int sports;
		private int architec;

		public movieData(Context context, String _simple_info, int _stamp_course,
				int _id, String _name, String _addr, String _info, double _lat,
				double _lng, String _phone, String _pic1, String _pic2,
				String _pic3, int _family, int _date, int _nature,
				int _history, int _culture, int _citytour, int _sports,
				int _architec) {
			simple_info = _simple_info;
			stamp_course = _stamp_course;
			id = _id;
			name = _name;
			addr = _addr;
			info = _info;
			lat = _lat;
			lng = _lng;
			phone = _phone;
			pic1 = _pic1;
			pic2 = _pic2;
			pic3 = _pic3;
			family = _family;
			date = _date;
			nature = _nature;
			history = _history;
			culture = _culture;
			citytour = _citytour;
			sports = _sports;
			architec = _architec;
		}

		public String getsimple_info() {
			return simple_info;
		}

		public int getstamp_course() {
			return stamp_course;
		}

		public int getid() {
			return id;
		}

		public String getname() {
			return name;
		}

		public String getaddr() {
			return addr;
		}

		public String getinfo() {
			return info;
		}

		public Double getlat() {
			return lat;
		}

		public Double getlng() {
			return lng;
		}

		public String phone() {
			return phone;
		}

		public String getpic1() {
			return pic1;
		}

		public String getpic2() {
			return pic2;
		}

		public String getpic3() {
			return pic3;
		}

		public int getfamily() {
			return family;
		}

		public int getdate() {
			return date;
		}

		public int getnature() {
			return nature;
		}

		public int gethistory() {
			return history;
		}

		public int getculture() {
			return culture;
		}

		public int getcitytour() {
			return citytour;
		}

		public int getsports() {
			return sports;
		}

		public int getarchitec() {
			return architec;
		}
	}

}
