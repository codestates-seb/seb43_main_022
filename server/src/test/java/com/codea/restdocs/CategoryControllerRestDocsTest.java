//package com.codea.restdocs;
//
//
//import com.codea.category.*;
//import com.codea.tag.*;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
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
//import org.springframework.test.web.servlet.ResultActions;
//
//
////import static com.sun.org.apache.xalan.internal.xsltc.dom.LoadDocument.document;
////import static com.sun.org.apache.xalan.internal.xsltc.dom.LoadDocument.document;
//import java.util.List;
//
//import static com.codea.restdocs.ApiDocumentUtils.getRequestPreProcessor;
//import static com.codea.restdocs.ApiDocumentUtils.getResponsePreProcessor;
////import static com.sun.org.apache.xalan.internal.xsltc.dom.LoadDocument.document;
//import static net.bytebuddy.implementation.FixedValue.value;
////import static org.apache.logging.log4j.ThreadContext.get;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
////import static org.slf4j.MDC.get;
//import static org.springframework.restdocs.headers.HeaderDocumentation.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.startsWith;
//import static org.mockito.BDDMockito.given;
////import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; //!!!
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
////import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//
//@WebMvcTest(CategoryController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class CategoryControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CategoryService categoryService;
//    @MockBean
//    private CategoryMapper categoryMapper;
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void postCategoryTest() throws Exception{
//        CategoryDto.Post post = new CategoryDto.Post("dff");
//        String content = gson.toJson(post);
//
//
//       // CategoryDto.Response response =
//        given(categoryMapper.categoryPostDtoToCategory(Mockito.any(CategoryDto.Post.class))).willReturn(new Category());
//
//        Category mockResultCategory = new Category();
//        mockResultCategory.setCategoryId(1L);
//        given(categoryService.createCategory(Mockito.any(Category.class))).willReturn(mockResultCategory);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/categories")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", is(startsWith("/categories/"))))
//                .andDo(document("post-category",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                )
//                        ),
//                        responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
//                        )
//                ));
//
//
//    }
//   @Test
//    public void patchCategoryTest() throws Exception{
//        long categoryId= 1L;
//        CategoryDto.Patch patch = new CategoryDto.Patch ( categoryId, "ddf2");
//        String content = gson.toJson(patch);
//
//        CategoryDto.Response responseDto =
//                new CategoryDto.Response(1L,
//                        "ddf2");
//
//        given(categoryMapper.categoryPatchDtoToCategory(Mockito.any(CategoryDto.Patch.class))).willReturn(new Category());
//        given(categoryService.updateCategory(Mockito.any(Category.class))).willReturn(new Category());
//        given(categoryMapper.categoryToCategoryResponseDto(Mockito.any(Category.class))).willReturn(responseDto);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        patch("/categories/{category-id}", categoryId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("categoryId").value(patch.getCategoryId()))
//                .andExpect(jsonPath("name").value(patch.getName()))
//                .andDo(document("patch-category",
//
//
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("category-id").description("카테고리 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                )
//                        )
//                ));
//    }
//   @Test
//    void getCategoryTest() throws Exception{
//        CategoryDto.Response responseDto = new CategoryDto.Response(1L,"dff");
//
//        given(categoryMapper.categoryToCategoryResponseDto(Mockito.any(Category.class)))
//                .willReturn(responseDto);
//        given(categoryService.findCategory(Mockito.anyLong()))
//                .willReturn(new Category());
//
//
//
//        mockMvc.perform(
//                get("/categories/{categoryId}", responseDto.getCategoryId())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpectAll(
//                status().isOk(),
//                jsonPath("categoryId").value(responseDto.getCategoryId()),
//                jsonPath("name").value(responseDto.getName())
//        ).andDo(document("get-category",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                pathParameters(
//                        parameterWithName("categoryId").description("카테고리 식별자")
//                ),
//                responseFields(
//                        List.of(
//                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 식별자"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                        )
//                )
//
//        ));
//
//    }
//    @Test
//    void getCategoriesTest() throws Exception{
//        Category category1 = new Category(1L,"dff");
//        Category category2 = new Category(2L,"dff2");
//
//        List<CategoryDto.Response> response = List.of(
//                new CategoryDto.Response(1L, "dff"),
//                new CategoryDto.Response(2L, "dff2")
//        );
//
//        int page = 1;
//        int size = 10;
//
//        Page<Category> pageCategories = new PageImpl<>(List.of(category1,category2), PageRequest.of(page, size, Sort.by("categoryId").descending()), 2);
//
//        given(categoryService.findCategories(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageCategories);
//        given(categoryMapper.categoriesToCategoryResponseDto(Mockito.anyList())).willReturn(response);
//
//        mockMvc.perform(
//                get("/categories")
//                        .param("page", "1")
//                        .param("size", "10")
//                        .accept(MediaType.APPLICATION_JSON)
//
//        ).andExpectAll(
//                status().isOk(),
//                jsonPath("$.data[0].name").value(category1.getName()),
//                jsonPath("$.data[1].name").value(category2.getName())
//
//        ).andDo(document("get-categories",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                requestParameters(
//                        List.of(
//                                parameterWithName("page").description("페이지 수"),
//                                parameterWithName("size").description("페이지 당 Category 갯수")
//                        )
//                ),responseFields(
//                        List.of(
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                fieldWithPath("data[].categoryId").type(JsonFieldType.NUMBER).description("카테고리 식별자"),
//                                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("이름"),
//                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 수"),
//                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("한 페이지의 갯수"),
//                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 태그 수"),
//                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
//                        )
//                )));
//    }
//
//    @Test
//    void deleteCategoryTest() throws Exception{
//        doNothing().when(categoryService).deleteCategory(Mockito.anyLong());
//
//        mockMvc.perform(
//                        delete("/categories/{category-id}",1L)
//                                .header(HttpHeaders.AUTHORIZATION, "Bearer" + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoidGVzdDExMUBnbWFpbC5jb20iLCJzdWIiOiJ0ZXN0MTExQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYxMzc1OSwiZXhwIjoxNjgzNjE1NTU5fQ.krjhuX6Wu4eT8zf9woMBcY1_OYwjnERNTwRPboYqjFE")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-tag",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("category-id").description("카테고리 식별자")
//                        ),
//                        requestHeaders(
//                                headerWithName("Authorization").description("JWT토큰")
//                        )
//                ));
//    }
//
//
//
//}