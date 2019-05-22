package br.com.vivo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.domain.Message;
import br.com.vivo.service.JaimailRouterService;

@RestController
public class JaimailRouterController {
	
	private JaimailRouterService jaimailRouterService;
	
	@Autowired
	public JaimailRouterController(JaimailRouterService jaimailRouterService) {
		this.jaimailRouterService = jaimailRouterService;
	}

	@GetMapping(path = { "", "/send-message/{appName}" })
	public Map<String, String> sendMessage(@PathVariable String appName) {
	
		Message message = new Message();
		message.setApp(appName);
		message.setText("Sending message from " + appName);
		
		this.jaimailRouterService.route(message);

		Map<String, String> info = new HashMap<>();
		info.put("Message.App", message.getApp());
		info.put("Message.Text", message.getText());
		
		return info;
	}
	
}
