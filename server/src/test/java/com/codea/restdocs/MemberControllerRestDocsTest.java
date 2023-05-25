//package com.codea.restdocs.member;
//
//import com.codea.member.MemberController;
//import com.codea.member.MemberDto;
//
//import com.codea.member.Member;
//import com.codea.member.MemberMapper;
//import com.codea.member.MemberService;
//import com.codea.review.Review;
//import com.codea.review.ReviewDto;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
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
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static com.codea.util.ApiDocumentUtils.getRequestPreProcessor;
//import static com.codea.util.ApiDocumentUtils.getResponsePreProcessor;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.startsWith;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.restdocs.headers.HeaderDocumentation.*;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@WebMvcTest(controllers = MemberController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class MemberControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MemberService memberService;
//
//    @MockBean
//    private MemberMapper mapper;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    public void postMemberTest() throws Exception {
//        // given
//        MemberDto.Post post = new MemberDto.Post("test1@test.com", "test",
//                "김아무개", "photo Url", "위치", true);
//        String content = gson.toJson(post);
//
//        MemberDto.Response responseDto = new MemberDto.Response(1L, "김아무개", "test1@test.com",
//                "seoul", "사진 위치",
//                List.of(new ReviewDto.Response(1L, "리뷰 제목", "리뷰 내용", "photo/photo1.png",LocalDateTime.now(), LocalDateTime.now(),  Review.Rating.GOOD, 1L, "홍길동")));
//
//
//        // willReturn()이 최소한 null은 아니어야 한다.
//        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
//
//        Member mockResultMember = new Member();
//        mockResultMember.setMemberId(1L);
//        given(memberService.createMember(Mockito.any(Member.class))).willReturn(mockResultMember);
//
//        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseDto);
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/members/signup")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", is(startsWith("/members/"))))
//                .andDo(document("post-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("password").type(JsonFieldType.STRING).description("이름"),
//                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("멤버 닉네임"),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("프로필 사진"),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("위치")
//                                )
//                        ),
//                        responseHeaders(
//                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
//                        )
//                ));
//
//    }
//
//    @Test
//    public void patchMemberTest() throws Exception {
//        // given
//        long memberId = 1L;
//        MemberDto.Patch patch = new MemberDto.Patch(memberId, "changed MemberNickName1",
//                "changed Password1", "changed Location1", "changed Image1");
//        String content = gson.toJson(patch);
//
//        MemberDto.Response responseDto =
//                new MemberDto.Response(1L, "changed MemberNickName1",
//                        "test1@test.com", "changed Location1", "changed Image1",
//                        List.of(new ReviewDto.Response(1L, "리뷰 제목", "리뷰 내용", "photo/photo1.png",LocalDateTime.now(), LocalDateTime.now(),  Review.Rating.GOOD, new MemberDto.ReviewResponse(1L, "닉네임1", "photo/photo1.png"))));
//
//        // willReturn()이 최소한 null은 아니어야 한다.
//        given(mapper.memberPatchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
//
//        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
//
//        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseDto);
//
//        Mockito.doNothing().when(memberService).sameMemberTest(Mockito.anyLong(), Mockito.anyString());
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        patch("/members/{member-id}", memberId)
//                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoidGVzdDExMUBnbWFpbC5jb20iLCJzdWIiOiJ0ZXN0MTExQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYxMzc1OSwiZXhwIjoxNjgzNjE1NTU5fQ.krjhuX6Wu4eT8zf9woMBcY1_OYwjnERNTwRPboYqjFE") // JWT 토큰 값 설정
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//
//        // then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.memberId").value(patch.getMemberId()))
//                .andExpect(jsonPath("$.nickName").value(patch.getNickName()))
//                .andExpect(jsonPath("$.location").value(patch.getLocation()))
//                .andExpect(jsonPath("$.photo").value(patch.getPhoto()))
//                .andDo(document("patch-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("member-id").description("회원 식별자")
//                        ),
//                        requestHeaders(
//                                headerWithName("Authorization").description("JWT토큰")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
//                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임").optional(),
//                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("위치").optional(),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("프로필 이미지").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임"),
//                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                        fieldWithPath("location").type(JsonFieldType.STRING).description("위치"),
//                                        fieldWithPath("photo").type(JsonFieldType.STRING).description("프로필 이미지"),
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
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void getMemberTest() throws Exception {
//        MemberDto.Response responseDto =
//                new MemberDto.Response(1L, "changed MemberNickName1",
//                        "test1@test.com", "changed Location1", "changed Image1",
//                        List.of(new ReviewDto.Response(1L, "리뷰 제목", "리뷰 내용", "photo/photo1.png",LocalDateTime.now(), LocalDateTime.now(),  Review.Rating.GOOD, 1L, "홍길동")));
//
//        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseDto);
//        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());
//
//        mockMvc.perform(
//                get("/members/{member-id}", responseDto.getMemberId())
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andExpectAll(
//                status().isOk(),
//                jsonPath("$.memberId").value(responseDto.getMemberId()),
//                jsonPath("$.nickName").value(responseDto.getNickName()),
//                jsonPath("$.email").value(responseDto.getEmail()),
//                jsonPath("$.location").value(responseDto.getLocation()),
//                jsonPath("$.photo").value(responseDto.getPhoto())
//        ).andDo(document("get-member",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                pathParameters(
//                        parameterWithName("member-id").description("회원 식별자")
//                ),
//                responseFields(
//                        List.of(
//                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임"),
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
//                                fieldWithPath("location").type(JsonFieldType.STRING).description("장소"),
//                                fieldWithPath("photo").type(JsonFieldType.STRING).description("프로필 이미지"),
//                                fieldWithPath("reviews").type(JsonFieldType.ARRAY).description("리뷰"),
//                                fieldWithPath("reviews[].reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
//                                fieldWithPath("reviews[].title").type(JsonFieldType.STRING).description("리뷰 제목"),
//                                fieldWithPath("reviews[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
//                                fieldWithPath("reviews[].photo").type(JsonFieldType.STRING).description("리뷰 사진"),
//                                fieldWithPath("reviews[].createdAt").type(JsonFieldType.STRING).description("리뷰 작성일"),
//                                fieldWithPath("reviews[].modifiedAt").type(JsonFieldType.STRING).description("리뷰 수정일"),
//                                fieldWithPath("reviews[].rating").type(JsonFieldType.STRING).description("맛집 평가"),
//                                fieldWithPath("reviews[].memberId").type(JsonFieldType.NUMBER).description("멤버 식별자"),
//                                fieldWithPath("reviews[].memberNickName").type(JsonFieldType.STRING).description("멤버 닉네임")
//                        )
//                )
//        ));
//
//    }
//
//    @Test
//    void getMembersTest() throws Exception {
//        Member member1 = new Member(1L, "changed MemberNickName1",
//                "test1@test.com", "changed Location1",
//                "changed Image1", Member.MemberStatus.MEMBER_ACTIVE);
//
//        Member member2 = new Member(2L, "changed MemberNickName2",
//                "test2@test.com", "changed Location2",
//                "changed Image2", Member.MemberStatus.MEMBER_ACTIVE);
//
//        int page = 1;
//        int size = 10;
//
//        Page<Member> pageMembers = new PageImpl<>(List.of(member1, member2),
//                PageRequest.of(page , size, Sort.by("memberId").descending()), 2);
//        List<MemberDto.Response> response = List.of(
//                new MemberDto.Response(1L, "changed MemberNickName1",
//                        "test1@test.com", "changed Location1", "changed Image1",
//                        List.of(new ReviewDto.Response(1L, "리뷰 제목", "리뷰 내용", "photo/photo1.png",LocalDateTime.now(), LocalDateTime.now(),  Review.Rating.GOOD, 1L, "홍길동"))),
//
//                new MemberDto.Response(2L, "changed MemberNickName2",
//                        "test2@test.com", "changed Location2", "changed Image2",
//                        List.of(new ReviewDto.Response(1L, "리뷰 제목", "리뷰 내용", "photo/photo1.png",LocalDateTime.now(), LocalDateTime.now(),  Review.Rating.GOOD, 1L, "홍길동")))
//        );
//
//        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageMembers);
//        given(mapper.membersToMemberResponseDto(Mockito.anyList())).willReturn(response);
//
//        mockMvc.perform(
//                get("/members?page=" + page + "&size=" + size)
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andExpectAll(
//                status().isOk(),
//                jsonPath("$.data[0].email").value(member1.getEmail()), // 페이지네이션 정렬에 따른 순서 주의
//                jsonPath("$.data[1].email").value(member2.getEmail())
//        ).andDo(document("get-members",
//                getRequestPreProcessor(),
//                getResponsePreProcessor(),
//                requestParameters(
//                        List.of(
//                                parameterWithName("page").description("페이지 수"),
//                                parameterWithName("size").description("페이지 당 Member 갯수")
//                        )
//                ), responseFields(
//                        List.of(
////                                fieldWithPath("uri").type(JsonFieldType.STRING).description("요청한 리소스의 URI 정보"),
//                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
//                                fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                fieldWithPath("data[].nickName").type(JsonFieldType.STRING).description("닉네임"),
//                                fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
//                                fieldWithPath("data[].location").type(JsonFieldType.STRING).description("위치"),
//                                fieldWithPath("data[].photo").type(JsonFieldType.STRING).description("프로필 이미지"),
//                                fieldWithPath("reviews").type(JsonFieldType.ARRAY).description("리뷰"),
//                                fieldWithPath("reviews[].reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
//                                fieldWithPath("reviews[].title").type(JsonFieldType.STRING).description("리뷰 제목"),
//                                fieldWithPath("reviews[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
//                                fieldWithPath("reviews[].photo").type(JsonFieldType.STRING).description("리뷰 사진"),
//                                fieldWithPath("reviews[].createdAt").type(JsonFieldType.STRING).description("리뷰 작성일"),
//                                fieldWithPath("reviews[].modifiedAt").type(JsonFieldType.STRING).description("리뷰 수정일"),
//                                fieldWithPath("reviews[].rating").type(JsonFieldType.STRING).description("맛집 평가"),
//                                fieldWithPath("reviews[].memberId").type(JsonFieldType.NUMBER).description("멤버 식별자"),
//                                fieldWithPath("reviews[].memberNickName").type(JsonFieldType.STRING).description("멤버 닉네임"),
//                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
//                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 수"),
//                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("한 페이지의 갯수"),
//                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 조회 수"),
//                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
//                        )
//                )));
//    }
//
//    @Test
//    void deleteMemberTest() throws Exception { // 결론적으로 spring연결 확인하는 정도 밖에 의미 없음
//
//        doNothing().when(memberService).deleteMember(Mockito.anyLong()); // 그나마 하나 있는 기능 아무것도 안하게 만들기
//
//        mockMvc.perform(
//                        delete("/members/{member-id}", 1L)
//                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoidGVzdDExMUBnbWFpbC5jb20iLCJzdWIiOiJ0ZXN0MTExQGdtYWlsLmNvbSIsImlhdCI6MTY4MzYxMzc1OSwiZXhwIjoxNjgzNjE1NTU5fQ.krjhuX6Wu4eT8zf9woMBcY1_OYwjnERNTwRPboYqjFE") // JWT 토큰 값 설정
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-member",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("member-id").description("회원 식별자")
//                        ),
//                        requestHeaders(
//                                headerWithName("Authorization").description("JWT토큰")
//                        )
//                ));
//    }
//
//
//}