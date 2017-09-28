package com.zr.service.impl;

import java.util.List;

import com.zr.dao.QueryScoreDao;
import com.zr.dao.impl.QueryScoreDaoImpl;
import com.zr.model.Scoreofuser;
import com.zr.service.QueryScoreService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QueryScoreServiceImpl implements QueryScoreService {
	QueryScoreDao qdao = new QueryScoreDaoImpl();

	@Override
	public JSONArray getExamMsg1(String uname, int page, int pageSize) {
		int start = (page-1)*pageSize;
		List<Scoreofuser> stus = qdao.getmsg1(uname, start, pageSize);
		JSONArray ja = new JSONArray();
		for (int i = 0; i < stus.size(); i++) {
			JSONObject js = new JSONObject();
			js.put("uname",uname);
			js.put("ename",qdao.getEnameByEid(stus.get(i).getE_id()));
			js.put("score",stus.get(i).getScore());
			ja.add(js);
		}
		return ja;
	}
	
	@Override
	public JSONArray getExamMsg2(String ename, int page, int pageSize) {
		int start = (page-1)*pageSize;
		List<Scoreofuser> stus = qdao.getmsg2(ename, start, pageSize);
		JSONArray ja = new JSONArray();
		for (int i = 0; i < stus.size(); i++) {
			JSONObject js = new JSONObject();
			js.put("ename",ename);
			js.put("uname",qdao.getUnameByUid(stus.get(i).getU_id()));
			js.put("score",stus.get(i).getScore());
			ja.add(js);
		}
		return ja;
	}
	
	@Override
	public int getUserExamNum(String uname) {
		int a = qdao.getUserExam(uname);
		return a;
	}

	@Override
	public int getExamUserNum(String ename) {
		int a = qdao.getExamUser(ename);
		return a;
	}

	@Override
	public String getUserNameByUid(int u_id) {
		String a = qdao.getUnameByUid(u_id);
		return a;
	}

	@Override
	public String getExamNameByEid(int e_id) {
		String a = qdao.getEnameByEid(e_id);
		return a;
	}

	@Override
	public int getUidByUname(String uname) {
		int a = qdao.getUidByUname(uname);
		return a;
	}

	@Override
	public int getEidByEname(String ename) {
		int a = qdao.getEidByEname(ename);
		return a;
	}

	@Override
	public JSONArray getScoreByUname(String uname) {
		List<Scoreofuser> stus = qdao.getScoreByU(uname);
		JSONArray ja = new JSONArray();
		for (int i = 0; i < stus.size(); i++) {
			JSONObject js = new JSONObject();
			js.put("uname",uname);
			js.put("ename",qdao.getEnameByEid(stus.get(i).getE_id()));
			js.put("score",stus.get(i).getScore());
			ja.add(js);
		}
		return ja;
	}

	@Override
	public JSONArray getScoreByEname(String ename) {
		List<Scoreofuser> stus = qdao.getScoreByE(ename);
		JSONArray ja = new JSONArray();
		for (int i = 0; i < stus.size(); i++) {
			JSONObject js = new JSONObject();
			js.put("ename",ename);
			js.put("uname",qdao.getUnameByUid(stus.get(i).getU_id()));
			js.put("score",stus.get(i).getScore());
			ja.add(js);
		}
		return ja;
	}
}
