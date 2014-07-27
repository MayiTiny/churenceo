package com.refferal.crawler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class AliCrawler {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HttpClient httpclient = new DefaultHttpClient();
		int index = 1;
		while (true) {
			HttpGet httpget = new HttpGet(
					"http://job.alibaba.com/zhaopin/socialPositionList/doList.json?pageSize=10&pageIndex="
							+ index++);
		httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
		HttpResponse response = httpclient.execute(httpget); 
		InputStream is = response.getEntity().getContent();  
		String result = inStream2String(is);
		System.out.println(result);
		EntityUtils.consume(response.getEntity());
		Thread.sleep(5000L);
		}
	}
	private static String inStream2String(InputStream in) throws Exception { 
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf8"));
		String tempbf;
		StringBuffer html = new StringBuffer(100);
		boolean addEnter = false;
		while ((tempbf = br.readLine()) != null) {
			if(!addEnter){
				html.append(tempbf);
				addEnter = true;
			}else{
				html.append(tempbf +"\n");
			}
		}
       return html.toString();  
    }  

}
