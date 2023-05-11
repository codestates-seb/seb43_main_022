package com.codea.location;

import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final RestaurantRepository restaurantRepository;

    public LocationService(LocationRepository locationRepository, RestaurantRepository restaurantRepository) {
        this.locationRepository = locationRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(long locationId, Location location) {
        Location findLocation = findLocation(locationId);

        Optional.ofNullable(location.getLatitude()).ifPresent(latitude -> findLocation.setLatitude(latitude));
        Optional.ofNullable(location.getLongitude()).ifPresent(longitude -> findLocation.setLongitude(longitude));

        return locationRepository.save(findLocation);
    }

    public Location findLocation(long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MENU_NOT_FOUND));
    }

    public Page<Location> findLocations(int page, int size) {
        return locationRepository.findAll(PageRequest.of(page, size, Sort.by("locationId").descending()));
    }

    public void deleteLocation(long locationId) {
        Location findLocation = findLocation(locationId);

        locationRepository.delete(findLocation);
    }
}
