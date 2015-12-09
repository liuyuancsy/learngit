package com.tangdi.wechat.service;

import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.tangdi.wechat.translate.TranslateResult;

public class BaiDuTranslateService extends TodayInHistoryService{
	/**
	 * utf-8编码
	 * @param source
	 * @return
	 */
	public static String  urlEncodeUTF8(String source){
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String translate(String source){
	      String dest =null;
	      //组装查询地址
	      String requestURL =  "http://openapi.baidu.com/public/2.0/bmt/translate?" +
	      		"client_id=vgjhOcR9Yk5lNK7o0vh1SKZF&q={keyWord}&from=auto&to=auto";
	      // 对参数 q 的值进行 urlEncode utf-8 编码
	      requestURL = requestURL.replace("{keyWord}", urlEncodeUTF8(source));
	   // 查询并解析结果
	      try{
	      String json = httpRequest(requestURL);
	      //通过Gson工具将json转换成TranslateResult对象
	      TranslateResult result = new Gson().fromJson(json, TranslateResult.class);
	      //取出translateResult 中的译文
	      dest = result.getTrans_result().get(0).getDst();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	      if(null==dest){
	    	  System.out.println("操作异常，请检查代码");
	    	  dest="系统异常，请稍后再试";
	      }
	      return dest;
	}
	
	public static void main(String[] args) {
		String ss = "百度的翻译功能真强大！";
		System.out.println(translate(ss));
	}
	
	
	

}
