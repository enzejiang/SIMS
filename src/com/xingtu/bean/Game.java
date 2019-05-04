package com.xingtu.bean;

import java.io.Serializable;

public class Game implements Serializable{
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setGameName(String name)
	{
		this.gamename = name;
	}
	
	public String getGameName()
	{
		return this.gamename;
	}
	public void setGamePlace(String place)
	{
		this.gameplace = place;
	}
	
	public String getGamePlace()
	{
		return this.gameplace;
	}
	
	public void setGameAddr(String addr)
	{
		this.gameaddr = addr;
	}
	
	public String getGameAddr()
	{
		return this.gameaddr;
	}
	
	public void setGameDate(String date)
	{
		this.gamedate = date;
	}
	
	public String getGameDate()
	{
		return this.gamedate;
	}
	public void setGameContent(String content)
	{
		this.gamecontent = content;
	}
	
	public String getGameContent()
	{
		return this.gamecontent;
	}
	
	public void setGameResults(String result)
	{
		this.gameresults = result;
	}
	
	public String getGameResults()
	{
		return this.gameresults;
	}
	
	
	private Integer id;
	private String gamename;
	private String gameplace;
	private String gamedate;
	private String gamecontent;
	private String gameaddr;
	private String gameresults;
}
