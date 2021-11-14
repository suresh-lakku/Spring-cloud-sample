package com.learning.photoapp.api.user.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.photoapp.api.user.ui.model.AlbumResponseModel;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "albums-ws", fallbackFactory = AlbumsFallBackFactory.class)
public interface AlbumsFeignClient {

	@GetMapping("/users/{id}/albums")
	public List<AlbumResponseModel> getAlbums(@PathVariable String id);
}

@Component
class AlbumsFallBackFactory implements FallbackFactory<AlbumsFeignClient>
{

	@Override
	public AlbumsFeignClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new AlbumsFeignClientFallBack(cause);
	}
}

class AlbumsFeignClientFallBack implements AlbumsFeignClient
{
	Logger logger = LoggerFactory.getLogger(AlbumsFeignClientFallBack.class);
	private final Throwable cause;
	
	public AlbumsFeignClientFallBack(Throwable cause)
	{
		this.cause = cause;
	}

	@Override
	public List<AlbumResponseModel> getAlbums(String id) {
		
		if(cause instanceof FeignException && ((FeignException)cause).status() == 404)
		{
			logger.error("404 error occurred when getAlbums method called for User Id"+
					id + ", Error message "+ cause.getLocalizedMessage(), cause);
		}
		else
		{
			logger.error("Error caused by other " + cause.getLocalizedMessage(), cause);
		}
		
		return new ArrayList<AlbumResponseModel>();
	}
	
}


