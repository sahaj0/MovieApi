package com.MovieBookingApp.MovieBookingApp.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
//public class DataPublisherServiceImpl
//{
//	public static final String topic ="MovieBookingApp";
//
//	@Autowired
//	private KafkaTemplate<String, String> temp;
//
//	public void setTemp(String message)
//	{
//		this.temp.send(topic, message);
//	}
//
//}