package com.study.servlet_study.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParamsConverter {
	public static Map<String, String> convertParamToMap(Map<String, String[]> paramsMap) {
		Map<String, String> map = new HashMap<>();
		
		paramsMap.forEach((k, v) -> {
			StringBuilder b = new StringBuilder();
			
			Arrays.asList(v).forEach(value -> b.append(value));
			
			map.put(k, b.toString());
		});
		
		return map;
	}
}