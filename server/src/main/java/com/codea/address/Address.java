package com.codea.address;

import com.codea.favorite.Favorite;
import com.codea.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
//    @Column(length = 50, nullable = false, unique = true)
    private String streetAddress;
//    @Column(unique = true)
    private double latitude;
//    @Column(unique = true)
    private double longitude;

    public Address(String streetAddress, double latitude, double longitude) {
        this.streetAddress = streetAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
