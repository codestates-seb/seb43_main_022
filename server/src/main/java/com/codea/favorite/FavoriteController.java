package com.codea.favorite;

import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private FavoriteService favoriteService;
    private RestaurantRepository restaurantRepository;
    private MemberRepository memberRepository;

    public FavoriteController(FavoriteService favoriteService, RestaurantRepository restaurantRepository,
                              MemberRepository memberRepository) {
        this.favoriteService = favoriteService;
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/add")
    public void addToFavorites(@RequestBody FavoriteDto.AddFavoriteRequest request) {
        // 요청에서 식당 ID와 회원 ID를 받아와서 해당 식당을 즐겨찾기에 추가하는 기능
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        favoriteService.addToFavorites(restaurant, member);
    }

    @PostMapping("/remove")
    public void removeFromFavorites(@RequestBody FavoriteDto.RemoveFavoriteRequest request) {
        // 요청에서 식당 ID와 회원 ID를 받아와서 해당 식당을 즐겨찾기에서 제거하는 기능
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        favoriteService.removeFromFavorites(restaurant, member);
    }

    // 추가적인 즐겨찾기 관련 API 엔드포인트
}