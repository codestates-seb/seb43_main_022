package com.codea.Menu;

import com.codea.exception.BusinessLogicException;
import com.codea.exception.ExceptionCode;
import com.codea.member.Member;
import com.codea.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu createMenu(Menu menu) {
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

    public Page<Menu> findMenus(int page, int size) {
        return menuRepository.findAll(PageRequest.of(page, size, Sort.by("menuId").descending()));
    }

    public void deleteMenu(long menuId) {
        Menu findMenu = findMenu(menuId);

        menuRepository.delete(findMenu);
    }
}
