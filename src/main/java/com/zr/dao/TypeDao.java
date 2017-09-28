package com.zr.dao;

import com.zr.model.Type;

public interface TypeDao {

	/**
	 * 获取题型信息
	 * @param typeId
	 * @return
	 */
	Type getTypeById(int typeId);
	
}
