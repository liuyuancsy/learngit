package com.tangdi.wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tangdi.wechat.rsp.Article;
import com.tangdi.wechat.rsp.NewsMessage;
import com.tangdi.wechat.rsp.TextMessage;
import com.tangdi.wechat.utils.MessageUtil;
/**
 * 核心服务类
 * @author Administrator
 *
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
   public static String  processRequest(HttpServletRequest request){
	   
	   String respMessage = null;
	    try {
	     // 默认返回的文本消息内容
	     String respContent = "请求处理异常，请稍候尝试！";
	     // xml 请求解析
	     Map<String, String> requestMap = MessageUtil.parseXml(request);
	     // 发送方帐号（open_id）
	     String fromUserName = requestMap.get("FromUserName").trim();
	     // 公众帐号
	     String toUserName = requestMap.get("ToUserName").trim();
	     // 消息类型
	     String msgType = requestMap.get("MsgType").trim();
	     // 默认回复此文本消息
	     TextMessage textMessage = new TextMessage();
	     textMessage.setToUserName(fromUserName);
	     textMessage.setFromUserName(toUserName);
	     textMessage.setCreateTime(new Date().getTime());
	     textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	     textMessage.setFuncFlag(0);
//	     textMessage.setContent("欢迎访问<a href=\"http://www.tangdi.com.cn/\">上海棠棣首页</a>");
	   
	     textMessage.setContent(TodayInHistoryService.getTodayInHistoryInfo());
	     //将文本消息转换成xml字符串
	     respMessage=MessageUtil.textMessageToXml(textMessage);
	     // 文本消息
	     if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
	    	 //接收用户发送的文本消息内容
	    	 String content = requestMap.get("Content").trim();
//	    	 //调用百度翻译接口！！！
	    if("fy".equals(content.substring(0, 2))){
	    	textMessage.setContent(BaiDuTranslateService.translate(content).substring(2));
	    	respMessage=MessageUtil.textMessageToXml(textMessage);
	    }
	     }
	     } catch (Exception e) {
	     e.printStackTrace();
	     }
	     return respMessage ;
	     }
   
   
   public static String emoji(int hexEmoji) {
	     return String.valueOf(Character.toChars(hexEmoji));
	    }
   }

