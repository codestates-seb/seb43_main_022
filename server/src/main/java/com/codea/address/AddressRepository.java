package com.codea.address;

import com.codea.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetAddress(String streetAddress);
}
