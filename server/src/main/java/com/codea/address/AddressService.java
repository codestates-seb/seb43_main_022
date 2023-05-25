package com.codea.address;

import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(long addressId, Address address) {
        Address findAddress = findAddress(addressId);

        Optional.ofNullable(address.getStreetAddress()).ifPresent(streetAddress -> findAddress.setStreetAddress(streetAddress));
        Optional.ofNullable(address.getLatitude()).ifPresent(latitude -> findAddress.setLatitude(latitude));
        Optional.ofNullable(address.getLongitude()).ifPresent(longitude -> findAddress.setLongitude(longitude));

        return addressRepository.save(findAddress);
    }

    public Address findAddress(long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ADDRESS_NOT_FOUND));
    }

    public Page<Address> findAddresss(int page, int size) {
        return addressRepository.findAll(PageRequest.of(page, size, Sort.by("addressId").descending()));
    }

    public void deleteAddress(long addressId) {
        Address findAddress = findAddress(addressId);

        addressRepository.delete(findAddress);
    }
}
