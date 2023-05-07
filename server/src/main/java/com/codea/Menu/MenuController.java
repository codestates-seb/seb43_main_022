package com.codea.Menu;

import com.codea.response.MultiResponseDto;
import com.codea.utils.UriCreator;
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
@RequestMapping
@Validated
public class MenuController {
    private final static String MENU_DEFAULT_URL = "/menus";
    private final MenuService menuService;

    private final MenuMapper mapper;

    public MenuController(MenuService menuService, MenuMapper mapper) {
        this.menuService = menuService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMenu(@Valid @RequestBody MenuDto.Post requestBody) {
        Menu menu = menuService.createMenu(mapper.menuPostDtoToMenu(requestBody));

        URI location = UriCreator.createUri(MENU_DEFAULT_URL, menu.getId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchMenu(@PathVariable("id") @Positive long id,
                                      @Valid @RequestBody MenuDto.Patch requestBody) {
        Menu menu = menuService.updateMenu(id, mapper.menuPatchDtoToMenu(requestBody));

        return new ResponseEntity<>(mapper.menuToMenuResponseDto(menu),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMenu(@PathVariable("id") @Positive long id) {
        Menu menu = menuService.findMenu(id);

        return new ResponseEntity<>(mapper.menuToMenuResponseDto(menu),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMenus(@Positive @RequestParam(value = "page", required = false) Integer page,
                                     @Positive @RequestParam(value = "size", required = false) Integer size) {
        if(page == null) page = 1;
        if(size == null) size = 10;
        Page<Menu> menuPage = menuService.findMenus(page - 1, size);
        List<Menu> menu = menuPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.menuToMenuResponseDto(menu), menuPage), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMenu(@PathVariable("id") @Positive long id) {
        menuService.deleteMenu(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
