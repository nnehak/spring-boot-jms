package com.example.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat ;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJmsApplicationTests {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void test() {
		String s = "Hello World";
		this.jmsTemplate.convertAndSend("inbound.queue",s);
		assertThat(this.jmsTemplate.receiveAndConvert("outbound.queue")).isEqualTo(s);
	}

}
