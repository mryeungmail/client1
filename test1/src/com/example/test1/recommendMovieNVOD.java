package com.example.test1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class recommendMovieNVOD extends Activity {

	/** Called when the activity is first created. */

	private ListView listview;
	DataAdapter adapter;
	ArrayList<movieNVODData> recommendmovieNVODList;
	movieNVODData d;
	movieNVODData data;
	Cursor cursor;
	
	private dataBase mHelper;

	private SQLiteDatabase dbRead; // 데이터베이스 불러와 읽을 수 있게!!
	// private SQLiteDatabase dbWrite; // 데이터베이스 불러와 쓸수 수 있게!!

	final static String dbName = "site.db";
	final static String dbTableName = "site_table";

	Button gotoHome;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommendmovienvod_layout);
		// TODO Auto-generated method stub
		listview = (ListView) findViewById(R.id.listView1);
		recommendmovieNVODList = new ArrayList<movieNVODData>();
		
		recommendmovieNVODList.add(data);
		recommendmovieNVODList.add(data);
		recommendmovieNVODList.add(data);
		recommendmovieNVODList.add(data);
		recommendmovieNVODList.add(data);
		
		adapter = new DataAdapter(this, recommendmovieNVODList);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(mListViewItemClickLister);
		
		gotoHome = (Button)findViewById(R.id.bt_gotoMain);
		gotoHome.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t = new Intent(recommendMovieNVOD.this, MainActivity.class);
				t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(t);
			}
		});
	}// end of create

	private class DataAdapter extends ArrayAdapter<movieNVODData> {
		private LayoutInflater mInflater;

		public DataAdapter(Context context, ArrayList<movieNVODData> object) {
			super(context, 0, object);
			mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		}
		public View getView(int position, View v, ViewGroup parent) {
			View view = null;
			if (v == null) {
				view = mInflater.inflate(R.layout.recommendmovienvod_item, null);
			} else {
				view = v;
			}
			data = this.getItem(position);
			if (data != null) {
				view.setTag(data.getId());
				// Id.add(data.getid());
				TextView tv_movieName = (TextView) view.findViewById(R.id.tv_recommendmovienvod_item_movieName);
				//tv_movieName.setText(" " + data.getname());
				TextView tv_movieDirector = (TextView) view.findViewById(R.id.tv_recommendmovienvod_item_movieDirector);
				//tv_movieName.setText(" " + data.getname());
				TextView tv_movieActor = (TextView) view.findViewById(R.id.tv_recommendmovienvod_item_movieActor);
				//tv_movieName.setText(" " + data.getname());
				TextView tv_movieRating = (TextView) view.findViewById(R.id.tv_recommendmovienvod_item_movieRating);
				//selectImage(view, data.getId());
			}
			return view;
		}
	}// end of DataAdapter

	public void selectImage(View v, int data) {
		Log.e("data_id", "" + data);
		ImageView iv = (ImageView) v.findViewById(R.id.im_movieNVOD);
		// iv.setImageResource(R.drawable.p1_1 + (data * 3 - 3));
	}
	AdapterView.OnItemClickListener mListViewItemClickLister = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			//Log.e("view.getTag", "" + view.getTag());
			Intent intent = new Intent(recommendMovieNVOD.this, movieReservation_activity.class);
			//intent.putExtra("data_id", view.getTag().toString());
			//intent.putExtra("from_where", 1);
			startActivity(intent);
		}
	};
	
	public void checkDB() {
		
	}

	class movieNVODData {
		private int id;
		private String movieName;
		private String movieDirector;
		private String movieActor;
		private double movieRating;
		public movieNVODData(Context context, int _id, String _movieName, String _movieDirector,
				String _movieActor, double _movieRating) {
			id = _id;
			movieName = _movieName;
			movieDirector = _movieDirector;
			movieActor = _movieActor;
			movieRating = _movieRating;
		}
		public int getId() {
			return id;
		}
		public String getmovieName() {
			return movieName;
		}
		public String getmovieDirector() {
			return movieDirector;
		}
		public String getmovieActor() {
			return movieActor;
		}
		public double getmovieRating() {
			return movieRating;
		}
	}
}
