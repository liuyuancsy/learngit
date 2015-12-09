package com.tangdi.wechat.rsp;
/**
 * 文本信息
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
      private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
