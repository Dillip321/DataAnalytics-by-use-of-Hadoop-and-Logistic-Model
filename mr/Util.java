package com.poc.mr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Util {
	public static boolean deleteFile(File dir) {
		// File dir = new File(filepath);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();

	}

	public static boolean deleteHDFSFile(String hdfsFileLocation) {
		boolean isDeleted = false;
		try {
			Configuration conf = new Configuration();
			System.out.println("HDFS file location is : " + hdfsFileLocation);
			FileSystem fs = FileSystem.get(URI.create(hdfsFileLocation), conf);
			isDeleted = fs.deleteOnExit(new Path(hdfsFileLocation));
			if (isDeleted) {
				System.out.println("File is deleted successfully .");
			} else {
				System.out.println("File not deleted.");
			}
			conf.clear();
			fs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isDeleted;
	}

	
	public Properties getPropertiesFromClasspath() throws IOException {
		Properties props = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"/props.properties");
		props.load(in);
		in.close();
		return props;
	}
	public static double round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return (double) tmp / p;
	}
}
