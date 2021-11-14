package com.learning.photoapp.api.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.photoapp.api.user.dao.UsersRepository;
import com.learning.photoapp.api.user.data.AlbumsFeignClient;
import com.learning.photoapp.api.user.data.UserEntity;
import com.learning.photoapp.api.user.dto.UserDto;
import com.learning.photoapp.api.user.service.UsersService;
import com.learning.photoapp.api.user.ui.model.AlbumResponseModel;

import feign.FeignException;

@Service
public class UsersServiceImpl implements UsersService {

	Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	private UsersRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private RestTemplate restTemplate;
	
	private Environment environment;
	
	private AlbumsFeignClient albumsFeignClient;
	
	@Autowired
	public UsersServiceImpl(UsersRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			RestTemplate restTemplate,
			Environment environment,
			AlbumsFeignClient albumsFeignClient) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.restTemplate = restTemplate;
		this.environment = environment;
		this.albumsFeignClient = albumsFeignClient;
	}
	
	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity entity = mapper.map(userDetails, UserEntity.class);
		
		
		userRepository.save(entity);
		
		UserDto returnValue = mapper.map(entity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		UserEntity entity = userRepository.findByEmailId(emailId);
		if(Objects.isNull(entity))
		{
			throw new UsernameNotFoundException(emailId);
		}
		return new User(entity.getEmailId(), entity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmailId(String emailId) {
		UserEntity entity = userRepository.findByEmailId(emailId);
		if(Objects.isNull(entity))
		{
			throw new UsernameNotFoundException(emailId);
		}
		return new ModelMapper().map(entity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity entity = userRepository.findByUserId(userId);
		UserDto user = null;
		if(Objects.nonNull(entity ))
		{
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			

//			String albumsURL = String.format(environment.getProperty("albums.url"), userId);
//			
//			ResponseEntity<List<AlbumResponseModel>> albums = 
//					restTemplate.exchange(albumsURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>(){});
//			List<AlbumResponseModel> list = albums.getBody();
			
			List<AlbumResponseModel> list = null;
//			try
//			{
				
//			}
//			catch(FeignException error)
//			{
//				logger.error("Error occurred", error);
//			}
			logger.info("Before sending to Album microservice");
			list = albumsFeignClient.getAlbums(userId);
			logger.info("After sending to Album microservice");
			user = mapper.map(entity, UserDto.class);
			user.setAlbums(list);
			return user;
		}
		throw new UsernameNotFoundException("User Not found");
	}

}
