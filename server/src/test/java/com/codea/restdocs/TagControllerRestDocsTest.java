//package com.codea.restdocs;
//
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
//@WebMvcTest(TagController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class TagControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private  TagService tagService;
//    @MockBean
//    private TagMapper tagMapper;
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void postTagTest() throws Exception{
//        TagDto.Post post = new TagDto.Post("sdff");
//        String content = gson.toJson(post);
//
//        given(tagMapper.tagPostDtoToTag(Mockito.any(TagDto.Post.class))).willReturn(new Tag());
//
//        Tag mockResultTag = new Tag();
//        mockResultTag.setTagId(1L);
//        given(tagService.createTag(Mockito.any(Tag.class))).willReturn(mockResultTag);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/tags")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", is(startsWith("/tags"))))
//                .andDo(document(
//                        "post-tag",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                )
//                        ),
//                        responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
//                        )
//                ));
//
//
//    }
//    @Test
//    public void patchTagTest() throws Exception{
//        long tagId= 1L;
//        TagDto.Patch patch = new TagDto.Patch ( tagId, "ddf");
//        String content = gson.toJson(patch);
//
//        TagDto.Response responseDto =
//                new TagDto.Response(1L,
//                        "ddf");
//
//        given(tagMapper.tagPatchDtoToTag(Mockito.any(TagDto.Patch.class))).willReturn(new Tag());
//        given(tagService.updateTag(Mockito.any(Tag.class))).willReturn(new Tag());
//        given(tagMapper.tagToTagResponseDto(Mockito.any(Tag.class))).willReturn(responseDto);
//
//        ResultActions actions =
//                mockMvc.perform(
//                        patch("/tags/{tag-id}", tagId)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("tagId").value(patch.getTagId()))
//                .andExpect(jsonPath("name").value(patch.getName()))
//                .andDo(document("patch-tag",
//
//
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("tag-id").description("회원 식별자")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("tagId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("tagId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                                )
//                        )
//                ));
//    }
//    @Test
//    void getTagTest() throws Exception{
//        TagDto.Response responseDto = new TagDto.Response(1L,"dff");
//
//        given(tagMapper.tagToTagResponseDto(Mockito.any(Tag.class)))
//                .willReturn(responseDto);
//        given(tagService.findTag(Mockito.anyLong()))
//                .willReturn(new Tag());
//
//
//
//        mockMvc.perform(
//                get("/tags/{tagId}", responseDto.getTagId())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpectAll(
//                status().isOk(),
//                jsonPath("tagId").value(responseDto.getTagId()),
//                jsonPath("name").value(responseDto.getName())
//                        ).andDo(document("get-tag",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                pathParameters(
//                        parameterWithName("tagId").description("태그 식별자")
//                ),
//                responseFields(
//                        List.of(
//                                fieldWithPath("tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
//                        )
//                        )
//
//                ));
//
//    }
//    @Test
//    void getTagsTest() throws Exception{
//        Tag tag1 = new Tag(1L,"dff");
//        Tag tag2 = new Tag(2L,"dff2");
//
//        List<TagDto.Response> response = List.of(
//                new TagDto.Response(1L, "dff"),
//                new TagDto.Response(2L, "dff2")
//        );
//
//         int page = 1;
//         int size = 10;
//
//        Page<Tag> pageTags = new PageImpl<>(List.of(tag1,tag2), PageRequest.of(page, size, Sort.by("tagId").descending()), 2);
//
//        given(tagService.findTags(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageTags);
//        given(tagMapper.tagsToTagResponseDto(Mockito.anyList())).willReturn(response);
//
//        mockMvc.perform(
//                get("/tags")
//                        .param("page", "1")
//                        .param("size", "10")
//                        .accept(MediaType.APPLICATION_JSON)
//
//        ).andExpectAll(
//                status().isOk(),
//                jsonPath("$.data[0].name").value(tag1.getName()),
//                jsonPath("$.data[1].name").value(tag2.getName())
//
//        ).andDo(document("get-tags",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                requestParameters(
//                        List.of(
//                                parameterWithName("page").description("페이지 수"),
//                                parameterWithName("size").description("페이지 당 Tag 갯수")
//                        )
//                ),responseFields(
//                        List.of(
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                fieldWithPath("data[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
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
//    void deleteTagTest() throws Exception{
//        doNothing().when(tagService).deleteTag(Mockito.anyLong());
//
//        mockMvc.perform(
//                delete("/tags/{tag-id}",1L)
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer" + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoidGVzdDExMUBnbWFpbC5jb20iLCJzdWIiOiJ0ZXN0MTExQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYxMzc1OSwiZXhwIjoxNjgzNjE1NTU5fQ.krjhuX6Wu4eT8zf9woMBcY1_OYwjnERNTwRPboYqjFE")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-tag",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("tag-id").description("태그 식별자")
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