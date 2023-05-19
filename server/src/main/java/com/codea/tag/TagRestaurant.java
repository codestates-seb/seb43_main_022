package com.codea.tag;

import com.codea.restaurant.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class TagRestaurant  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagRestaurantId;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;


    public void setTag(Tag tag){
        this.tag = tag;
        if(!this.tag.getTagRestaurant().contains(this)){
            this.tag.getTagRestaurant().add(this);
        }
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        if(!this.restaurant.getTagRestaurants().contains(this)){
            this.restaurant.setTagRestaurant(this);
        }
    }

    public TagRestaurant(Restaurant restaurant, Tag tag) {
        this.restaurant = restaurant;
        this.tag = tag;
    }
}
