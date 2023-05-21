package com.codea.Image;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;

    @NotNull
    private long boardIdx;

    @NotNull
    private String originalFileName; // 기존 파일명

    @NotNull
    private String storedFileName; // 저장한 파일명, 굳이 파일명을 나눈 이유는 기존 파일명이 이미 DB에 저장되어 있을 가능성은 높지만
    // 따로 이름을 바꿔서 저장한 파일명이 같은 경우는 낮기 때문에 굳이 나눠서 저장 하였다.

    private long fileSize;

//    MultipartFile



}
