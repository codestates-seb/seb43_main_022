package com.codea.favorite;

import com.codea.BaseEntity.BaseEntity;
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
@Builder
@Entity
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(nullable = false)
    private boolean status; // true = 즐겨찾기, false = 즐겨찾기 취소


    public Favorite(Restaurant restaurant, Member member) {
        this.restaurant = restaurant;
        this.member = member;
    }
}