package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.Game;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.IGameService;

public class GameServiceImpl implements IGameService {
	private IBaseDao dao = new BaseDaoImpl();
	@Override
	public List<Object> getGames() {
		// TODO Auto-generated method stub
	    //获取数据
		List<Object> list = this.dao.getList(Game.class, "SELECT * FROM G_GAME");
				
		return list;
	}

	@Override
	public Integer insert(String name, String place, String date, String content, String result, String addr) {
		// TODO Auto-generated method stub
		Integer id = this.dao.insertReturnKeys("INSERT INTO G_GAME(GAMENAME, GAMEPLACE, GAMEDATE, GAMECONTENT, GAMERESULTS, GAMEADDR) VALUE(?, ?, ?, ?, ?, ?)",
								new Object[]{name, place, date, content, result, addr});
		return id;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.dao.delete("DELETE FROM G_GAME WHERE ID = ?", new Object[]{id});
	}

	@Override
	public void update(String name, String place, String date, String content, String result, String addr,Integer id) {
		// TODO Auto-generated method stub
		this.dao.update("UPDATE G_GAME SET GAMENAME=?, GAMEPLACE=?, GAMEDATE=?, GAMECONTENT=? , GAMERESULTS=?, GAMEADDR=? WHERE ID=?",
				new Object[]{name, place, date, content, result,  addr, id});
	}

}
