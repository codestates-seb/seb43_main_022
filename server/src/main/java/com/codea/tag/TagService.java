package com.codea.tag;

import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Tag createTag(Tag tag){
       verifyExistsName(tag.getName());

       return tagRepository.save(tag);
    }
    public Tag updateTag(Tag tag){
       Tag findTag = findVerifiedTag(tag.getTagId());
       Optional.ofNullable(tag.getName())
               .ifPresent(name-> findTag.setName(name));
       return tagRepository.save(findTag);
    }
    public Tag findTag(long tagId){
        return findVerifiedTag(tagId);
    }
    public Page<Tag> findTags(int page, int size){
       return  tagRepository.findAll(PageRequest.of(page, size,
               Sort.by("tagId").descending()));

    }
    public void deleteTag(long tagId){
        Tag findTag = findVerifiedTag(tagId);

        tagRepository.delete(findTag);
    }
    public Tag findVerifiedTag(long tagId){
        Optional<Tag> optionalTag =
                tagRepository.findById(tagId);
        Tag findTag =
                optionalTag.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
        return findTag;
    }
    private void verifyExistsName(String name){
        Optional<Tag> tag = tagRepository.findByName(name);
        if(tag.isPresent())
            throw new BusinessLogicException(ExceptionCode.TAG_EXISTS);
    }



}
