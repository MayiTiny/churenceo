package com.refferal.crawler.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private static final Log LOGGER = LogFactory.getLog("AliCrawler");
	
	@Autowired
	private JobDescriptionDao jobDescriptionDao;

	private static final String ALI_URL = "https://job.alibaba.com/zhaopin/socialPositionList/doList.json?pageSize=10&pageIndex=";
	
	/**
	 * @throws Exception
	 */
	public void startCrawl() throws Exception {
		int index = 0;
		while (true) {
			index++;
			// 阿里已经更新成高端的https
			URL myURL = new URL(ALI_URL + index);
			HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
			httpsConn.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String s, SSLSession sslsession) {
					return true;
				}
			});
			InputStream is = httpsConn.getInputStream();

			String result = inStream2String(is);
			JSONObject totalJson = (JSONObject) JSONObject.parse(result);
			JSONObject returnValue = (JSONObject) totalJson.get("returnValue");
			JSONArray jobs = (JSONArray) returnValue.get("datas");
			for (int i = 0; i < jobs.size(); i++) {
				JobDescription jobDesc = new JobDescription();
				try {
					JSONObject job = jobs.getJSONObject(i);
					jobDesc.setCompany(CompanyEnum.ALIBABA.getCompanyId());
					jobDesc.setDegree(job.getString("degree"));
					jobDesc.setName(job.getString("name"));
					// 默认全部是社招
					jobDesc.setRecruitType(1);
					jobDesc.setHeadCount(job.getIntValue("recruitNumber"));
					if (jobDesc.getHeadCount() == 0) {
						jobDesc.setHeadCount(-1);
					}
					jobDesc.setDepartment(job.getString("departmentName"));
					jobDesc.setPostRequire(job.getString("requirement"));
					jobDesc.setPostDescription(job.getString("description"));
					jobDesc.setYearsLimit(job.getString("workExperience"));
					jobDesc.setBeginDate(new Date(job.getLongValue("gmtModified")));
					// 解析预留
					jobDesc.setFunctionType(AliCategoryEnum.getCodeByName(job
							.getString("firstCategory")));
					jobDesc.setCityId(job.getString("workLocation"));
					int isExsit = jobDescriptionDao.selectExsit(jobDesc);
					if (0 == isExsit) {
						jobDescriptionDao.insert(jobDesc);
					} else {
						jobDescriptionDao.updateById(isExsit);
					}
				} catch (Exception e) {
					LOGGER.error("AliCrawler exception,url:" + myURL + ",jd:" + jobDesc, e);
				}
			}
			int totalPage = Integer.valueOf(returnValue.get("totalPage")
					.toString());
			if (index > totalPage || index > 350) {
				return;
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
