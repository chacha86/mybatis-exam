package com.example.basic.domain.auth.filter;

import com.example.basic.global.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyFilterConfig {

    private final ReqResHandler reqResHandler;

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterRegistrationBean() {
        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
        registrationBean.setFilter(new TestFilter()); // TestFilter를 등록하겠다.
        registrationBean.addUrlPatterns("/**"); // 모든 url

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterRegistrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
        registrationBean.setFilter(new AdminFilter(reqResHandler)); // TestFilter를 등록하겠다.
        registrationBean.addUrlPatterns("/admin/*"); // /admin/으로 시작하는 url만 필터 태우겠다.

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
        registrationBean.setFilter(new LoginFilter(reqResHandler)); // TestFilter를 등록하겠다.
        registrationBean.addUrlPatterns("/article/write"); // /admin/으로 시작하는 url만 필터 태우겠다.
        registrationBean.addUrlPatterns("/article/detail/*");
        registrationBean.addUrlPatterns("/article/update/*");
        registrationBean.addUrlPatterns("/article/delete/*");
        registrationBean.addUrlPatterns("/comment/write");

        return registrationBean;
    }
}
