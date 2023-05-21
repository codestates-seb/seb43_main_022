package com.codea.address;

import com.codea.common.response.MultiResponseDto;
import com.codea.common.utils.UriCreator;
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
@RequestMapping("/address")
@Validated
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper mapper;

    public AddressController(AddressService addressService, AddressMapper mapper) {
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAddress(@Valid @RequestBody AddressDto.Post requestBody) {
        Address address = addressService.createAddress(mapper.addressPostDtoToAddress(requestBody));

        URI location = UriCreator.createUri("/address", address.getAddressId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{address-id}")
    public ResponseEntity patchAddress(@PathVariable("address-id") @Positive long addressId,
                                       @Valid @RequestBody AddressDto.Patch requestBody) {
        Address address = addressService.updateAddress(addressId, mapper.addressPatchDtoToAddress(requestBody));

        return new ResponseEntity<>(mapper.addressToAddressResponseDto(address), HttpStatus.OK);
    }

    @GetMapping("/{address-id}")
    public ResponseEntity getAddress(@PathVariable("address-id") @Positive long addressId) {
        Address address = addressService.findAddress(addressId);

        return new ResponseEntity<>(mapper.addressToAddressResponseDto(address),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAddresses(@Positive @RequestParam(value = "page", required = false) Integer page,
                                      @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Address> addressPage = addressService.findAddresss(page - 1, size);
        List<Address> address = addressPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.addressToAddressResponseDto(address), addressPage), HttpStatus.OK);
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity deleteAddress(@PathVariable("address-id") @Positive long addressId) {
        addressService.deleteAddress(addressId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
