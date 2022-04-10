package com.sik.augur.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sik.augur.entity.IpEntity;

/**
 * 
 * @author Sikandar Mahmood
 *
 */
@Service
public class ApplicationService implements IApplicationService {

	/**
	 * get ip details
	 */
	@Override
	public String getIp() throws Exception {
		
		String ipAddress = "";
		String ipDetails = "";
		
		try {
			
			ipAddress = getIpAddress(ipAddress);
			
			ipDetails = getIpDetails(ipAddress);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipDetails;
	}

	
	/**
	 * this method gets the geo details by ip
	 * @param ipAddress
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	public String getIpDetails(String ipAddress) throws MalformedURLException, IOException, ProtocolException {
		URL urlIpInfo = new URL("https://ipinfo.io/"+ipAddress+"?token=af1eb4dede9fbe");
		HttpURLConnection urlIpInfoConn = (HttpURLConnection) urlIpInfo.openConnection();
		urlIpInfoConn.setRequestMethod("GET");
		urlIpInfoConn.connect();
		if (urlIpInfoConn.getResponseCode() == 200) {
			Scanner scan = new Scanner(urlIpInfo.openStream());
			ipAddress="";
			while (scan.hasNext()) {
				ipAddress += scan.nextLine();
			}
		}
		return ipAddress;
	}

	/**
	 * this method detects the ip address
	 * @param ipAddress
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	public String getIpAddress(String ipAddress) throws MalformedURLException, IOException, ProtocolException {
		URL url = new URL("http://api.ipify.org/?format=json");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		if (conn.getResponseCode() == 200) {
			Scanner scan = new Scanner(url.openStream());
			while (scan.hasNext()) {
				ipAddress = scan.nextLine();
			}
		}
		
		if( ipAddress != null ) {
			Gson gson = new Gson();
		    IpEntity thing = gson.fromJson(ipAddress, IpEntity.class);
		    ipAddress = thing.getIp();
		}
		return ipAddress;
	}

}
