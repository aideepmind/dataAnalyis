package com.hejia.dataAnalysis.module.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 拷贝，只拷贝非空属性（目前只支持拷贝String和Integer、int、Long、long、Double、double、Short、short、Boolean、boolean等）
 * @author chenyq
 *
 */
public class BeanCopyUtils {
	
	/**
	 * @Definition: 把orig对象中的非空属性拷贝到dest对象中（改进版）
	 * @author: chenyongqiang
	 * @Date: 2016年2月28日
	 * @param orig
	 * @param dest
	 * @param isEmptyCopy
	 * @throws Exception
	 */
	public static void copyProperties(Object orig, Object dest, boolean isEmptyCopy) throws Exception {
		Class origClass = orig.getClass();
		Class destClass = dest.getClass();
		Method origMethods[] = origClass.getMethods();
		try {
			for (int i = 0; i < origMethods.length; i++) {
				Method origMethod = origMethods[i];
				String origMethodName = origMethod.getName();
				Class origMethodParams[] = origMethod.getParameterTypes();
				Class origMethodReturnType = origMethod.getReturnType();
				if (origMethodName.startsWith("get") && origMethodParams.length == 0 && judgeType(origMethodReturnType)) {
					Object value = origMethod.invoke(orig);
					if (!isEmptyCopy && value == null) continue;
					Method destMethod = destClass.getMethod("set" + origMethodName.substring(3), origMethodReturnType);
					if (destMethod == null || !destMethod.getReturnType().getName().equals(void.class.getName())) continue;
					if (origMethodReturnType.getName().endsWith("String")) {
						if (!isEmptyCopy && "".equals(value)) continue;
						value = new String((String) value);
					}
					destMethod.invoke(dest, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @Definition: 把orig对象中的非空属性拷贝到dest对象中（json版）
	 * 1.支持任何类型，调用时orig和dest可以不是同一类对象
	 * 2.数组的话，直接把orig中的所有元素拷贝到dest中
	 * 3.对象的话，只管拷贝属性值，而不管属性值是否同类型
	 * @author: chenyongqiang
	 * @Date: 2016年5月13日
	 * @param orig 源对象
	 * @param dest 目标对象
	 * @param isEmptyCopy 空的时候是否copy
	 * @throws Exception
	 * @return
	 */
	public static String copyPropertiesForJson(Object orig, Object dest, boolean isEmptyCopy) throws Exception {
		Object o = null;
		Object d = null;
		//支持任何类型，调用时orig和dest可以不是同一类对象
		if (orig instanceof net.sf.json.JSONObject || orig instanceof net.sf.json.JSONArray) {
			o = orig;
		} else if (orig instanceof java.util.Collection || (orig instanceof java.lang.String && ((java.lang.String) orig).startsWith("["))) {
			o = JSONArray.fromObject(orig);
		} else {
			o = JSONObject.fromObject(orig);
		}
		if (dest instanceof net.sf.json.JSONObject || dest instanceof net.sf.json.JSONArray) {
			d = dest;
		} else if (dest instanceof java.util.Collection || (dest instanceof java.lang.String && ((java.lang.String) dest).startsWith("["))) {
			d = JSONArray.fromObject(dest);
		} else {
			d = JSONObject.fromObject(dest);
		}
		//同类型才复制
		if (o instanceof net.sf.json.JSONArray && d instanceof net.sf.json.JSONArray) {//数组的话，直接把orig中的所有元素拷贝到dest中
			JSONArray o2 = (JSONArray) o;
			JSONArray d2 = (JSONArray) d;
			for (int i = 0; i < o2.size(); i++) {
				d2.add(o2.get(i));
			}
		} else if (o instanceof net.sf.json.JSONObject && d instanceof net.sf.json.JSONObject) {//对象的话，只管拷贝属性值，而不管属性值是否同类型
			JSONObject o2 = (JSONObject) o;
			JSONObject d2 = (JSONObject) d;
			Iterator it = o2.keys();
			while (it.hasNext()) {
				Object key = it.next();
				Object value = o2.get(key);
				if (isEmptyCopy || (value != null && !"".equals(value.toString()))) {
					d2.put(key, value);
				}
			}
		}
		return d.toString();
	}
	
	/**
	 * 把orig对象中的非空属性拷贝到dest对象中（当前的方法代理对象中取不到值，因为代理对象可以没有属性，而只有方法，需要改进）
	 * @param orig 源对象
	 * @param dest 目标对象
	 * @throws Exception 
	 */
	public static void copyProperties2(Object orig, Object dest) throws Exception {
		Class origClass = orig.getClass();
		Class destClass = dest.getClass();
		Method origMethods[] = origClass.getMethods();
		Method destMethods[] = destClass.getMethods();
		Field origFields[] = origClass.getDeclaredFields();
		Field destFields[] = destClass.getDeclaredFields();
		if (origFields.length == 0 || destFields.length == 0) {
			return;
		}
		try {
			loop : for (int i = 0; i < origFields.length; i++) {
				Field origField = origFields[i];
				Class origFieldType = origField.getType();
				if (!judgeType(origFieldType)) {
					continue;
				}
				for (int j = 0; j < destFields.length; j++) {
					Field destField = destFields[j];
					Class destFieldType = destField.getType();
					if (origField.getName().equals(destField.getName()) && origFieldType.getName().equals(destFieldType.getName())) {
						String fieldName = origField.getName();
//						Method origMethod = origClass.getMethod("get" + StringTools.firstCharactorUpper(fieldName),null);
						Method origMethod = getMothod(origMethods, "get" + StringTools.firstCharactorUpper(fieldName));
						if (origMethod == null) {
							continue loop;
						}
//						Method destMethod = destClass.getMethod("set" + StringTools.firstCharactorUpper(fieldName), origFieldType);
						Method destMethod = getMothod(destMethods, "set" + StringTools.firstCharactorUpper(fieldName));
						if (destMethod == null) {
							continue loop;
						}
						Object value = origMethod.invoke(orig);
						if (value == null) {
							continue loop;
						}
						if (origFieldType.getName().endsWith("Integer")) {
							value = new Integer((Integer)value);
						}
						if (origFieldType.getName().endsWith("Long")) {
							value = new Long((Long)value);
						}
						if (origFieldType.getName().endsWith("Double")) {
							value = new Double((Double)value);
						}
						if (origFieldType.getName().endsWith("Short")) {
							value = new Short((Short)value);
						}
						if (origFieldType.getName().endsWith("Boolean")) {
							value = new Boolean((Boolean)value);
						}
						if (origFieldType.getName().endsWith("String")) {
							if ("".equals(value)) 
								continue loop;
							value = new String((String)value);
						}
						destMethod.invoke(dest, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 根据方法名称获取方法
	 * @param methods
	 * @param methodName
	 * @return
	 */
	private static Method getMothod(Method[] methods, String methodName){
		if (methods == null || methods.length == 0 || methodName == null || "".equals(methodName)) 
			return null;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				return methods[i];
			}
		}
		return null;
	}
	/**
	 * 
	 * @param typeClass
	 * @return
	 */
	private static boolean judgeType(Class typeClass){
		if (typeClass.getName().equals(String.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(int.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(Integer.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(long.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(Long.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(double.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(Double.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(short.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(Short.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(boolean.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(Boolean.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(Date.class.getName())) {
			return true;
		}
		if (typeClass.getName().equals(java.sql.Date.class.getName())) {
			return true;
		}
		return false;
	}
}
