package com.falcon.avisep.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.falcon.avisep.model.Role;
import com.falcon.avisep.model.UserAvis;

public class utilMethod {
	
	public static <E> List<E> toList(Iterable<E> iterable) {
		if(iterable instanceof List) {
			return (List<E>) iterable;
		}
		ArrayList<E> list = new ArrayList<E>();
		if(iterable != null) {
			for(E e: iterable) {
				if(e!=null)list.add(e);
			}
		}
		return list;
	}
	public static List<String> takeChamps(List<String> listS, int indexOfChamps) {
		List<String> listChamps=new ArrayList<String>();
		for(int i1=indexOfChamps;i1<=indexOfChamps && i1<listS.size();i1++){
			String s=listS.get(i1);
			int val[]=new int[4];
			int t=0;
			for(int i11=0;i11!=s.length();i11++){
				if(s.charAt(i11)==','){
					val[t]=i11;
					t++;
				}
			}
			//System.out.println(getString(listS.get(i1).substring(0, val[0]),0));
			listChamps.add(getString(listS.get(i1).substring(0, val[0]),0));
			if(t==1){
				//System.out.println(getString(listS.get(i1).substring(val[0]+2,listS.get(i1).length()),0) );
				listChamps.add(getString(listS.get(i1).substring(val[0]+2,listS.get(i1).length()),0));
			}else{
			//System.out.println(getString(listS.get(i1).substring(val[0]+2,val[1]),0));
			listChamps.add(getString(listS.get(i1).substring(val[0]+2,val[1]),0));
			//System.out.println(getString(listS.get(i1).substring(val[1]+2, listS.get(i1).length()),0));
			listChamps.add(getString(listS.get(i1).substring(val[1]+2, listS.get(i1).length()),0));
			}
		}
		return listChamps;
	}

	public static List<String> takeListFromMap(Map<String, Object> jsonObjectBuilderForm) {
		int i=0;
		int start=0;
		int end=0;
		List<String> listS=new ArrayList<String>();
		Map<String, Object>  json=jsonObjectBuilderForm;
		for (Entry<String, Object> entry : json.entrySet()) {
//			System.out.println("entry key "+i+": "+entry.getKey());
			System.out.println("Object value "+i+": "+entry.getValue());
			String s=String.valueOf(entry.getValue());
			for(int i1=0;i1<s.length();i1++){
				if(s.charAt(i1)=='{'){
					start=i1+1;
				}
				if(s.charAt(i1)=='}'){
					end=i1;
					listS.add(s.substring(start, end));
				}
			}
			i++;
		}
		return listS;
	}
	public static String takeFromTitle(Map<String, Object> jsonObjectBuilderForm) {
		int i=0;
		List<String> listS=new ArrayList<String>();
		Map<String, Object>  json=jsonObjectBuilderForm;
		for (Entry<String, Object> entry : json.entrySet()) {
//			System.out.println("entry key "+i+": "+entry.getKey());
			System.out.println("Object value "+i+": "+entry.getValue());
			String s=String.valueOf(entry.getValue());
				if(i==0){
					return String.valueOf(entry.getValue());
				}
			i++;
		}
		return null;
	}
	public static String getString(String s, int i){
		//if (s.charAt(i)=='=') return s.substring(i+1, searchEnd(s,i));
		if (s.charAt(i)=='=') return s.substring(i+1);
		else return getString(s,i+1);
	}
	public String getS(String s, int i){
		if (s.charAt(i)=='=') return s.substring(i+1, searchEnd(s,i));
		//if (s.charAt(i)=='=') return s.substring(i+1);
		else return getString(s,i+1);
	}
	public int searchEnd(String s, int i){
		if (s.charAt(i)==',') return i;
		else return searchEnd(s,i+1);
	}
	public JSONObject getJsonFromMap(Map<String, Object> map) throws JSONException {
		JSONObject jsonData = new JSONObject();
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof Map<?, ?>) {
				value = getJsonFromMap((Map<String, Object>) value);
			}
			jsonData.put(key, value);
		}
		return jsonData;
	}
	public static List<String> parseOptions(String s){
		
		s.replaceAll("\\[", "").replaceAll("\\]", "");
		String[] chaine=s.split(", ");
		List<String> options=Arrays.asList(chaine);
		
//		while(i<s.length()){
//			if(s.charAt(i)!=',' && s.charAt(i)!='['){
//				chaine=chaine+s.charAt(i);
//			}
//			if(s.charAt(i)==']'){
//				options.add(chaine);
//				break;
//			}
//			if(!(s.charAt(i)!=',' && s.charAt(i)!='[')){
//				options.add(chaine);
//			}
//			i++;
//		}
		return options;
		
	}
	public static List<String> getF(List<String> listS) {
		List<String> l=new ArrayList<String>();
		for(int i=0;i<4;i++){
			List<String> listChamps=utilMethod.takeChamps(listS,i);
			for(int j=0;j<listChamps.size();j++){
				//System.out.println(listChamps.get(j));
				switch (j)
				{
				  case 0:
					  l.add(j,listChamps.get(j));
				    System.out.println("Type :"+listChamps.get(j));
				    break;
				  case 1:
					  l.add(j,listChamps.get(j));
				    System.out.println("Title :"+listChamps.get(j));
				    break;
				  default:
					  l.add(j,listChamps.get(j));
				    System.out.println("Options :"+listChamps.get(j));
				}
			}
		}
		
		return listS;
	}
	
	
	public static String getBaseUrl(HttpServletRequest request) {
		UserAvis user=((UserAvis )request.getSession().getAttribute("userLogged"));
		Role role= user.getRole();
		switch (role)
		{
		case STUDENT:
			return "welcomeS";
		case VTEACHER:
			return "welcomeT";
		case ADMIN:
			return "welcomeA";
		case VTEACHER_and_ETEACHER:
			return "welcomeT";
		default:
			return "index";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}
	public static List<Object> takeValuesFromMap(List<String> keysList, Map<String, Object> map) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < keysList.size(); i++) {

			String s=removeChar(map.get(keysList.get(i)).toString(), '[');
			s=removeChar(s, ']');
			list.add(i,s);
		}
		return list;
	}
	public static String removeChar(String s, char c) {
		StringBuffer r = new StringBuffer( s.length() );
		r.setLength( s.length() );
		int current = 0;
		for (int i = 0; i < s.length(); i ++) {
			char cur = s.charAt(i);
			if (cur != c) r.setCharAt( current++, cur );
		}
		return r.toString();
	}
	
}
