package com.zr.dao.qktemp;

import java.util.List;

import com.zr.model.Question;
import com.zr.model.Type;

public interface QuestionDao {

	/**
	 * 获取所有的题型
	 * @return
	 */
	List<Type> getAllQuestionType();

	/**
	 * 分页查找,
	 * 通过题型获取所有该类题
	 * @param start			开始位置
	 * @param pageSize		条数
	 * @param typeId		题型id
	 * @param key			关键字
	 * @param questionList	题链表
	 * @return
	 */
	int getQustionByTypeIdAndKey(int start, int size, int typeId,String key,List<Question> questionList);
	
	
	
}
