package com.codea.Image;

import com.codea.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(length = 50)
    private String imageName;

    @Column
    private String Image;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public Image(String imageName, String image, Review review) {
        this.imageName = imageName;
        Image = image;
        this.review = review;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", s3Url='" + Image + '\'' +
                '}';
    }
}