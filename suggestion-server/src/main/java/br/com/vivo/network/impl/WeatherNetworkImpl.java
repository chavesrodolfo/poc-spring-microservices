package br.com.vivo.network.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vivo.domain.openweather.OpenWeather;
import br.com.vivo.exception.CityNotFoundException;
import br.com.vivo.exception.NetworkException;
import br.com.vivo.network.WeatherNetwork;
import br.com.vivo.properties.OpenWeatherProperties;

@Component
public class WeatherNetworkImpl implements WeatherNetwork {
	
	private static final String INVALID_CITY_NAME = "Invalid city name";
	
	@Autowired
	private OpenWeatherProperties openWeatherProperties;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Cacheable("temperatureFromCity")
	public Float getTemperatureFromCity(String cityName) throws NetworkException, CityNotFoundException {
		Float temperature = null;
		try {
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromUriString(openWeatherProperties.getUrl());

			builder.queryParam("q", cityName);
			builder.queryParam("units", openWeatherProperties.getUnits());
			builder.queryParam("APPID", openWeatherProperties.getAppId());
			
			OpenWeather wheater = restTemplate.getForObject(builder.build().toUriString(), OpenWeather.class);

			temperature = wheater.getMain().getTemp();
		} catch (HttpClientErrorException ex) {
			throw new CityNotFoundException(INVALID_CITY_NAME);
		} catch (Exception e) {
			throw new NetworkException(e);
		}
		return temperature;
	}

	@Override
	@Cacheable("temperatureFromLocation")
	public Float getTemperatureFromLocation(Double latitude, Double longitude) throws NetworkException {
		Float temperature = null;
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(openWeatherProperties.getUrl());

			builder.queryParam("lat", latitude);
			builder.queryParam("lon", longitude);
			builder.queryParam("units", openWeatherProperties.getUnits());
			builder.queryParam("APPID", openWeatherProperties.getAppId());

			ResponseEntity<OpenWeather> wheater = restTemplate.getForEntity(builder.build().toUriString(), OpenWeather.class);

			temperature = wheater.getBody().getMain().getTemp();
		} catch (Exception e) {
			throw new NetworkException(e);
		}
		return temperature;
	}
	
}
