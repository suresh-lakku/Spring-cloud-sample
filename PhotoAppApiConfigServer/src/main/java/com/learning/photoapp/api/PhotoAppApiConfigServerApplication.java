package com.learning.photoapp.api;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.encryption.SingleTextEncryptorLocator;
import org.springframework.cloud.context.encrypt.EncryptorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@SpringBootApplication
@EnableConfigServer
public class PhotoAppApiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiConfigServerApplication.class, args);
//		JmsT
	}

	
	@Autowired
	private KeyProperties key;
	
	
	private TextEncryptor encryptor;
	
	@Bean(name = "encryptor")
	public TextEncryptor encryptor()
	{
		if(Objects.nonNull(this.key.getKey()))
		{
			this.encryptor = new EncryptorFactory(this.key.getSalt()).create(this.key.getKey());
		}
		return encryptor;
	}
	
	
	@Bean(name = "locator")
	public SingleTextEncryptorLocator locator()
	{
	  return new SingleTextEncryptorLocator(this.encryptor);
	}
}
