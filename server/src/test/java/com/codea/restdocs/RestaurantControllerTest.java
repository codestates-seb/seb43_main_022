//package com.codea;
//
//import com.codea.Menu.Menu;
//import com.codea.Menu.MenuDto;
//import com.codea.favorite.Favorite;
//import com.codea.member.Member;
//import com.codea.member.MemberDto;
//import com.codea.restaurant.*;
//import com.codea.review.Review;
//import com.codea.review.ReviewDto;
//import com.google.gson.Gson;
//import com.jayway.jsonpath.JsonPath;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
//import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//
//@WebMvcTest(controllers = RestaurantController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class RestaurantControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private Gson gson;
//    @MockBean
//    private RestaurantService restaurantService;
//    @MockBean
//    private RestaurantMapper mapper;
//
//    @Test
//    public void postRestaurantTest() throws Exception {
//        RestaurantDto.Post post = new RestaurantDto.Post("맛집", "맛집 설명", "지역", "1234-1234", "10:00 ~", "photo/photo.png");
//        String content = gson.toJson(post);
//
//        Restaurant mockResultRestaurant = new Restaurant();
//        mockResultRestaurant.setRestaurantId(1L);
//
//        given(mapper.restaurantPostDtoToRestaurant(Mockito.any(RestaurantDto.Post.class))).willReturn(new Restaurant());
//        given(restaurantService.createRestaurant(Mockito.anyString(), Mockito.any(Restaurant.class))).willReturn(mockResultRestaurant);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/restaurants")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions.andExpect(status().isCreated())
//                .andExpect(header().string("Location", is(startsWith("/restaurants"))))
//                .andDo(
//                        document("post-restaurant",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("맛집 이름"),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("맛집 설명"),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("지역"),
//                                        fieldWithPath("tel").type(JsonFieldType.STRING).description("전화번호"),
//                                        fieldWithPath("open_time").type(JsonFieldType.STRING).description("영업 시간"),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("사진 (경로)").optional()
//                                )
//                        ),
//                        responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
//                        )
//                ));
//    }
//
//    @Test
//    public void patchRestaurantTest() throws Exception {
//        Long restaurantId = 1L;
//        RestaurantDto.Patch patch = new RestaurantDto.Patch(restaurantId, "맛집", "맛집 설명", "지역", "1234-1234", "10:00 ~", "photo/photo.png");
//        String content = gson.toJson(patch);
//
//        RestaurantDto.Response response = new RestaurantDto.Response(restaurantId, "맛집", "맛집 설명", "지역", "1234-1234",
//                "10:00 ~", "photo/photo.png", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1, 4.5,
//                List.of(new MenuDto.Response(1,"김밥",2000), new MenuDto.Response(2,"라면",2500)), List.of(new ReviewDto.Response(1, "리뷰 제목1", "리뷰 내용1", "photo/photo1.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.GOOD, new MemberDto.ReviewResponse(1L, "닉네임1", "photo/photo1.png")), new ReviewDto.Response(2, "리뷰 제목2", "리뷰 내용2", "photo/photo2.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.NOT_GOOD, new MemberDto.ReviewResponse(2L, "닉네임2", "photo/photo2.png"))));
//
//        given(mapper.restaurantPatchDtoToRestaurant(Mockito.any(RestaurantDto.Patch.class))).willReturn(new Restaurant());
//        given(restaurantService.updateRestaurant(Mockito.anyLong(), Mockito.anyString(), Mockito.any(Restaurant.class))).willReturn(new Restaurant());
//        given(mapper.restaurantToRestaurantResponseDto(Mockito.any(Restaurant.class))).willReturn(response);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        patch("/restaurants/{restaurantId}", restaurantId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("restaurantId").value(patch.getRestaurantId()))
//                .andExpect(jsonPath("name").value(patch.getName()))
//                .andExpect(jsonPath("content").value(patch.getContent()))
//                .andExpect(jsonPath("location").value(patch.getLocation()))
//                .andExpect(jsonPath("tel").value(patch.getTel()))
//                .andExpect(jsonPath("open_time").value(patch.getOpen_time()))
//                .andExpect(jsonPath("photo").value(patch.getPhoto()))
//                .andDo(document("patch-restaurant",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                pathParameters(parameterWithName("restaurantId").description("맛집 식별자")),
//                                requestFields(List.of(
//                                        fieldWithPath("restaurantId").type(JsonFieldType.NUMBER).description("맛집 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("맛집 이름").optional(),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("맛집 설명").optional(),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("맛집 위치").optional(),
//                                        fieldWithPath("tel").type(JsonFieldType.STRING).description("전화번호").optional(),
//                                        fieldWithPath("open_time").type(JsonFieldType.STRING).description("영업시간").optional(),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("사진 (경로)").optional()
//                                )),
//                                responseFields(List.of(
//                                        fieldWithPath("restaurantId").type(JsonFieldType.NUMBER).description("맛집 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("맛집 이름"),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("맛집 설명"),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("맛집 위치"),
//                                        fieldWithPath("tel").type(JsonFieldType.STRING).description("전화번호"),
//                                        fieldWithPath("open_time").type(JsonFieldType.STRING).description("영업시간"),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("사진 (파일시스템의 사진 경로)"),
//                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("맛집 등록일"),
//                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("맛집 정보 수정일"),
//                                        fieldWithPath("total_views").type(JsonFieldType.NUMBER).description("조회수"),
//                                        fieldWithPath("total_reviews").type(JsonFieldType.NUMBER).description("리뷰 수"),
//                                        fieldWithPath("total_favorite").type(JsonFieldType.NUMBER).description("즐겨찾기 수"),
//                                        fieldWithPath("rating").type(JsonFieldType.NUMBER).description("평점"),
//                                        fieldWithPath("menu").type(JsonFieldType.ARRAY).description("메뉴"),
//                                        fieldWithPath("menu[].menuId").type(JsonFieldType.NUMBER).description("메뉴 식별자"),
//                                        fieldWithPath("menu[].name").type(JsonFieldType.STRING).description("메뉴 이름"),
//                                        fieldWithPath("menu[].price").type(JsonFieldType.NUMBER).description("가격"),
//                                        fieldWithPath("reviews").type(JsonFieldType.ARRAY).description("리뷰"),
//                                        fieldWithPath("reviews[].reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
//                                        fieldWithPath("reviews[].title").type(JsonFieldType.STRING).description("리뷰 제목"),
//                                        fieldWithPath("reviews[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
//                                        fieldWithPath("reviews[].photo").type(JsonFieldType.STRING).description("리뷰 사진"),
//                                        fieldWithPath("reviews[].createdAt").type(JsonFieldType.STRING).description("리뷰 작성일"),
//                                        fieldWithPath("reviews[].modifiedAt").type(JsonFieldType.STRING).description("리뷰 수정일"),
//                                        fieldWithPath("reviews[].rating").type(JsonFieldType.STRING).description("맛집 평가"),
//                                        fieldWithPath("reviews[].memberId").type(JsonFieldType.NUMBER).description("멤버 식별자"),
//                                        fieldWithPath("reviews[].memberNickName").type(JsonFieldType.STRING).description("멤버 닉네임")
//                                ))
//                        )
//                );
//    }
//
//    @Test
//    public void getRestaurantTest() throws Exception {
//        Long restaurantId = 1L;
//        RestaurantDto.Response response = new RestaurantDto.Response(restaurantId, "맛집", "맛집 설명", "지역", "1234-1234",
//                "10:00 ~", "photo/photo.png", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1, 4.5,
//                List.of(new MenuDto.Response(1,"김밥",2000), new MenuDto.Response(2,"라면",2500)), List.of(new ReviewDto.Response(1, "리뷰 제목1", "리뷰 내용1", "photo/photo1.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.GOOD, new MemberDto.ReviewResponse(1L, "닉네임", "photo/photo1.png")), new ReviewDto.Response(2, "리뷰 제목2", "리뷰 내용2", "photo/photo2.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.NOT_GOOD, new MemberDto.ReviewResponse(2L, "닉네임2", "photo/photo2.png"))));
//
//        given(restaurantService.findRestaurant(Mockito.anyLong())).willReturn(new Restaurant());
//        given(mapper.restaurantToRestaurantResponseDto(Mockito.any(Restaurant.class))).willReturn(response);
//
//        ResultActions actions = mockMvc.perform(get("/restaurants/{restaurantId}", response).accept(MediaType.APPLICATION_JSON));
//
//
//        actions.andExpect(status().isOk())
//                .andDo(
//                        document("get-restaurant",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                                pathParameters(parameterWithName("restaurantId").description("맛집 식별자")),
//                                responseFields(List.of(
//                                        fieldWithPath("restaurantId").type(JsonFieldType.NUMBER).description("맛집 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("맛집 이름"),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("맛집 설명"),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("맛집 위치"),
//                                        fieldWithPath("tel").type(JsonFieldType.STRING).description("전화번호"),
//                                        fieldWithPath("open_time").type(JsonFieldType.STRING).description("영업시간"),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("사진 (경로)"),
//                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("맛집 등록일"),
//                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("맛집 정보 수정일"),
//                                        fieldWithPath("total_views").type(JsonFieldType.NUMBER).description("조회수"),
//                                        fieldWithPath("total_reviews").type(JsonFieldType.NUMBER).description("리뷰 수"),
//                                        fieldWithPath("total_favorite").type(JsonFieldType.NUMBER).description("즐겨찾기 수"),
//                                        fieldWithPath("rating").type(JsonFieldType.NUMBER).description("평점"),
//                                        fieldWithPath("menu").type(JsonFieldType.ARRAY).description("메뉴"),
//                                        fieldWithPath("menu[].menuId").type(JsonFieldType.NUMBER).description("메뉴 식별자"),
//                                        fieldWithPath("menu[].name").type(JsonFieldType.STRING).description("메뉴 이름"),
//                                        fieldWithPath("menu[].price").type(JsonFieldType.NUMBER).description("가격"),
//                                        fieldWithPath("reviews").type(JsonFieldType.ARRAY).description("리뷰"),
//                                        fieldWithPath("reviews[].reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
//                                        fieldWithPath("reviews[].title").type(JsonFieldType.STRING).description("리뷰 제목"),
//                                        fieldWithPath("reviews[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
//                                        fieldWithPath("reviews[].photo").type(JsonFieldType.STRING).description("리뷰 사진"),
//                                        fieldWithPath("reviews[].createdAt").type(JsonFieldType.STRING).description("리뷰 작성일"),
//                                        fieldWithPath("reviews[].modifiedAt").type(JsonFieldType.STRING).description("리뷰 수정일"),
//                                        fieldWithPath("reviews[].rating").type(JsonFieldType.STRING).description("맛집 평가"),
//                                        fieldWithPath("reviews[].memberId").type(JsonFieldType.NUMBER).description("멤버 식별자"),
//                                        fieldWithPath("reviews[].memberNickName").type(JsonFieldType.STRING).description("멤버 닉네임")
//                                ))
//                        )
//                );
//    }
//
//    @Test
//    public void getRestaurantsTest() throws Exception {
//        Restaurant restaurant1 = new Restaurant(1L, "맛집1", "맛집 설명1", "지역1", "1111-1111", "11:00 ~", "photo/photo1.png", 1, 1, 1, 4.1, new Member(), List.of(new Menu()), List.of(new Review()), List.of(new Favorite()));
//        Restaurant restaurant2 = new Restaurant(2L, "맛집2", "맛집 설명2", "지역2", "2222-2222", "12:00 ~", "photo/photo2.png", 2, 2, 2, 4.2, new Member(), List.of(new Menu()), List.of(new Review()), List.of(new Favorite()));
//
//        RestaurantDto.Response response1 = new RestaurantDto.Response(1L, "맛집1", "맛집 설명1", "지역1", "1111-1111",
//                "11:00 ~", "photo/photo1.png", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1, 4.1,
//                List.of(new MenuDto.Response(1,"김밥",2000), new MenuDto.Response(2,"라면",2500)), List.of(new ReviewDto.Response(1, "리뷰 제목1", "리뷰 내용1", "photo/photo1.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.GOOD, new MemberDto.ReviewResponse(1L, "닉네임1", "photo/photo1.png")), new ReviewDto.Response(2, "리뷰 제목2", "리뷰 내용2", "photo/photo2.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.NOT_GOOD, new MemberDto.ReviewResponse(2L, "닉네임2", "photo/photo2.png"))));
//        RestaurantDto.Response response2 = new RestaurantDto.Response(2L, "맛집2", "맛집 설명2", "지역2", "2222-2222",
//                "12:00 ~", "photo/photo2.png", LocalDateTime.now(), LocalDateTime.now(), 2, 2, 2, 4.2,
//                List.of(new MenuDto.Response(1,"김밥",2000), new MenuDto.Response(2,"라면",2500)), List.of(new ReviewDto.Response(1, "리뷰 제목1", "리뷰 내용1", "photo/photo1.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.GOOD, new MemberDto.ReviewResponse(1L, "닉네임1", "photo/photo1.png")), new ReviewDto.Response(2, "리뷰 제목2", "리뷰 내용2", "photo/photo2.jpg",
//                LocalDateTime.now(), LocalDateTime.now(), Review.Rating.NOT_GOOD, new MemberDto.ReviewResponse(2L, "닉네임2", "photo/photo2.png"))));
//
//        Page<Restaurant> restaurants = new PageImpl<>(List.of(restaurant1, restaurant2), PageRequest.of(1, 10, Sort.by("restaurantId").descending()), 2);
//        List<RestaurantDto.Response> responses = Arrays.asList(response1, response2);
//
//        given(restaurantService.findRestaurants(Mockito.anyInt(),Mockito.anyInt())).willReturn(restaurants);
//        given(mapper.restaurantToRestaurantResponseDtos(Mockito.anyList())).willReturn(responses);
//
//        ResultActions actions = mockMvc.perform(
//                get("/restaurants")
//                        .param("page", "1")
//                        .param("size", "10")
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        MvcResult result = actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.*", hasSize(2)))
//                .andDo(
//                        document("get-restaurants",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                requestParameters(List.of(
//                                        parameterWithName("page").description("페이지 번호"),
//                                        parameterWithName("size").description("페이지 사이즈")
//                                )),
//                                responseFields(List.of(
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                        fieldWithPath("data[].restaurantId").type(JsonFieldType.NUMBER).description("맛집 식별자"),
//                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("맛집 이름"),
//                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("맛집 설명"),
//                                        fieldWithPath("data[].location").type(JsonFieldType.STRING).description("맛집 위치"),
//                                        fieldWithPath("data[].tel").type(JsonFieldType.STRING).description("전화번호"),
//                                        fieldWithPath("data[].open_time").type(JsonFieldType.STRING).description("영업시간"),
//                                        fieldWithPath("data[].photo").type(JsonFieldType.STRING).description("사진 (파일시스템의 사진 경로)"),
//                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("맛집 등록일"),
//                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("맛집 정보 수정일"),
//                                        fieldWithPath("data[].total_views").type(JsonFieldType.NUMBER).description("조회수"),
//                                        fieldWithPath("data[].total_reviews").type(JsonFieldType.NUMBER).description("리뷰 수"),
//                                        fieldWithPath("data[].total_favorite").type(JsonFieldType.NUMBER).description("즐겨찾기 수"),
//                                        fieldWithPath("data[].rating").type(JsonFieldType.NUMBER).description("평점"),
//                                        fieldWithPath("data[].menu").type(JsonFieldType.ARRAY).description("메뉴"),
//                                        fieldWithPath("data[].menu[].menuId").type(JsonFieldType.NUMBER).description("메뉴 식별자"),
//                                        fieldWithPath("data[].menu[].name").type(JsonFieldType.STRING).description("메뉴 이름"),
//                                        fieldWithPath("data[].menu[].price").type(JsonFieldType.NUMBER).description("가격"),
//                                        fieldWithPath("data[].reviews").type(JsonFieldType.ARRAY).description("리뷰"),
//                                        fieldWithPath("data[].reviews[].reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
//                                        fieldWithPath("data[].reviews[].title").type(JsonFieldType.STRING).description("리뷰 제목"),
//                                        fieldWithPath("data[].reviews[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
//                                        fieldWithPath("data[].reviews[].photo").type(JsonFieldType.STRING).description("리뷰 사진"),
//                                        fieldWithPath("data[].reviews[].createdAt").type(JsonFieldType.STRING).description("리뷰 작성일"),
//                                        fieldWithPath("data[].reviews[].modifiedAt").type(JsonFieldType.STRING).description("리뷰 수정일"),
//                                        fieldWithPath("data[].reviews[].rating").type(JsonFieldType.STRING).description("맛집 평가"),
//                                        fieldWithPath("data[].reviews[].memberId").type(JsonFieldType.NUMBER).description("멤버 식별자"),
//                                        fieldWithPath("data[].reviews[].memberNickName").type(JsonFieldType.STRING).description("멤버 닉네임"),
//                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
//                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
//                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
//                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 데이터 수"),
//                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
//                                ))
//                        )
//                )
//                .andReturn();
//
//        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
//        assertThat(list.size(), is(2));
//    }
//
//    @Test
//    public void deleteRestaurantTest() throws Exception {
//
//        Long restaurantId = 1L;
//        doNothing().when(restaurantService).deleteRestaurant(Mockito.anyLong());
//
//        ResultActions actions =
//                mockMvc.perform(
//                        delete("/restaurants/{restaurantsId}", restaurantId));
//
//        actions.andExpect(status().isNoContent())
//                .andDo(
//                        document("delete-restaurant",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                pathParameters(Arrays.asList(parameterWithName("restaurantsId").description("맛집 식별자")))
//                        )
//                );
//    }
//}
//
