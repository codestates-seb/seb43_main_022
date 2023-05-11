package com.codea.location;

import com.codea.response.MultiResponseDto;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/location")
@Validated
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper mapper;

    public LocationController(LocationService locationService, LocationMapper mapper) {
        this.locationService = locationService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postLocation(@Valid @RequestBody LocationDto.Post requestBody) {
        Location location = locationService.createLocation(mapper.locationPostDtoToLocation(requestBody));

        URI urlLocation = UriCreator.createUri("/location", location.getLocationId());

        return ResponseEntity.created(urlLocation).build();
    }

    @PatchMapping("/{location-id}")
    public ResponseEntity patchLocation(@PathVariable("location-id") @Positive long locationId,
                                       @Valid @RequestBody LocationDto.Patch requestBody) {
        Location location = locationService.updateLocation(locationId, mapper.locationPatchDtoToLocation(requestBody));

        return new ResponseEntity<>(mapper.locationToLocationResponseDto(location), HttpStatus.OK);
    }

    @GetMapping("/{location-id}")
    public ResponseEntity getLocation(@PathVariable("location-id") @Positive long locationId) {
        Location location = locationService.findLocation(locationId);

        return new ResponseEntity<>(mapper.locationToLocationResponseDto(location),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getLocations(@Positive @RequestParam(value = "page", required = false) Integer page,
                                      @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Location> locationPage = locationService.findLocations(page - 1, size);
        List<Location> location = locationPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.locationToLocationResponseDto(location), locationPage), HttpStatus.OK);
    }

    @DeleteMapping("/{location-id}")
    public ResponseEntity deleteLocation(@PathVariable("location-id") @Positive long locationId) {
        locationService.deleteLocation(locationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
