package com.example.test1;

import android.content.Context;

public class Movie_Data {
	private int movie_Code;
	private String movie_Name;
	private String movie_Director;
	private String movie_Nation;
	private String movie_Actor;
	private String movie_ReleaseData;
	private int movie_RunningTime;
	private int movie_Grade;
	private String movie_Genre;
	private String movie_Story;
	private float movie_Score;
	//private String movie_Review;
	private int movie_Price;
	private int movie_Spectator;
	private int movie_Running_Flag;
	
	public Movie_Data(Context context, int _movie_Code, String _movie_Name, String _movie_Director,String _movie_Nation, String _movie_Actor,
			String _movie_ReleaseData, int _movie_RunningTime, int _movie_Grade, String _movie_Genre, String _movie_Story, float _movie_Score,
			int _movie_Price, int _movie_Spectator, int _movie_Running_Flag) {
		movie_Code = _movie_Code;
		movie_Name = _movie_Name;
		movie_Director = _movie_Director;
		movie_Nation = _movie_Nation;
		movie_Actor = _movie_Actor;
		movie_ReleaseData = _movie_ReleaseData;
		movie_RunningTime = _movie_RunningTime;
		movie_Grade = _movie_Grade;
		movie_Genre = _movie_Genre;
		movie_Story = _movie_Story;
		movie_Score = _movie_Score;
		movie_Price = _movie_Price;
		movie_Spectator = _movie_Spectator;
		movie_Running_Flag = _movie_Running_Flag;
				
	}
	/*
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
	*/
}