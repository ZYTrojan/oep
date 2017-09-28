package com.zr.dao;

import java.util.List;

import com.zr.model.Exam;
import com.zr.model.Question;

public interface ExamDao {
	/**
	 * 根据关键词实现对exam的分页查找
	 * @param key		关键词
	 * @param start		起始位置
	 * @param pageSize	该页记录行数
	 * @param examList	存exam的链表
	 * @return
	 */
	int getExamByKey(String key, int start, int pageSize,List<Exam> examList);

	/**
	 * 批量删除考试
	 * @param examIds	考试id数组
	 * @return			删除成功返回true
	 */
	boolean deleteByIds(int[] examIds);

	/**
	 * 新增一门考试记录
	 * @param exam	考试实体:主键自增,状态默认为false
	 * @return		插入成功返回考试id,否则返回-1
	 */
	int insert(Exam exam);

	/**
	 * 通过id获取考试实体
	 * @param id
	 * @return
	 */
	Exam getExamById(int id);

	/**
	 * 获取已存在的考题id
	 * @param examId	考试id
	 * @return	
	 */
	int[] getExistQuestionId(int examId);

	/**
	 * 批量添加考题
	 * @param questionIds	
	 * @return	插入成功则返回true
	 */
	boolean insertExamQuestions(int examId,int[] questionIds,int score);

	/**
	 * 通过考试id获取该考试全部的题目信息
	 * @param examId
	 * @return 
	 */
	List<Question> getQuestionOfExam(int examId);

	/**
	 * 删除考题通过考试id和题id[]
	 * @param examId
	 * @param ids
	 * @return
	 */
	boolean removeQuestionOfExam(int examId, int[] ids);
	
}
