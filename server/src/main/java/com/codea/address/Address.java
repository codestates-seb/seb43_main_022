package com.codea.address;

import com.codea.Menu.Menu;
import com.codea.location.Location;
import com.codea.restaurant.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    @Column(length = 50, nullable = false)
    private String streetAddress;
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Location location;
    @OneToMany(mappedBy = "restaurant")
    private List<Restaurant> restaurants = new ArrayList<>();

}
