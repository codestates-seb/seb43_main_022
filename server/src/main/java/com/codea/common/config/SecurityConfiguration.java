package com.codea.common.config;

import com.codea.auth.filter.JwtAuthenticationFilter;
import com.codea.auth.filter.JwtVerificationFilter;
import com.codea.auth.handler.*;
import com.codea.auth.jwt.JwtTokenizer;
import com.codea.auth.utils.CustomAuthorityUtils;
import com.codea.member.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // Spring Security를 사용하기 위한 필수 설정들을 자동으로 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 메소드 보안 기능 활성화
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberRepository memberRepository;
    private final RedisTemplate redisTemplate;
    private final MemberAuthenticationEntryPoint memberAuthenticationEntryPoint;


    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils,
                                 MemberRepository memberRepository, RedisTemplate redisTemplate,
                                 MemberAuthenticationEntryPoint memberAuthenticationEntryPoint) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberRepository = memberRepository;
        this.redisTemplate = redisTemplate;
        this.memberAuthenticationEntryPoint = memberAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin() // 동일 출처로부터 들어오는 request만 페이지 렌더링을 허용 (H2 웹 콘솔(개발단계용으로) 쓰기 위해 추가한거)
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint(redisTemplate, jwtTokenizer, memberRepository))
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                                .anyRequest().permitAll()                // 모든 HTTP request 요청에 대해서 접근 허용
//                                .antMatchers(HttpMethod.POST, "/*/members").permitAll() // 누구나 접근 가능
//                                .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER")  // USER권한 있눈 사용자만
//                                .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")
//                                .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")
//                                .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER")
//                                .anyRequest().permitAll() // 위에 설정한 요청 외의 모든 요청 허용
                );
//                .oauth2Login(oauth2 -> oauth2
//                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberRepository))  // OAuth 2 인증이 성공한 뒤 실행되는 핸들러를 추가
//                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(Boolean.valueOf(true));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer, redisTemplate);
            jwtAuthenticationFilter.setFilterProcessesUrl("/members/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());


            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils, memberAuthenticationEntryPoint);

            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
//                    .addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class); // OAuth2로그인 성공 시 jwtVerificationFilter 호출
        }
    }

//    @Configuration
//    public class WebMvcConfig implements WebMvcConfigurer {
//
//        @Value("${image.upload.dir}")
//        private String imageUploadDir;
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry
//                    .addResourceHandler("/images/**")
//                    .addResourceLocations("file:" + imageUploadDir + "/");
//        }
//    }
}
