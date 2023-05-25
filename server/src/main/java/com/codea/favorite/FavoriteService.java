package com.codea.favorite;

import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.member.MemberRepository;
import com.codea.restaurant.Restaurant;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private FavoriteRepository favoriteRepository;
    private RestaurantRepository restaurantRepository;
    private MemberRepository memberRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
        this.favoriteRepository = favoriteRepository;
        this.restaurantRepository = restaurantRepository;
        this.memberRepository = memberRepository;
    }

    public Favorite createFavorite(long restaurantId, String email) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        Favorite favorite = new Favorite();
        favorite.setRestaurant(restaurant);
        favorite.setMember(member);


        int count = favoriteRepository.countByRestaurant_RestaurantId(restaurant.getRestaurantId());
        restaurant.setTotalFavorite(count + 1);
        restaurantRepository.save(restaurant); // 카운트가 업데이트된 restaurant를 저장합니다.

        return favoriteRepository.save(favorite);
    }

    public Favorite findFavorite(long favoriteId) {
        return favoriteRepository.findById(favoriteId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FAVORITE_NOT_FOUND));
    }

    public Page<Favorite> findFavorites(String email, int page, int size) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        long memberId = member.getMemberId();

        return favoriteRepository.findByMember_MemberId(memberId, PageRequest.of(page, size, Sort.by("favoriteId").descending()));
    }

    public List<Favorite> getFavoritesByMember(Member member) {
        return favoriteRepository.findByMember(member);
    }

    // 추가적인 즐겨찾기 관련 메서드

    public void deleteFavorite(long favoriteId) {
        Favorite favorite = findFavorite(favoriteId);
        Restaurant restaurant = favorite.getRestaurant();

        favoriteRepository.delete(favorite);

        int count = favoriteRepository.countByRestaurant_RestaurantId(restaurant.getRestaurantId());
        restaurant.setTotalFavorite(count);

        restaurantRepository.save(restaurant);
    }

}
