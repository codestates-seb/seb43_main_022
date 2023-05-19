package com.codea.restaurant;

import com.codea.Menu.Menu;
import com.codea.Menu.MenuDto;
import com.codea.Menu.MenuRepository;
import com.codea.address.Address;
import com.codea.address.AddressRepository;
import com.codea.category.Category;
import com.codea.category.CategoryDto;
import com.codea.category.CategoryRepository;
import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberDto;
import com.codea.member.MemberRepository;
import com.codea.review.Review;
import com.codea.review.ReviewRepository;
import com.codea.tag.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codea.review.Review.ReviewStatus.REVIEW_VALID;
import static java.awt.SystemColor.menu;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final MenuRepository menuRepository;
    private final TagRepository tagRepository;
    private final TagRestaurantRepository tagRestaurantRepository;
    private final CategoryRepository categoryRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, MemberRepository memberRepository, AddressRepository addressRepository, MenuRepository menuRepository, TagRepository tagRepository, TagRestaurantRepository tagRestaurantRepository, CategoryRepository categoryRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.menuRepository = menuRepository;
        this.tagRepository = tagRepository;
        this.tagRestaurantRepository = tagRestaurantRepository;
        this.categoryRepository = categoryRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public Restaurant createRestaurant(String email, Address address, Category category, RestaurantDto.Post post) {
        Restaurant restaurant = new Restaurant(post.getRestaurantName(), post.getContent(), post.getTel(), post.getOpen_time(),
                post.getPhotoUrl(), post.getDetailAddress());

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        restaurant.setMember(member);

        String streetAddress = address.getStreetAddress();
        Address findAddress = addressRepository.findByStreetAddress(streetAddress).orElseGet(() -> addressRepository.save(address));
        restaurant.setAddress(findAddress);

        String categoryName = category.getName();
        Category findCategory = categoryRepository.findByName(categoryName).orElseGet(() -> categoryRepository.save(category));
        restaurant.setCategory(findCategory);

        for (MenuDto.Post menuPost : post.getMenu()) {
            Menu menu = new Menu(menuPost.getName(), menuPost.getPrice(), restaurant);
            menuRepository.save(menu);
        }

        for (TagDto.Post tagPost : post.getTag()) {
            Tag findTag = tagRepository.findByName(tagPost.getName()).orElseGet(() -> {
                Tag tag = new Tag(tagPost.getName());
                return tagRepository.save(tag);
            });
            TagRestaurant tagRestaurant = new TagRestaurant();
            tagRestaurant.setTag(findTag);
            tagRestaurant.setRestaurant(restaurant);
            tagRestaurantRepository.save(tagRestaurant);
        }

        return restaurantRepository.save(restaurant);
    }


    @Transactional
    public Restaurant updateRestaurant(long restaurantId, String email, Address address,  RestaurantDto.Patch patch) {
        // 주소를 수정하면 데이터베이스를 직접 수정하는 게 아닌, 수정된 주소를 데이터베이스에 추가함
        // 만약, 수정된 주소가 데이터베이스에 존재하면, 수정된 주소를 set 한다
        // 수정된 주소가 데이터베이스에 존재하지 않으면, 수정된 주소를 데이터베이스에 추가한다.
        // 주소를 삭제하면 데이터베이스의 값을 삭제하지 않고 그대로 놔둠 -> 나중에 같은 도로명 주소를 저장하는 경우가 생길 수 있기 때문

        patch.setRestaurantId(restaurantId);
        Restaurant restaurant = restaurantMapper.restaurantPatchDtoToRestaurant(patch);

        Restaurant findRestaurant = findRestaurant(restaurantId);

        if (!findRestaurant.getMember().getEmail().equals(email)) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_EDIT);

        Optional.ofNullable(restaurant.getRestaurantName()).ifPresent(restaurantName -> findRestaurant.setRestaurantName(restaurantName));
        Optional.ofNullable(restaurant.getContent()).ifPresent(content -> findRestaurant.setContent(content));
        Optional.ofNullable(restaurant.getTel()).ifPresent(tel -> findRestaurant.setTel(tel));
        Optional.ofNullable(restaurant.getPhotoUrl()).ifPresent(photoUrl -> findRestaurant.setPhotoUrl(photoUrl));
        Optional.ofNullable(restaurant.getOpen_time()).ifPresent(open_time -> findRestaurant.setOpen_time(open_time));
        Optional.ofNullable(restaurant.getDetailAddress()).ifPresent(detailAddress -> findRestaurant.setDetailAddress(detailAddress));
        findRestaurant.setModifiedAt(LocalDateTime.now());

        if (address != null) {  // 수정 안됨, 200 ok
            String streetAddress = address.getStreetAddress();
            Address findAddress = addressRepository.findByStreetAddress(streetAddress)
                    .orElseGet(() -> addressRepository.save(address));
            findRestaurant.setAddress(findAddress);
        }


//        if (address != null) {
//            String streetAddress = address.getStreetAddress();
//            Address persistedAddress = addressRepository.findByStreetAddress(streetAddress)
//                    .orElseGet(() -> addressRepository.save(address));
//            findMember.setAddress(persistedAddress);
//        }
////
//         Optional.ofNullable(restaurant.getCategory()).ifPresent(category -> findRestaurant.setCategory(category));

        Optional.ofNullable(restaurant.getCategory()).ifPresent(category -> {
            Category categoryTemp = new Category();
            categoryTemp.setName(patch.getCategory().getName());

            Category findCategory = categoryRepository.findByName(categoryTemp.getName()).orElseGet(() -> categoryRepository.save(category));
            findRestaurant.setCategory(findCategory);
        });

//        String categoryName = patch.getCategory().getName();
//        Category findCategory = categoryRepository.findByName(categoryName).orElseGet(() -> categoryRepository.save(patch));
//        restaurant.setCategory(findCategory);


        Optional.ofNullable(restaurant.getMenu()).ifPresent((menuList) -> {
            menuRepository.deleteAllByRestaurant_RestaurantId(restaurantId);

            for (Menu menuTemp: restaurant.getMenu()) {

                Menu findMenu = menuRepository.findById(menuTemp.getMenuId()).orElseGet(() -> {
                    menuTemp.setRestaurant(findRestaurant);
                    return menuRepository.save(menuTemp);
                });

                findMenu.setName(menuTemp.getName());
                findMenu.setPrice(menuTemp.getPrice());
                menuRepository.save(findMenu);

            }
            findRestaurant.setMenu(menuList);
        });
//
//        for (TagDto.Post tagPost : post.getTag()) {
//            Tag findTag = tagRepository.findByName(tagPost.getName()).orElseGet(() -> {
//                Tag tag = new Tag(tagPost.getName());
//                return tagRepository.save(tag);
//            });
//            TagRestaurant tagRestaurant = new TagRestaurant();
//            tagRestaurant.setTag(findTag);
//            tagRestaurant.setRestaurant(restaurant);
//            tagRestaurantRepository.save(tagRestaurant);
//        }
        //태그 레스토랑의 tagId 를 받아와서 태그를 조회한뒤,
        // 변수에 저장. 태그가 존재하지 않으면 데이터베이스에도 저장.
        // 찾아온 태그로 이름을 찾고
        Optional.ofNullable(patch.getTag()).ifPresent((TagList) -> {
            tagRestaurantRepository.deleteAllByRestaurant(restaurant);
            for (TagDto.Patch tagTemp : patch.getTag()) {
                Tag findTag = tagRepository.findByName(tagTemp.getName()).orElseGet(() -> {
                    Tag newtag = new Tag(tagTemp.getName());
                    return tagRepository.save(newtag);
                });

                TagRestaurant findTagRestaurant = tagRestaurantRepository.findByTag_TagId(findTag.getTagId()).orElseGet(() -> {
                    TagRestaurant newTagRestaurant = new TagRestaurant(restaurant, findTag);
                    return tagRestaurantRepository.save(newTagRestaurant);
                });
            }
        });

        return restaurantRepository.save(findRestaurant);
    }

    @EntityGraph(attributePaths = {"menu", "reviews", "tagRestaurant"})
    public Restaurant findRestaurant(long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
    }

    public Page<Restaurant> findRestaurants(int page, int size) {
        return restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("restaurantId").descending()));
    }

    public void deleteRestaurant(long restaurantId) {
        Restaurant findRestaurant = findRestaurant(restaurantId);

        restaurantRepository.delete(findRestaurant);
    }

//    public Page<Restaurant> getTop10Restaurants(int page, int size) {
//        return restaurantRepository.findAllByOrderByTotalFavoriteDesc(PageRequest.of(page, size, Sort.by("restaurantId").descending()));
//
//    }

    public Page<Restaurant> searchRestaurants(int page, int size, String keyword) {

        return restaurantRepository.searchByKeyword(keyword, PageRequest.of(page, size, Sort.by("restaurantId").descending()));
    }

//    public Page<Restaurant> searchByTagRestaurants(int page, int size, String url, String tag) {
//
//        return restaurantRepository.searchByTag(url, tag, PageRequest.of(page, size, Sort.by("restaurantId").descending()));
//    }


}
