package com.education.framework.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class SearchParams {
	private Map<String, Object> map = new LinkedHashMap<String, Object>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Object get(String mapKey){
		return map.get(mapKey);
	}
}
