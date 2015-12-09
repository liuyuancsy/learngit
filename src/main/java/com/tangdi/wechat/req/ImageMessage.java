package com.tangdi.wechat.req;
/**
 * 请求消息之图片消息
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage {
	//图片链接
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	private String PicUrl;

}
