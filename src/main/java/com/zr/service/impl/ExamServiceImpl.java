package com.zr.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.zr.dao.ExamDao;
import com.zr.dao.TypeDao;
import com.zr.dao.impl.ExamDaoImpl;
import com.zr.dao.impl.TypeDaoImpl;
import com.zr.dao.qktemp.QuestionDao;
import com.zr.dao.qktemp.QuestionDaoImpl;
import com.zr.model.Exam;
import com.zr.model.Question;
import com.zr.model.Type;
import com.zr.service.ExamService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ExamServiceImpl implements ExamService {
	ExamDao examDao = new ExamDaoImpl();

	@Override
	public JSONObject getExamByKey(String key, int currentPage, int pageSize) {
		JSONObject json = new JSONObject();
		List<Exam> examList = new LinkedList<>();
		//count是记录总数即json中的total
		int count = examDao.getExamByKey(key, (currentPage - 1) * pageSize, pageSize, examList);
		json.put("total", count);
		JSONArray examsJson = new JSONArray();
		for (Exam ee : examList) {
			//rows数组
			JSONObject exam = new JSONObject();
			exam.put("examId", ee.getE_id());
			exam.put("examName", ee.getE_name());
			int length = ee.getE_endtime().length();
			exam.put("examTime", ee.getE_starttime()+" - "+ee.getE_endtime().substring(length-8, length));
			exam.put("examTotal", ee.getE_total());
			exam.put("examState", ee.getE_state());
			examsJson.add(exam);
		}
		json.put("rows", examsJson);
		return json;
	}

	@Override
	public boolean deleteExamByIds(int[] examIds) {
		return examDao.deleteByIds(examIds);
	}

	@Override
	public int insertExam(String examName, String examStartTime, String examEndTime, int examTotal) {
		Exam exam = new Exam();
		exam.setE_name(examName);
		exam.setE_starttime(examStartTime);
		exam.setE_endtime(examEndTime);
		exam.setE_total(examTotal);
		exam.setE_state("false");
		return examDao.insert(exam);
	}

	@Override
	public JSONObject getExamById(int id) {
		Exam examEntity = examDao.getExamById(id);
		JSONObject examJson = new JSONObject();
		examJson.put("examId", examEntity.getE_id());
		examJson.put("examName", examEntity.getE_name());
		int length = examEntity.getE_endtime().length();
		examJson.put("examTime", examEntity.getE_starttime()+" - "+examEntity.getE_endtime().substring(length-8, length));
		examJson.put("examTotal", examEntity.getE_total());
		examJson.put("examState", examEntity.getE_state());
		return examJson;
	}

	@Override
	public boolean setExamQuestion(int currentExamId, int[] questionIds,int score) {
		int[] isExistQuestionId = examDao.getExistQuestionId(currentExamId);
		Integer[] temp = arrContrast(questionIds,isExistQuestionId);
		int[] result = new int[temp.length];
		for(int i = 0;i < temp.length;i++){
			result[i] = temp[i];
		}
		return examDao.insertExamQuestions(currentExamId,result,score);
	}
	
    /**
     * 去除两个数组中相同的值并返回一个新的数组
     * @param arr1
     * @param arr2
     * @return
     */
    private static Integer[] arrContrast(int[] arr1, int[] arr2){  
        List<Integer> list = new LinkedList<Integer>();  
        for (Integer str : arr1) {                //处理第一个数组
            if (!list.contains(str)) {  
                list.add(str);  
            }  
        }  
        for (Integer str : arr2) {      //如果第二个数组存在和第一个数组相同的值，就删除  
            if(list.contains(str)){  
                list.remove(str);  
            }  
        }  
        Integer[] temp = {};   //创建空数组  
        list.toArray(temp);    //List to Array  
        return list.toArray(temp);
    }

	@Override
	public JSONArray getQuestionOfExam(int currentExamId) {
		List<Question> questionList = examDao.getQuestionOfExam(currentExamId);
		
		JSONArray resultJson = new JSONArray();
		for (Question question : questionList) {
			JSONObject qJson = new JSONObject();
			qJson.put("id",question.getQ_id());
			qJson.put("typeId", question.getT_id());
			TypeDao tDao = new TypeDaoImpl();
			Type type = tDao.getTypeById(question.getT_id());
			qJson.put("typeName", type.getT_name());
			qJson.put("content", question.getQ_content());
			qJson.put("answer", question.getQ_answer());
			resultJson.add(qJson);
		}
		return resultJson;
	}

	@Override
	public boolean removeQuestionOfExamByIds(int examId, int[] ids) {
		return examDao.removeQuestionOfExam(examId,ids);
	}  
}
