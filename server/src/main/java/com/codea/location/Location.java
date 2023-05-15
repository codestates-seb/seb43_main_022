//package com.codea.location;
//
//import com.codea.address.Address;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//public class Location {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long locationId;
//    @Column(unique = true)
//    private double latitude;
//    @Column(unique = true)
//    private double longitude;
//    @OneToOne
//    @JoinColumn(name = "ADDRESS_ID")
//    private Address address;
//}
