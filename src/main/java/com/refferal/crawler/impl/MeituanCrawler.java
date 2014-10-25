package com.refferal.crawler.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.refferal.crawler.JDCrawler;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.enums.CompanyEnum;
import com.refferal.enums.MeituanCategoryEnum;

public class MeituanCrawler implements JDCrawler{

	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	
	private static final String HOME_PAGE = "http://www.hotjob.cn/wt/meituan/web/index/webPosition100!getPostList?pc.rowSize=100&recruitType=2&blockType=1&pc.currentPage=1";

	public void startCrawl() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(HOME_PAGE);
		httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
		HttpResponse response = httpclient.execute(httpget);
		InputStream is = response.getEntity().getContent();
		String result = inStream2String(is);
		System.out.println(result);
		Pattern p = Pattern.compile("现在最大页码数为(\\d+)");
		Matcher mat = p.matcher(result);
		int totalPages = 1;
		if (mat.find()) {
			totalPages = Integer.valueOf(mat.group(1));
		}
		getAllJobs(totalPages);
	}

	private  void getAllJobs(int totalPages) throws Exception{
		HttpClient httpclient = new DefaultHttpClient();
		for (int i = 1; i <= totalPages; i++) {
			String url = "http://www.hotjob.cn/wt/meituan/web/index/webPosition100!getPostList?pc.rowSize=100&recruitType=2&blockType=1&pc.currentPage="
					+ i;
			HttpGet httpget = new HttpGet(url);
			httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();
			String result = inStream2String(is);
			Document doc = Jsoup.parse(result);
			for(Element job : doc.getElementsByClass("table_newjob_01_left")){
				String onClick = job.getAllElements().get(1).attr("onclick");
				String jobId =  onClick.split("\"")[1];
				getJobDetail(jobId);
			}
		}
	}
	
	private  void getJobDetail(String jobId) throws Exception{
		HttpClient httpclient = new DefaultHttpClient();
		String url = "http://www.hotjob.cn/wt/meituan/web/index/webPosition100!getOnePosition?recruitType=100&postId=" + jobId;
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
		HttpResponse response = httpclient.execute(httpget);
		InputStream is = response.getEntity().getContent();
		String result = inStream2String(is);
		Document doc = Jsoup.parse(result);
		JobDescription jobDesc = new JobDescription();
		jobDesc.setName(doc.getElementsByClass("jobshow_jobname").get(0).text());
		Elements rights = doc.getElementsByClass("jobshow_jobmain_right");
		jobDesc.setDepartment(rights.get(0).text());
		jobDesc.setFunctionType(MeituanCategoryEnum.getCodeByName(rights.get(0).text()));
		jobDesc.setCityId(rights.get(2).text());
		jobDesc.setDegree(rights.get(3).text());
		if(StringUtils.isNotBlank(rights.get(6).text()) && !rights.get(6).text().contains("若干")){
			jobDesc.setHeadCount(Integer.valueOf(rights.get(6).text()));
		}else{
			jobDesc.setHeadCount(-1);
		}
		jobDesc.setYearsLimit(rights.get(7).text());
		jobDesc.setPostDescription(doc.getElementsByClass("jobshow_jobmain_02").get(0).text());
		jobDesc.setPostRequire(doc.getElementsByClass("jobshow_jobmain_02").get(1).text());
		jobDesc.setCompany(CompanyEnum.MEITUAN.getCompanyId());
		jobDescriptionDao.insert(jobDesc);
	}

	private static String inStream2String(InputStream in) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(in, "utf8"));
		String tempbf;
		StringBuffer html = new StringBuffer(100);
		boolean addEnter = false;
		while ((tempbf = br.readLine()) != null) {
			if (!addEnter) {
				html.append(tempbf);
				addEnter = true;
			} else {
				html.append(tempbf + "\n");
			}
		}
		return html.toString();
	}

}
