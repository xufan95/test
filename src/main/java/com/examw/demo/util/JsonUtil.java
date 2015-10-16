package com.examw.demo.util;



import net.sf.json.JSONArray;

public class JsonUtil {

	/**
	 * 转换成JsonArray数组
	 * @param list
	 * @return array
	 * @throws Exception
	 */
	public static JSONArray formatRsToJsonArray(Object o)throws Exception{
		JSONArray array= JSONArray.fromObject(o);
		return array;
	}
}
