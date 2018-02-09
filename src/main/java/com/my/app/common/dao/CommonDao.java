package com.my.app.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao {

	@Autowired
	private SqlSession sqlSession;

	public <E> List<E> selectList(String statement) {
		return sqlSession.selectList(statement);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	public <T> T selectOne(String statement) {
		return sqlSession.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return sqlSession.selectMap(statement, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		return sqlSession.selectMap(statement, parameter, mapKey);
	}

	public int insert(String statement, Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}

}
