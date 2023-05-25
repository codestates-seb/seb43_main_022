//package com.codea.tag;
//
//import com.codea.utils.UriCreator;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.Positive;
//import java.net.URI;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/tags")
//@Validated
//public class TagController {
//    private final static  String TAG_DEFAULT_URL = "/tags";
//    private final TagService tagService;
//    private final TagMapper tagMapper;
//    public TagController(TagService tagService, TagMapper tagMapper){
//        this.tagService = tagService;
//        this.tagMapper = tagMapper;
//    }
//    @PostMapping
//    public ResponseEntity postTag(@Valid@RequestBody TagDto.Post tagPostDto){
//        Tag tag = tagMapper.tagPostDtoToTag(tagPostDto);
//        Tag response = tagService.createTag(tag);
//        URI location = UriCreator.createUri(TAG_DEFAULT_URL, tag.getTagId());
//        //return new ResponseEntity<>(tagMapper.tagToTagResponseDto(response), HttpStatus.CREATED);
//        return ResponseEntity.created(location).build();
//    }
//    @PatchMapping("/{tag-id}")
//    public ResponseEntity patchTag(@PathVariable("tag-id")@Positive long tagId,
//                                   @Valid @RequestBody TagDto.Patch tagPatchDto){
//        tagPatchDto.setTagId(tagId);
//        Tag response =
//                tagService.updateTag( tagMapper.tagPatchDtoToTag(tagPatchDto));
//
//        return new ResponseEntity<>(tagMapper.tagToTagResponseDto(response), HttpStatus.OK);
//    }
//    @GetMapping("/{tag-id}")
//    public ResponseEntity getTag(@PathVariable("tag-id")@Positive long tagId){
//
//        Tag response = tagService.findTag(tagId);
//        return new ResponseEntity<>(tagMapper.tagToTagResponseDto(response) ,HttpStatus.OK);
//    }
//    @GetMapping
//    public ResponseEntity getTags(@Positive @RequestParam int page,
//                                    @Positive @RequestParam int size){
//
//        Page<Tag> tags = tagService.findTags(page-1,size);
//        List<TagDto.Response> response =
//                tags.stream()
//                        .map(tag -> tagMapper.tagToTagResponseDto(tag))
//                        .collect(Collectors.toList());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    @DeleteMapping("/{tag-id}")
//    public ResponseEntity deleteTag(@PathVariable("tag-id") @Positive long tagId){
//
//        tagService.deleteTag(tagId);
//        return  new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//}
