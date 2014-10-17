package com.refferal.lucene;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class JobIndexService {

	public static void indexBuilder(List<JobDescription> jobs) throws Exception {

		/* 这里放索引文件的位置 */
		File indexDir = new File("F:/lucene_index");
		Analyzer luceneAnalyzer = new StandardAnalyzer();
		IndexWriter indexWriter = new IndexWriter(indexDir, luceneAnalyzer,
				true);
		long startTime = new Date().getTime();

		// 增加document到索引去
		for (JobDescription job : jobs) {
			System.out.println(" job:  " + job.getJobName()
					+ " 正在被索引. ");
			Document document = new Document();
			Field name = new Field("jobName", job.getJobName(),
					Field.Store.YES, Field.Index.TOKENIZED);
			Field desc = new Field("description", job.getDescription(), Field.Store.YES,
					Field.Index.TOKENIZED,
					Field.TermVector.WITH_POSITIONS_OFFSETS);
			Field require = new Field("requirement", job.getJobRequirement(), Field.Store.YES,
					Field.Index.TOKENIZED,
					Field.TermVector.WITH_POSITIONS_OFFSETS);
			document.add(require);
			document.add(name);
			document.add(desc);
			indexWriter.addDocument(document);
		}
		// optimize()方法是对索引进行优化
		indexWriter.optimize();
		indexWriter.close();

		// 测试一下索引的时间
		long endTime = new Date().getTime();
		System.out.println(" 这花费了 " + (endTime - startTime)
				+ "  毫秒来把文档增加到索引里面去! ");
	}

}
