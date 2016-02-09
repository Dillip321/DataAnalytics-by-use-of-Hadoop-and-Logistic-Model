package com.poc.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Properties;

import kafka.message.Message;

public class ExampleUtils {
	private Properties getPropertiesFromClasspath() throws IOException {
		Properties props = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
				"/props.properties");
		props.load(in);
		in.close();
		return props;
	}

	 public static String getMessage(Message message)
	 {
	  System.out.println("message  : "+ message.toString());
	 
	  ByteBuffer buffer = message.payload();
	  byte [] bytes = new byte[buffer.remaining()];
	  buffer.get(bytes);
	  return new String(bytes);
	
	 }
	public static void main(String[] args) throws IOException {
		ExampleUtils exampleUtils =new ExampleUtils();
		System.out.println("dddd"+exampleUtils.getPropertiesFromClasspath());
		Properties props = new Properties();
		props =exampleUtils.getPropertiesFromClasspath();
		System.out.println("jjjjjjjj : "+props.getProperty("CUSTOMER_MASTER_NBA"));
		
		
	}
}
