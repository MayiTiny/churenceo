package com.refferal.lucene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Seacher {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\lenovo\\Desktop\\zw-140602-223524.txt"));
		String line = br.readLine();// 一次读入一行，直到读入null为文件结束
		List<JobDescription> jobs = new ArrayList<JobDescription>();
		JobDescription jobModel = new JobDescription();
		Class c = Class.forName("JobDescription");
		while (line != null) {
			if (line.contains("----------------------------------------")) {
				jobs.add(jobModel);
				jobModel = new JobDescription();
			} else if (!line.isEmpty()) {
				handleLine(line, jobModel, c);
			}
			line = br.readLine(); // 接着读下一行
		}
		JobIndexService.indexBuilder(jobs);
		JobSearchService.SercherByKeyWord("开发");
	}

	@SuppressWarnings("unchecked")
	public static void handleLine(String line, JobDescription job, Class c)
			throws Exception, NoSuchMethodException {
		if (null == line) {
			return;
		}
		String methodName = job.getType();
		String content = line;
		boolean hasTitle = false;
		for (JobColumnEnum column : JobColumnEnum.values()) {
			if (line.startsWith(column.getChName())) {
				methodName = column.getColumnName();
				job.setType(methodName);
				hasTitle = true;
				break;
			}
		}
		if(!hasTitle){
			Method m = c.getDeclaredMethod("get" + methodName);
			Object obj = m.invoke(job);
			if (null != obj) {
				content = obj.toString() + "\n" + content;
			}
		}
		
		if (null != methodName) {
			Method setter = c.getDeclaredMethod("set" + methodName,
					String.class);
			setter.invoke(job, content);
		} else {
			System.out.println(content);
		}

	}
}
