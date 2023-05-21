package com.codea.Menu;

import com.codea.restaurant.Restaurant;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long menuId;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Menu(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}
