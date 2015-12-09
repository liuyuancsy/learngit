package com.tangdi.wechat.translate;

import java.util.List;

/**
 * 封装百度翻译结果的类
 * @author Administrator
 *
 */
public class TranslateResult {
	private String from;
	private String to;
	private List<ResultList>  trans_result;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
//	public List<ResultList> getTrans_result() {
//		return trans_result;
//	}
	public void setTrans_result(List<ResultList> trans_result) {
		this.trans_result = trans_result;
	}
	public List<ResultList> getTrans_result() {
		// TODO Auto-generated method stub
		return trans_result;
	}
	

}
