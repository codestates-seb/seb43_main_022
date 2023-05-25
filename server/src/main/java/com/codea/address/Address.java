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
    @Column(nullable = false)
    private String streetAddress;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;

    public Address(String streetAddress, double latitude, double longitude) {
        this.streetAddress = streetAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
