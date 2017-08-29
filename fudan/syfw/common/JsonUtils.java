package org.wuxi.fudan.syfw.common;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
/**
 * 抽取出来的json工具类，主要用于处理前端的ajax请求
 * @author Administrator
 *
 */
public class JsonUtils {
	public static final String DEFAULT_JSON_DATA = "aaData";

	public static final String DEFAULT_JSON_TOTAL_PROPERTY = "iTotalRecords";

	public static final String DEFAULT_JSON_SECHO = "sEcho";

	public static final String DEFAULT_JSON_RECORDSFILTERED = "iTotalDisplayRecords";

	public static final String DEFAULT_JSON_MESSAGE = "message";

	public static final String DEFAULT_JSON_SUCCESS = "success";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	

	



	public static JSONObject toJSONResult(long count, List data) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}

	/**
	 * 千万注意，若sEcho为null的时候将会出现异常，数据将无法加载出来
	 * @param count
	 * @param data
	 * @param sEcho
	 * @return
	 */
	public static JSONObject toJSONResult(long count, List data, int sEcho) {
		//处理jsonObject 嵌套数据过滤的问题
		JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.registerJsonValueProcessor(Set.class, null);
		jsonConfig.registerJsonValueProcessor(Float.class, new JsonFloatValueProcessor());
		jsonConfig.setExcludes( new String[]{ "handler","hibernateLazyInitializer","traces"} );
		//自动加载list中每一项包含的set，耗时耗资源
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONArray json = JSONArray.fromObject(data, jsonConfig);
				//JSONObject json = JSONObject.fromObject(data,jsonConfig);
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_RECORDSFILTERED, count);
		result.put(DEFAULT_JSON_DATA, json);
		result.put(DEFAULT_JSON_SECHO, sEcho);
		result.put(DEFAULT_JSON_SUCCESS, true);
		return result;
	}
	
	public static JSONObject toJSONResult(long count, List data, int sEcho, String[] exclude) {
		//处理jsonObject 嵌套数据过滤的问题
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Float.class, new JsonFloatValueProcessor());
		
		jsonConfig.setExcludes(exclude);
		//自动加载list中每一项包含的set，耗时耗资源
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONArray json = JSONArray.fromObject(data, jsonConfig);
				//JSONObject json = JSONObject.fromObject(data,jsonConfig);
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_RECORDSFILTERED, count);
		result.put(DEFAULT_JSON_DATA, json);
		result.put(DEFAULT_JSON_SECHO, sEcho);
		result.put(DEFAULT_JSON_SUCCESS, true);
		return result;
	}

	public static JSONObject toJSONResult(boolean success, String message) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		if (!GeneralUtils.isNullOrZeroLenght(message)) {
			result.put(DEFAULT_JSON_MESSAGE, message);
		}
		return result;
	}

	public static JSONObject toJSONResult(boolean success) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		return result;
	}

	public static JSONObject toJSONResult(boolean success, Object data, String[] exclude) {
		//处理jsonObject 嵌套数据过滤的问题
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(exclude);
		//自动加载list中每一项包含的set
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		//jsonConfig.registerJsonValueProcessor(Set.class, null);
		jsonConfig.registerJsonValueProcessor(Float.class, new JsonFloatValueProcessor());
		JSONArray json = JSONArray.fromObject(data, jsonConfig);
		//JSONObject json = JSONObject.fromObject(data,jsonConfig);
		
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, json);
		return result;
	}
	
	public static JSONObject toJSONResult(boolean success, Object data) {
		//处理jsonObject 嵌套数据过滤的问题
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Float.class, new JsonFloatValueProcessor());
		//将代理类型的属性过滤掉，即因延迟加载自动为对象生成的属性handler、hibernateLazyInitializer。
		jsonConfig.setExcludes( new String[]{ "handler","hibernateLazyInitializer","traces"} );
		//自动加载list中每一项包含的set
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Set.class, null);
		JSONArray json = JSONArray.fromObject(data, jsonConfig);
		//JSONObject json = JSONObject.fromObject(data,jsonConfig);
		
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, json);
		return result;
	}

	public static Map<String, Object> toJSONResultMap(boolean success, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}

	public static Map<String, Object> toJSONResultMap(boolean success, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_MESSAGE, msg);
		return result;
	}

	public static JSONArray toJSONArrayResult(List data) {
		return JSONArray.fromObject(data);
	}

	public static Pageable convert2Pageable(int start, int limit) {
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		Pageable pageable = new PageRequest(start / limit, limit);
		return pageable;
	}


}
