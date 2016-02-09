package com.poc.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.poc.mr.Util;

public class HDFSFileProcessor implements FileFilter{
	/* 
	 * This method is use to upload the file from local file system to HDFS 
	 */
	
	public  void loadFileOnHDFS(String localPath){
		Util util = new Util();
		Properties properties = new Properties();
		try {
			properties = util.getPropertiesFromClasspath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String localSrc = localPath;
		String dst =properties.getProperty("FILEUPLOADED");
		//String dst = "hdfs://10.102.103.196:8020/user/hduser/bhagyashree/Input/Sentiment/Twits/data.csv";
		
		
		try
		{
			InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
			Configuration conf = new Configuration();
			deleteFile(dst);
			FileSystem fs = FileSystem.get(URI.create(dst), conf);
			fs.copyFromLocalFile(false, true, new Path(localSrc), new Path(dst));
			System.out.println("path at hdfs : " +dst);
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean deleteFile(String hdfsFileLocation){
		boolean isDeleted = false;
		try
		{
			Configuration conf = new Configuration();
			System.out.println("HDFS file location is : " +hdfsFileLocation);
			FileSystem fs = FileSystem.get(URI.create(hdfsFileLocation), conf);
			isDeleted = fs.deleteOnExit(new Path(hdfsFileLocation));
			if(isDeleted){
				System.out.println("File is deleted successfully .");
			}else{
				System.out.println("File not deleted.");
			}
			conf.clear();
			fs.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isDeleted;
	}
	
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return !pathname.getName().endsWith(".crc");
	}
}

