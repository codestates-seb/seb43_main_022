package com.codea.photo;

import com.codea.tag.TagRestaurant;
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

public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long photoId;
    @Column(nullable = false,unique = true,length = 50)
    private String name;


}
