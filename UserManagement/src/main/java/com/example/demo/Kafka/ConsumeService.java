package com.example.demo.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService 
{
	@KafkaListener(topics="MovieBookingApp", groupId="mygroup")
	public void consumeFromTopic(String message)
	{
		System.out.println("Consumer message: "+ message);
	}

}