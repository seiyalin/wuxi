package org.wuxi.fudan.syfw.common;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_RECORDSFILTERED, count);
		result.put(DEFAULT_JSON_DATA, data);
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

	public static JSONObject toJSONResult(boolean success, Object data) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, data);
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
