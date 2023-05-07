package com.codea.restaurant;

import com.codea.BaseEntity.BaseEntity;
import com.codea.Menu.Menu;
import com.codea.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant extends BaseEntity {

    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String name;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String content;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String location;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String tel;
    @Column(nullable = false,unique = true)
    @NotBlank
    private byte[] photo;
    @Column(nullable = false)
    @NotBlank
    private String opentime;
    private int total_views;
    private int total_favorite;
    private double rating;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menu = new ArrayList<>();

}
