package com.codea.restaurant;

import com.codea.Menu.MenuRepository;
import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import com.codea.review.Review;
import com.codea.review.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.codea.review.Review.ReviewStatus.REVIEW_VALID;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
    }

    public Restaurant createRestaurant(String email, Restaurant restaurant) {
        System.out.println(email+ "2@@@@@@@@@@@@@@@@@@@");
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        restaurant.setMember(member);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(long restaurantId, String email, Restaurant restaurant){
        Restaurant findRestaurant = findRestaurant(restaurantId);

        if (!findRestaurant.getMember().getEmail().equals(email)) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_EDIT);

        Optional.ofNullable(restaurant.getName()).ifPresent(name->findRestaurant.setName(name));
        Optional.ofNullable(restaurant.getContent()).ifPresent(content-> findRestaurant.setContent(content));
        Optional.ofNullable(restaurant.getLocation()).ifPresent(location-> findRestaurant.setLocation(location));
        Optional.ofNullable(restaurant.getTel()).ifPresent(tel-> findRestaurant.setTel(tel));
        Optional.ofNullable(restaurant.getPhoto()).ifPresent(photo-> findRestaurant.setPhoto(photo));
        Optional.ofNullable(restaurant.getOpen_time()).ifPresent(open_time-> findRestaurant.setOpen_time(open_time));
        findRestaurant.setModifiedAt(LocalDateTime.now());

        return restaurantRepository.save(findRestaurant);
    }

    public Restaurant findRestaurant(long restaurantId){
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
    }

    public Page<Restaurant> findRestaurants(int page, int size){
        return restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("restaurantId").descending()));

    }

    public void deleteRestaurant(long restaurantId){
        Restaurant findRestaurant = findRestaurant(restaurantId);

        restaurantRepository.delete(findRestaurant);
    }

    public List<Restaurant> getTop10Restaurants() {
        return restaurantRepository.findTop10ByOrderByTotalFavoriteDesc();
    }

}
