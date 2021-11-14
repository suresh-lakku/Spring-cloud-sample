package com.learning.photoapp.api.user.ui.controllers;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.ipc.http.HttpSender.Method;


public class Demo {

//	static {
//        //for localhost testing only
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//        new javax.net.ssl.HostnameVerifier(){
//
//            public boolean verify(String hostname,
//                    javax.net.ssl.SSLSession sslSession) {
//                if (hostname.equals("maxae.local")) {
//                    return true;
//                }
//                return false;
//            }
//        });
//    }
	
	public static void main(String[] args)
	{
		String url = "https://uat2.maxfashion.com/bh/en/quickCheckout/acknowledgement?merchantidentifier=H4sIAAAAAAAAAKtWyi9KSS1yzk9JVbJSMrG0NLI0NTIwNFDSUSqpLACJFZcmJ6cWFwMFAhwjfV39QuIDgvzDPF1cg4CSTq5-rm6eIUq1AOsRwyNIAAAA";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders(){{
	         String auth = "lms.com" + ":" + "Webteam!";
	         byte[] encodedAuth = Base64.encodeBase64( 
	            auth.getBytes(Charset.forName("US-ASCII")) );
	         String authHeader = "Basic " + new String( encodedAuth );
	         set( "Authorization", authHeader );
	      }};
	      
	    HttpEntity<String> entity = new HttpEntity<String>("", headers);
		
		//restTemplate.getForEntity(url, String.class, new ArrayList<>());
		restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new ArrayList<>());
	}
}
