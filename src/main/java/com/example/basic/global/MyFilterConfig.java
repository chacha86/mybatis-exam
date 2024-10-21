package com.example.basic.global;

import com.example.basic.domain.auth.auth.filter.AdminFilter;
import com.example.basic.domain.auth.auth.filter.LoginFilter;
import com.example.basic.domain.auth.auth.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

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
        registrationBean.setFilter(new AdminFilter()); // TestFilter를 등록하겠다.
        registrationBean.addUrlPatterns("/admin/*"); // /admin/으로 시작하는 url만 필터 태우겠다.

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistrationBean() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
        registrationBean.setFilter(new LoginFilter()); // TestFilter를 등록하겠다.
        registrationBean.addUrlPatterns("/article/write"); // /admin/으로 시작하는 url만 필터 태우겠다.
        registrationBean.addUrlPatterns("/article/detail/*");
        registrationBean.addUrlPatterns("/article/update/*");
        registrationBean.addUrlPatterns("/article/delete/*");

        return registrationBean;
    }
}
