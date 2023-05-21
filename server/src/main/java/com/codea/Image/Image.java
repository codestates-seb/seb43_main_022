//package com.codea.Image;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Getter
//@Entity
//@NoArgsConstructor
//public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String title;
//
//    @Column
//    private String s3Url;
//
//    public Image(String title, String s3Url) {
//        this.title = title;
//        this.s3Url = s3Url;
//    }
//
//    @Override
//    public String toString() {
//        return "FileEntity{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", s3Url='" + s3Url + '\'' +
//                '}';
//    }
//}
//}
