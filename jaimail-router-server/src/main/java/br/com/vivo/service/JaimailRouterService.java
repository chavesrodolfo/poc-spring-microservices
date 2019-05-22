package br.com.vivo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.vivo.domain.Message;
import br.com.vivo.kafka.producer.RouterSender;

@Service
public class JaimailRouterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JaimailRouterService.class);
	
	@Autowired
	private RouterSender sender;

	//@HystrixCommand(fallbackMethod="routeDefault")
	public void route(Message message) throws IllegalArgumentException{
		if(message.getApp().equals("communicator")) {
			sender.send("communicator.t", message.getText());
		} else if(message.getApp().equals("rubeus")) {
				sender.send("rubeus.t", message.getText());
		} else {
			//next task: send to others topics
			LOGGER.error("App does not exists!");
			throw new IllegalArgumentException();
		}
	}
	
	@SuppressWarnings("unused")
	private void routeDefault(Message message) {
		LOGGER.info("Fallback Method routeDefault called." + message);
	}
}
