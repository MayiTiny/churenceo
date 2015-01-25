package com.refferal.crawler.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.refferal.crawler.JDCrawler;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.enums.CompanyEnum;
import com.refferal.enums.VIPCategoryEnum;

public class VipCrawler implements JDCrawler {

	private String VIP_JOB_PAGE = "http://vipshop.hirede.com/Job/SearchJob";

	@Autowired
	private JobDescriptionDao jobDescriptionDao;
	
	public void startCrawl() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		for (int i = 1; i <= 15; i++) {
			JobDescription jd = new JobDescription();
			jd.setCompany(CompanyEnum.VIP.getCompanyId());
			HttpPost post = new HttpPost(VIP_JOB_PAGE + "?pageIndex=" + i);
			post.addHeader("Content-Type", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
			HttpParams params = new BasicHttpParams();
			params.setParameter("recruiteType", 1);
			post.setParams(params);
			HttpResponse response = httpclient.execute(post);
			InputStream is = response.getEntity().getContent();
			String result = inStream2String(is);
			Document doc = Jsoup.parse("<html><body><table>" + result + "</table></body></html>");
			Elements jobs = doc.getElementsByClass("single");
			for(Element job : jobs){
				Elements tds = job.getElementsByTag("td");
				String nameAndDept = tds.get(0).text();
				jd.setName(nameAndDept.split("-")[0]);
				if(nameAndDept.contains("-")){
					jd.setDepartment(nameAndDept.split("-")[1]);
				}else{
					jd.setDepartment("");
				}
				jd.setFunctionType(VIPCategoryEnum.getCodeByName(tds.get(1).text()));
				jd.setCityId(tds.get(2).text().split("-")[0]);
				String headCount = tds.get(3).text();
				if(headCount.contains("人")){
					jd.setHeadCount(Integer.valueOf(headCount.split("人")[0]));
				}else{
					jd.setHeadCount(-1);
				}
				jd.setDegree("本科");
				Element detail = job.nextElementSibling();
				Elements jobDesc = detail.getElementsByClass("jobcolumn");
				String desc = "";
				if(!jobDesc.isEmpty() && !jobDesc.get(0).getElementsByTag("p").isEmpty()){
//					System.out.println(jobDesc.get(0).html());
					for(Element p : jobDesc.get(0).getElementsByTag("p")){
						desc += p.text();
					}
//					if(!jobDesc.get(0).getElementsByTag("span").isEmpty()){
//						for(Element p : jobDesc.get(0).getElementsByTag("span")){
//							desc += p.text();
//						}
//					}
					
				}else{
					desc= jobDesc.get(0).text();
				}
				desc = desc.replace("；","；</br>");
				desc = desc.replace("：","：</br>");
				desc = desc.replace("】", "】</br>");
				desc = desc.replace("。", "。</br>");
				jd.setYearsLimit("不限");
				jd.setPostDescription(desc.trim());
				int isExsit = jobDescriptionDao.selectExsit(jd);
				if(isExsit == 0){
					jobDescriptionDao.insert(jd);
				}else{
					jobDescriptionDao.updateById(isExsit);
				}
				
			}
			Thread.sleep(1000L);
		}

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
