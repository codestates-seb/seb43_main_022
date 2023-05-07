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

    public Menu updateMenu(long id, Menu menu) {
        Menu findMenu = findMenu(id);

        Optional.ofNullable(menu.getName()).ifPresent(name -> findMenu.setName(name));
        Optional.ofNullable(menu.getPrice()).ifPresent(price -> findMenu.setPrice(price));

        return menuRepository.save(findMenu);
    }

    public Menu findMenu(long id) {
        return menuRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MENU_NOT_FOUND));
    }

    public Page<Menu> findMenus(int page, int size) {
        return menuRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public void deleteMenu(long id) {
        Menu findMenu = findMenu(id);

        menuRepository.delete(findMenu);
    }
}
