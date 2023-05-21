package com.codea.Menu;

import com.codea.common.response.MultiResponseDto;
import com.codea.common.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Validated
public class MenuController {
    private final MenuService menuService;
    private final MenuMapper mapper;

    public MenuController(MenuService menuService, MenuMapper mapper) {
        this.menuService = menuService;
        this.mapper = mapper;
    }

    @PostMapping("/restaurants/{restaurant-id}")
    public ResponseEntity postMenu(@PathVariable("restaurant-id") @Positive long restaurantId,
                                   @Valid @RequestBody MenuDto.Post requestBody) {
        Menu menu = menuService.createMenu(restaurantId, mapper.menuPostDtoToMenu(requestBody));

        String ReviewUrl = "/restaurants/" + restaurantId + "/menu";
        URI location = UriCreator.createUri(ReviewUrl, menu.getMenuId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{menu-id}")
    public ResponseEntity patchMenu(@PathVariable("menu-id") @Positive long menuId,
                                      @Valid @RequestBody MenuDto.Patch requestBody) {
        Menu menu = menuService.updateMenu(menuId, mapper.menuPatchDtoToMenu(requestBody));

        return new ResponseEntity<>(mapper.menuToMenuResponseDto(menu),HttpStatus.OK);
    }

    @GetMapping("/{menu-id}")
    public ResponseEntity getMenu(@PathVariable("menu-id") @Positive long menuId) {
        Menu menu = menuService.findMenu(menuId);

        return new ResponseEntity<>(mapper.menuToMenuResponseDto(menu),HttpStatus.OK);
    }

    @GetMapping("/restaurants/{restaurant-id}")
    public ResponseEntity getMenus(@PathVariable("restaurant-id") @Positive long restaurantId,
                                   @Positive @RequestParam(value = "page", required = false) Integer page,
                                   @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Menu> menuPage = menuService.findMenus(restaurantId, page - 1, size);
        List<Menu> menu = menuPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.menuToMenuResponseDto(menu), menuPage), HttpStatus.OK);
    }

    @DeleteMapping("/{menu-id}")
    public ResponseEntity deleteMenu(@PathVariable("menu-id") @Positive long menuId) {
        menuService.deleteMenu(menuId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
