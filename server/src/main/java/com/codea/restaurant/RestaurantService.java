package com.codea.restaurant;

import com.codea.Menu.MenuRepository;
import com.codea.address.Address;
import com.codea.address.AddressRepository;
import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import com.codea.review.Review;
import com.codea.review.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.codea.review.Review.ReviewStatus.REVIEW_VALID;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;


    public RestaurantService(RestaurantRepository restaurantRepository, MemberRepository memberRepository,
                             AddressRepository addressRepository) {
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    public Restaurant createRestaurant(String email, Address address, Restaurant restaurant) {

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        String streetAddress = address.getStreetAddress();
        Address findAddress = addressRepository.findByStreetAddress(streetAddress)
                .orElseGet(() -> addressRepository.save(address));

        restaurant.setAddress(findAddress);
        restaurant.setMember(member);

        return restaurantRepository.save(restaurant);
    }


    // 주소가 비어 있으면 = address 가 null이면
    // 데이터 저장 -> address (null) -> error

    // ! 주소가 비어있지 않으면
    // 데이터 저장
//        restaurant.setAddress(address);
//        restaurant.setMember(member);
//
//        return restaurantRepository.save(restaurant);

    @Transactional
    public Restaurant updateRestaurant(long restaurantId, String email, Address address, Restaurant restaurant) {
        // 주소를 수정하면 데이터베이스를 직접 수정하는 게 아닌, 수정된 주소를 데이터베이스에 추가함
        // 만약, 수정된 주소가 데이터베이스에 존재하면, 수정된 주소를 set 한다
        // 수정된 주소가 데이터베이스에 존재하지 않으면, 수정된 주소를 데이터베이스에 추가한다.
        // 주소를 삭제하면 데이터베이스의 값을 삭제하지 않고 그대로 놔둠 -> 나중에 같은 도로명 주소를 저장하는 경우가 생길 수 있기 때문

        Restaurant findRestaurant = findRestaurant(restaurantId);

        if (!findRestaurant.getMember().getEmail().equals(email)) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_EDIT);

        Optional.ofNullable(restaurant.getName()).ifPresent(name -> findRestaurant.setName(name));
        Optional.ofNullable(restaurant.getContent()).ifPresent(content -> findRestaurant.setContent(content));
        Optional.ofNullable(restaurant.getTel()).ifPresent(tel -> findRestaurant.setTel(tel));
        Optional.ofNullable(restaurant.getPhoto()).ifPresent(photo -> findRestaurant.setPhoto(photo));
        Optional.ofNullable(restaurant.getOpen_time()).ifPresent(open_time -> findRestaurant.setOpen_time(open_time));
        Optional.ofNullable(restaurant.getDetailAddress()).ifPresent(detailAddress -> findRestaurant.setDetailAddress(detailAddress));
        findRestaurant.setModifiedAt(LocalDateTime.now());

        if (address != null) {
            String streetAddress = address.getStreetAddress();
            Address findAddress = addressRepository.findByStreetAddress(streetAddress)
                    .orElseGet(() -> addressRepository.save(address));
            restaurant.setAddress(findAddress);
        }

        return restaurantRepository.save(findRestaurant);
    }

    @EntityGraph(attributePaths = "menu")
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

    public Page<Restaurant> getTop10Restaurants(int page, int size) {
//        return restaurantRepository.findAllOrderByTotalFavorite(1,PageRequest.of(page, size, Sort.by("restaurantId").descending()));
        return restaurantRepository.findAllByOrderByTotalFavoriteDesc(PageRequest.of(page, size, Sort.by("restaurantId").descending()));

    }

}
