package com.crikee.entertainment.object;


public class ItunesRank {
	
	private String rank ;
	private String songname ;
	private String singer ;
	private String rate ;
	private String state ;
	
	public ItunesRank(){}
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	public String getRate(){
		return rate ;
	}
	
	public void setRate(String rate)
	{
		this.rate = rate ;
	}
	
	public String getState()
	{
		return state ;
	}
	
	public void setState(String state)
	{
		this.state = state ;
	}
		
	
	
}