package com.codea.photo;

import com.codea.tag.Tag;
import com.codea.tag.TagDto;
import com.codea.tag.TagMapper;
import com.codea.tag.TagService;
import com.codea.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/photos")
@Validated
public class PhotoController {
    private final static  String PHOTO_DEFAULT_URL = "/photos";
    private final PhotoService photoService;
    private final PhotoMapper photoMapper;
    public PhotoController(PhotoService photoService, PhotoMapper photoMapper){
        this.photoService = photoService;
        this.photoMapper = photoMapper;
    }
    @PostMapping
    public ResponseEntity postPhoto(@Valid @RequestBody PhotoDto.Post photoPostDto){
        Photo photo = photoMapper.photoPostDtoToPhoto(photoPostDto);
        Photo response = photoService.createPhoto(photo);
        URI location = UriCreator.createUri(PHOTO_DEFAULT_URL, photo.getPhotoId());
        //return new ResponseEntity<>(tagMapper.tagToTagResponseDto(response), HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }
    @PatchMapping("/{photo-id}")
    public ResponseEntity patchPhoto(@PathVariable("photo-id")@Positive long photoId,
                                   @Valid @RequestBody PhotoDto.Patch photoPatchDto){
        photoPatchDto.setPhotoId(photoId);
        Photo response =
                photoService.updatePhoto( photoMapper.photoPatchDtoToPhoto(photoPatchDto));

        return new ResponseEntity<>(photoMapper.photoToPhotoResponseDto(response), HttpStatus.OK);
    }
    @GetMapping("/{photo-id}")
    public ResponseEntity getPhoto(@PathVariable("photo-id")@Positive long photoId){

        Photo response = photoService.findPhoto(photoId);
        return new ResponseEntity<>(photoMapper.photoToPhotoResponseDto(response) , HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getPhotos(@Positive @RequestParam int page,
                                  @Positive @RequestParam int size){

        Page<Photo> photos = photoService.findPhotos(page-1,size);
        List<PhotoDto.Response> response =
                photos.stream()
                        .map(photo -> photoMapper.photoToPhotoResponseDto(photo))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{photo-id}")
    public ResponseEntity deletePhoto(@PathVariable("photo-id") @Positive long photoId){

        photoService.deletePhoto(photoId);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
