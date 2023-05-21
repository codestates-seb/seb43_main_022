package com.codea.favorite;

import com.codea.common.BaseEntity.BaseEntity;
import com.codea.member.Member;
import com.codea.restaurant.Restaurant;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    public Favorite(Restaurant restaurant, Member member) {
        this.restaurant = restaurant;
        this.member = member;
    }
}