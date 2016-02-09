package com.poc.test;

public interface KafkaProperties {
	final static String zkConnect = "127.0.0.1:2181";
	  final static  String groupId = "group1";
	  final static String topic = "kkkkkkkk";
	  final static String kafkaServerURL = "10.102.103.196";
	  final static int kafkaServerPort = 9092;
	  final static int kafkaProducerBufferSize = 64*1024;
	  final static int connectionTimeOut = 100000;
	  final static int reconnectInterval = 10000;
	  final static String topic2 = "kkkkkkkk";
	  final static String topic3 = "kkkkkkkk";
}
