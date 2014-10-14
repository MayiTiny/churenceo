package com.refferal.crawler.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.refferal.crawler.JDCrawler;
import com.refferal.dao.JobDescriptionDao;
import com.refferal.entity.JobDescription;
import com.refferal.enums.AliCategoryEnum;
import com.refferal.enums.CompanyEnum;

@Service
public class AliCrawler implements JDCrawler {

	@Autowired
	private JobDescriptionDao jobDescriptionDao;

	private static final String ALI_URL = "http://job.alibaba.com/zhaopin/socialPositionList/doList.json?pageSize=10&pageIndex=";

	/**
	 * @param args
	 * @throws Exception
	 */
	public void startCrawl() throws Exception {
		jobDescriptionDao.deleteAll();
		HttpClient httpclient = new DefaultHttpClient();
		int index = 1;
		while (true) {
			HttpGet httpget = new HttpGet(ALI_URL + index);
			index++;
			System.out.println(index);
			httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();
			String result = inStream2String(is);
			try {
				JSONObject totalJson = (JSONObject) JSONObject.parse(result);
				JSONObject returnValue = (JSONObject) totalJson
						.get("returnValue");
				JSONArray jobs = (JSONArray) returnValue.get("datas");
				for (int i = 0; i < jobs.size(); i++) {
					JSONObject job = jobs.getJSONObject(i);
					JobDescription jobDesc = new JobDescription();
					jobDesc.setCompany(CompanyEnum.ALIBABA.getCompanyId());
					jobDesc.setDegree(job.getString("degree"));
					jobDesc.setName(job.getString("name"));
					// 默认全部是社招
					jobDesc.setRecruitType(1);
					jobDesc.setHeadCount(job.getIntValue("recruitNumber"));
					if (jobDesc.getHeadCount() == 0) {
						jobDesc.setHeadCount(1);
					}
					jobDesc.setDepartment(job.getString("departmentName"));
					jobDesc.setPostRequire(job.getString("requirement"));
					jobDesc.setPostDescription(job.getString("description"));
					jobDesc.setYearsLimit(job.getString("workExperience"));
					// 解析预留
					jobDesc.setFunctionType(AliCategoryEnum.getCodeByName(job
							.getString("firstCategory")));
					jobDesc.setCityId(job.getString("workLocation"));
					jobDescriptionDao.insert(jobDesc);
					// TODO 插入数据库
				}
				int totalPage = Integer.valueOf(returnValue.get("totalPage")
						.toString());
				if (index > totalPage) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			EntityUtils.consume(response.getEntity());
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
