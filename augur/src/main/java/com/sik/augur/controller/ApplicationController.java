package com.sik.augur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sik.augur.service.IApplicationService;

/**
 * 
 * @author Sikandar Mahmood
 *
 */
@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {
	
	@Autowired
	private IApplicationService service;

	/**
	 * get ip details
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getIp")
	public String getIp() throws Exception {
		String ipAddress = service.getIp(); 
		return ipAddress;
	}

}
