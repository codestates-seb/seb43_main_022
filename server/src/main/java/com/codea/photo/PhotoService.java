package com.codea.photo;

import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.tag.Tag;
import com.codea.tag.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    public PhotoService(PhotoRepository photoRepository){
        this.photoRepository = photoRepository;
    }

    public Photo createPhoto(Photo photo){
        verifyExistsName(photo.getName());

        return photoRepository.save(photo);
    }
    public Photo updatePhoto(Photo photo){
        Photo findPhoto = findVerifiedPhoto(photo.getPhotoId());
        Optional.ofNullable(photo.getName())
                .ifPresent(name-> findPhoto.setName(name));
        return photoRepository.save(findPhoto);
    }
    public Photo findPhoto(long photoId){
        return findVerifiedPhoto(photoId);
    }
    public Page<Photo> findPhotos(int page, int size){
        return  photoRepository.findAll(PageRequest.of(page, size,
                Sort.by("photoId").descending()));

    }
    public void deletePhoto(long photoId){
        Photo findPhoto = findVerifiedPhoto(photoId);

        photoRepository.delete(findPhoto);
    }
    public Photo findVerifiedPhoto(long photoId){
        Optional<Photo> optionalPhoto =
                photoRepository.findById(photoId);
        Photo findPhoto =
                optionalPhoto.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.PHOTO_NOT_FOUND));
        return findPhoto;
    }
    private void verifyExistsName(String name){
        Optional<Photo> photo = photoRepository.findByName(name);
        if(photo.isPresent())
            throw new BusinessLogicException(ExceptionCode.PHOTO_EXISTS);
    }

}
