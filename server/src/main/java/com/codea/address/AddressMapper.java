package com.codea.address;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address addressPostDtoToAddress(AddressDto.Post requestBody);
    Address addressPatchDtoToAddress(AddressDto.Patch requestBody);
    AddressDto.Response addressToAddressResponseDto(Address address);
    List<AddressDto.Response> addressToAddressResponseDto(List<Address> address);
}
