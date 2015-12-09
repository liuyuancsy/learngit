package com.weChat.TEST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tangdi.wechat.rsp.Article;
import com.tangdi.wechat.rsp.NewsMessage;
import com.tangdi.wechat.rsp.TextMessage;
import com.tangdi.wechat.service.BaiDuTranslateService;
import com.tangdi.wechat.service.TodayInHistoryService;
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
//	    	 //创建图文消息
//	    	 NewsMessage newsMessage = new NewsMessage();
//	    	 newsMessage.setFromUserName(fromUserName);
//	    	 newsMessage.setToUserName(toUserName);
//	    	 newsMessage.setCreateTime(new Date().getTime());
//	    	 newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//	    	 newsMessage.setFuncFlag(0);
//	    	 //调用百度翻译接口！！！
	    if("fy".equals(content.substring(0, 2))){
	    	textMessage.setContent(BaiDuTranslateService.translate(content).substring(2));
	    	respMessage=MessageUtil.textMessageToXml(textMessage);
	    }
//	    	 
//	    	 
//	    	 //单图文消息--不含图片
//	    	 if("1".equals(content)){
//	    		
////	    		 Article article = new Article();
////	    		 article.setTitle("上海棠棣信息科技股份公司");
////	    		 article.setDescription("棠棣科技是一家致力于为金融机构、互联网金融企业提供软件产品和系统集成服务的高科技公司，总部位于上海，" +
////	    		 		"是上海市双软认定企业、高新技术企业，拥有数十项软件著作权，自2009年成立以来，" +
////	    		 		"先后在北京、深圳、成都、西安、济南、长春、合肥等地设立了分公司和开发中心，" +
////	    		 		"300多名技术服务人员遍布全国各大城市。");
////	    		 article.setPicUrl("");
////	    		 article.setUrl("http://www.tangdi.com.cn");
////	    		 list.add(article);
////	    		 //设置图文消息个数
////	    		 newsMessage.setArticleCount(list.size());
////	    		 //设置图文消息包含的图文集合
////	    		 newsMessage.setArticles(list);
//	    		 respMessage = MessageUtil.newsMessageToXml(newsMessage);
//	    	 }
//	    	 
////	      respContent = "您发送的是文本消息！"+emoji(0x1F6B2);
//	     }
//	     // 图片消息
//	     else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//	     respContent = "您发送的是图片消息！";
//	     }
//	    // 地理位置消息
//	     else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION))
//	   {
//	     respContent = "您发送的是地理位置消息！";
//	     }
//	     // 链接消息
//	     else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//	     respContent = "您发送的是链接消息！";
//	     }
//	     // 音频消息
//	     else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//	     respContent = "您发送的是音频消息！";
//	     }
//	     // 事件推送
//	     else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
//	     // 事件类型
//	     String eventType = requestMap.get("Event");
//	     // 订阅
//	     if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { 	 	
//	     respContent = "谢谢您的关注！";
//	     }
//	     // 取消订阅
//	     else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)
//	   ) {
//	    // TODO 取消订阅后用户再收不到公众号发送的消息， 因此不需要回复消息
//	     }
//	     // 自定义菜单点击事件
//	     else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
//	     // TODO 自定义菜单权没有开放，暂不处理该类消息
//	     }
	     }
//	     textMessage.setContent(respContent);
//	     respMessage = MessageUtil.textMessageToXml(textMessage);
	     } catch (Exception e) {
	     e.printStackTrace();
	     }
	     return respMessage ;
	     }
   
   
   public static String emoji(int hexEmoji) {
	     return String.valueOf(Character.toChars(hexEmoji));
	    }
   }

