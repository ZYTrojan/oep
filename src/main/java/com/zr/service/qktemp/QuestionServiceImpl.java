package com.zr.service.qktemp;

import java.util.LinkedList;
import java.util.List;

import com.zr.dao.TypeDao;
import com.zr.dao.impl.TypeDaoImpl;
import com.zr.dao.qktemp.QuestionDao;
import com.zr.dao.qktemp.QuestionDaoImpl;
import com.zr.model.Question;
import com.zr.model.Type;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QuestionServiceImpl implements QuestionService {
	QuestionDao qdao = new QuestionDaoImpl();

	@Override
	public List<Type> getAllQuestionType() {
		return qdao.getAllQuestionType();
	}

	@Override
	public JSONObject getAllQuestionJsonByTypeIdAndKey(int currentPage, int pageSize, int typeId,String key) {
		List<Question> questionList = new LinkedList<>();
		int totol = qdao.getQustionByTypeIdAndKey((currentPage-1)*pageSize,pageSize,typeId,key,questionList);
		JSONObject json = new JSONObject();
		json.put("total", totol);
		JSONArray rows = new JSONArray();
		for(Question qq:questionList){
			JSONObject tempQuestion = new JSONObject();
			tempQuestion.put("id", qq.getQ_id());
			tempQuestion.put("typeId", qq.getT_id());
			TypeDao tDao = new TypeDaoImpl();
			Type type = tDao.getTypeById(qq.getT_id());
			tempQuestion.put("typeName", type.getT_name());
			tempQuestion.put("content", qq.getQ_content());
			tempQuestion.put("answer", qq.getQ_answer());
			rows.add(tempQuestion);
		}
		json.put("rows", rows);
		return json;
	}
}
