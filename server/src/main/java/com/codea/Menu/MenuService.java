package com.codea.Menu;

import com.codea.common.exception.BusinessLogicException;
import com.codea.common.exception.ExceptionCode;
import com.codea.restaurant.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Menu createMenu(long restaurantId, Menu menu) {
        restaurantRepository.findById(restaurantId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.RESTAURANT_NOT_FOUND));

        return menuRepository.save(menu);
    }

    public Menu updateMenu(long menuId, Menu menu) {
        Menu findMenu = findMenu(menuId);

        Optional.ofNullable(menu.getName()).ifPresent(name -> findMenu.setName(name));
        Optional.ofNullable(menu.getPrice()).ifPresent(price -> findMenu.setPrice(price));

        return menuRepository.save(findMenu);
    }

    public Menu findMenu(long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MENU_NOT_FOUND));
    }

    public Page<Menu> findMenus(long restaurantId, int page, int size) {
        return menuRepository.findByRestaurant_RestaurantId(restaurantId, PageRequest.of(page, size, Sort.by("menuId").descending()));
    }

    public void deleteMenu(long menuId) {
        Menu findMenu = findMenu(menuId);

        menuRepository.delete(findMenu);
    }
}
