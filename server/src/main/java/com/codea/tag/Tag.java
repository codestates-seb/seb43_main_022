package com.codea.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;
    @Column(nullable = false,updatable = false,length = 30)
    private String name;

    @OneToMany(mappedBy = "tag",cascade = CascadeType.PERSIST)
    private List<TagRestaurant> tagRestaurants = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
