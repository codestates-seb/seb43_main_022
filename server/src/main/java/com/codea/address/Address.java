package com.codea.address;

import com.codea.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    @Column(length = 50, nullable = false, unique = true)
    private String streetAddress;
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Location location;
}
