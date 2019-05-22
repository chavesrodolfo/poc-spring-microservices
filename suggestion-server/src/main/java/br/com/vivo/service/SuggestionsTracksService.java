package br.com.vivo.service;

import br.com.vivo.domain.TrackList;
import br.com.vivo.exception.BusinessException;
import br.com.vivo.exception.CityNotFoundException;

public interface SuggestionsTracksService {

	/**
	 * Suggest a list of tracks based on a city name
	 *
	 * @param cityName the city name
	 * @return a list of {@link TrackList}s with the suggests tracks
	 * @throws BusinessException     whenever a business error occurs
	 * @throws CityNotFoundException whenever the city could not be found
	 */
	TrackList suggestTracksByCityName(String cityName) throws BusinessException, CityNotFoundException;

	/**
	 * Suggest a list of tracks based on a lat/long coordinate
	 *
	 * @param latitude  the latitude of the city
	 * @param longitude the longitude of the city
	 * @return a list of {@link TrackList}s with the suggests tracks
	 * @throws BusinessException whenever a business error occurs
	 */
	TrackList suggestTracksByLocation(Double latitude, Double longitude) throws BusinessException;

}
