package com.codea.location;

import com.codea.address.Address;
import com.codea.address.AddressRepository;
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
    private final AddressRepository addressRepository;

    public LocationService(LocationRepository locationRepository, AddressRepository addressRepository) {
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
    }

    public Location createLocation(LocationDto.Post post) {
        Address address = addressRepository.findById(post.getAddressId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ADDRESS_NOT_FOUND));

        Location location = new Location();
        location.setLatitude(post.getLatitude());
        location.setLongitude(post.getLongitude());
        location.setAddress(address);

        return locationRepository.save(location);
    }

    public Location updateLocation(long locationId, Location location) {
        Location findLocation = findLocation(locationId);

        Optional.ofNullable(location.getLatitude()).ifPresent(latitude -> findLocation.setLatitude(latitude));
        Optional.ofNullable(location.getLongitude()).ifPresent(longitude -> findLocation.setLongitude(longitude));

        return locationRepository.save(findLocation);
    }

    public Location findLocation(long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.LOCATION_NOT_FOUND));
    }

    public Page<Location> findLocations(int page, int size) {
        return locationRepository.findAll(PageRequest.of(page, size, Sort.by("locationId").descending()));
    }

    public void deleteLocation(long locationId) {
        Location findLocation = findLocation(locationId);

        locationRepository.delete(findLocation);
    }
}
