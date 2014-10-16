package com.refferal.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;


public class JobSearchService {

	public static void SercherByKeyWord(String keyWord) throws CorruptIndexException, IOException{
		 	Hits hits  =   null ;   
	        Query query  =   null ;   
	        IndexSearcher searcher  =   new  IndexSearcher( "F:\\lucene_index" );   
	  
	        Analyzer analyzer  =   new  StandardAnalyzer();   
	         try   {   
	            QueryParser qp  =   new  QueryParser( "jobName" , analyzer);   
	            query  =  qp.parse(keyWord);   
	        }   catch  (ParseException e)  {   
	        }    
	         if  (searcher  !=   null )  {   
	            hits  =  searcher.search(query);   
	             if  (hits.length()  >   0 )  {   
	                System.out.println( " 找到: "   +  hits.length()  +   "  个结果! " );  
	                for(int i=0;i<hits.length();i++){
	                	Document d = hits.doc(i);
	                	System.out.println(d);
	                }
	            }    
	        }    
	    }  
}
