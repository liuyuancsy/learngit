package com.tangdi.wechat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 历史上的今天查询服务
 * @author Administrator
 *
 */
public class TodayInHistoryService {
	/**
	 * 发起http请求获取网页
	 * @param requestUrl
	 * @return
	 */
	public static String  httpRequest(String requestUrl){
		StringBuffer buf = 	null;
		try {
			//建立连接
		
			URL url = new URL(requestUrl);
				HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
				httpURLConnection.setDoInput(true);
				httpURLConnection.setRequestMethod("GET");
				System.out.println("建立连接---");
				//获取输入流
				InputStream  inputStream = httpURLConnection.getInputStream();
				InputStreamReader inputStreamReader  = new InputStreamReader(inputStream,"utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				System.out.println("获取输入流---");
				buf = new StringBuffer();
				String str = null;
				while((str=bufferedReader.readLine())!=null){
					buf.append(str);
				}
			//释放资源
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				httpURLConnection.disconnect();
				System.out.println("释放资源---");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println("buf---"+buf.toString());
		return buf.toString();
	}
	/**
	 * 从html中抽取历史上的今天信息
	 * @param html
	 * @return
	 */
	public static String extract(String html){
		StringBuffer buf = null;
		//日期标签，区分是昨天还是今天
		String dateTag = getMonthDay(0);//今天
		// 将给定的正则表达式编译到模式中。
		Pattern p = Pattern.compile("(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)");	
		Matcher m = p.matcher(html);
		if(m.matches()){
			System.out.println("=======匹配分割线======");
			buf = new StringBuffer();
			if(m.group(3).contains(getMonthDay(-1)))
				dateTag=getMonthDay(-1);
				//拼装标题
				buf.append("<a href=\"http://www.baidu.com/\" align=\"center\" color=\"blue\">≡≡历史上的"+dateTag+"≡≡</a>").append("\n\n");
				//抽取需要的数据
				System.out.println("-----抽取所需数据-----");
				for(String info:m.group(3).split("  ")){
					info = info.replace(dateTag, "").replace("（图）", " ").replaceAll("</?[^>]+>", "").replaceAll("&nbsp;", "").trim();
					//在每行末尾追加两个换行符
					if(!"".equals(info)){
						buf.append(info).append("\n\n");
					}
				}
		}
		return (null==buf) ? null : buf.substring(0, buf.lastIndexOf("\n\n"));
	}
	/**
	 * 封装历史上的今天，供外部调用
	 * @return
	 */
	public static String getTodayInHistoryInfo(){
		//获取网页源代码
		String html = httpRequest("http://www.rijiben.com/");
		//从网叶中抽取信息
		String result  = extract(html);
		return  result;
	}
	/**
	 * 获取前/后 n天日期
	 * @return
	 */
	public static String getMonthDay(int diff){
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		Calendar cc = Calendar.getInstance();
		cc.add(Calendar.DAY_OF_YEAR, diff);
		String date = sdf.format(cc.getTime());
		return date;
	}

	public static void main(String[] args) {
		System.out.println("statr+++++++++");
		String info = getTodayInHistoryInfo();
		System.out.print(info);
	}
}
