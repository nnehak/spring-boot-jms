package com.example.jms.listner;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Component
public class Listener {

	@JmsListener(destination = "inbound.queue")
	@SendTo("outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException, JsonProcessingException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if (jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) jsonMessage;
			messageData = textMessage.getText();
//			Map map = new Gson().fromJson(messageData, Map.class);
//			response = "Hello " + map.get("name");
//			ObjectMapper mapper = new ObjectMapper();
//			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(messageData);
			response = messageData;

		}
		return response;
	}

}