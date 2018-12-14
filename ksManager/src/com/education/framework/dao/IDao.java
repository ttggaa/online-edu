package com.education.framework.dao;

import java.util.List;

import com.education.framework.domain.SearchParams;
import com.education.framework.page.Page;

public interface IDao<T> {

	public List<T> find(SearchParams searchParams, Page page);
	
	public int save(T obj);
	
	public T findForObject(Integer id) ;
	
	public void update(T obj);
	
	public void delete(Integer id);
	
	public boolean findIsExist(String name);
}
