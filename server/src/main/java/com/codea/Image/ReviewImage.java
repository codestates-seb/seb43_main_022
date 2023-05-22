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
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String imageName;

    @Column
    private String s3Url;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewImage(String imageName, String s3Url) {
        this.imageName = imageName;
        this.s3Url = s3Url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", s3Url='" + s3Url + '\'' +
                '}';
    }
}