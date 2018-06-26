package com.taj.config;

/**
 * Created by MahmoudAhmed on 5/27/2018.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final String ADD_USER_URL              = "/register/add"; // here where error appear
    public static final String GET_ALL_URL               = "/register/getAll";
    public static final String UPDATE_USER_URL           = "/register/update";
    public static final String DELETE_USER_URL           = "/register/delete/{id}";
    public static final String GET_USER_URL              = "/register/get/{id}";
    public static final String GET_INACTIVE_COMPANY_URL  = "/register/getInActive";
    public static final String ACTIVE_COMPANIES_URL      = "/register/activeCompanyUser/{id}";
    public static final String INACTIVE_COMPANIES_URL    = "/register/inActiveCompanyUser/{id}";
    public static final String GET_ACTIVE_COMPANIES_URL  = "/register/getActive";
    public static final String CONFIRM_MAIL_COMPANY_URL  = "/register/confirm/{id}";
    public static final String LOGIN_USER_URL            = "/login/loginUser";
    public static final String LOGGED_USER_URL           = "/login/getLogged";
    public static final String LOGGED_1USER_URL          = "/login/getUser/{id}";
    public static final String ADD_PROFILE_URL           = "/profile/addProfile"; // here where error appear
    public static final String GET_PROFILES_URL          = "/profile/getProfiles"; // here where error appear
    public static final String UPDATE_PROFILE_URL        = "/profile/updateProfile";//updateProfile
    public static final String GET_PROFILE_URL           = "/profile/getProfile/{id}";//updateProfile
    public static final String EXIST_PROFILE_URL         = "/profile/profileExist/{id}";//updateProfile
    public static final String ADD_SCHOOL_PROFILE_URL    = "/schoolProfile/addProfile";//updateProfile
    public static final String GET_SCHOOL_PROFILES_URL   = "/schoolProfile/getProfiles"; // here where error appear
    public static final String GET_SCHOOL_PROFILE_URL    = "/schoolProfile/getProfile/{id}";
    public static final String UPDATE_SCHOOL_PROFILE_URL = "/schoolProfile/updateProfile/{id}";//updateProfile
    public static final String ADD_SCHOOL_REQUEST_URL    = "/schoolRequest/addRequest";
    public static final String GET_SCHOOL_REQUESTS_URL   = "/schoolRequest/getRequests";
    public static final String GET_SCHOOL_REQUEST_URL    = "/schoolRequest/getRequest/{id}";
    public static final String UPDATE_SCHOOL_REQUEST_URL = "/schoolRequest/updateRequest/{id}";
    public static final String DELETE_SCHOOL_REQUEST_URL = "/schoolRequest/deleteRequest/{id}";
    public static final String ADD_COMPANY_OFFER_URL     = "/companyOffer/addOffer";
    public static final String GET_COMPANY_OFFERS_URL    = "/companyOffer/getOffers";
    public static final String SINGLE_COMPANY_OFFER_URL  = "/companyOffer/getOffers/{id}";
    public static final String GET_COMPANY_OFFER_URL     = "/companyOffer/getOffer/{id}";
    public static final String UPDATE_COMPANY_OFFERS_URL = "/companyOffer/updateOffer";
    public static final String DELETE_COMPANY_OFFERS_URL = "/companyOffer/deleteOffer/{id}";
    public static final String GET_ALL_CATEGORIES        = "/cat/getCategories";
    public static final String GET_ALL_CATEGORY          = "/cat/getCategory/{id}";
    public static final String ADD_LOGIN_DETAILS_URL     = "/details/add";
    public static final String GET_LOGIN_DETAILS_URL     = "/details/getAll";
    public static final String GET_LOGIN_DETAIL_URL      = "/details/get/{id}";
    public static final String CHECK_USER_URL            = "/login/isLogged";
    public static final String GET_USER_ID               = "/login/getLoginId";
    public static final String ADD_CATEGORY_URL          = "/cat/addCategory";
    public static final String UPDATE_CATEGORY_URL       = "/cat/updateCategory";
    public static final String DELETE_CATEGORY_URL       = "/cat/deleteCategory";
    public static final String UPDATE_PASSWORD_URL       = "/login/updatePassword";
    public static final String UPDATE_ACTIVE_URL         = "/login/updateActiveState";
    public static final String DELETE_LOGGED_USER_URL    = "/login/deleteUser";
    public static final String GET_NOT_LOGED_COMPANY_URL = "/login/inactiveCompanies";
    public static final String GET_LOGGED_COMPANIES_URL  = "/login/activeCompanies";
    public static final String ACTIVE_LOGGED_USER_URL    ="/login/activeUser/{id}";
    public static final String INACTIVE_LOGGED_USER_URL  = "/login/inActiveUser/{id}";
    public static final String GET_COMPANY_OFFER_DATA    = "/companyOffer/getData/{id}";

    //isLogged


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
    }
    public ApiSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().
                antMatchers(HttpMethod.POST, ADD_USER_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_ALL_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_USER_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_USER_URL).permitAll().
                antMatchers(HttpMethod.PUT, DELETE_USER_URL).permitAll().
                antMatchers(HttpMethod.POST, LOGIN_USER_URL).permitAll().
                antMatchers(HttpMethod.GET, LOGGED_USER_URL).permitAll().
                antMatchers(HttpMethod.GET, LOGGED_1USER_URL).permitAll().
                antMatchers(HttpMethod.POST, ADD_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_PROFILES_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.POST, ADD_SCHOOL_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_SCHOOL_PROFILES_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_SCHOOL_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.POST, ADD_SCHOOL_REQUEST_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUESTS_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUEST_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_REQUEST_URL).permitAll().
                antMatchers(HttpMethod.PUT, DELETE_SCHOOL_REQUEST_URL).permitAll().
                antMatchers(HttpMethod.POST, ADD_COMPANY_OFFER_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_COMPANY_OFFERS_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_COMPANY_OFFER_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_COMPANY_OFFERS_URL).permitAll().
                antMatchers(HttpMethod.PUT, DELETE_COMPANY_OFFERS_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_ALL_CATEGORIES).permitAll().
                antMatchers(HttpMethod.GET, GET_ALL_CATEGORY).permitAll().
                antMatchers(HttpMethod.POST, ADD_LOGIN_DETAILS_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_LOGIN_DETAILS_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_LOGIN_DETAIL_URL).permitAll().
                antMatchers(HttpMethod.POST, CHECK_USER_URL).permitAll().
                antMatchers(HttpMethod.POST, GET_USER_ID).permitAll().
                antMatchers(HttpMethod.POST, ADD_CATEGORY_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_CATEGORY_URL).permitAll().
                antMatchers(HttpMethod.PUT, DELETE_CATEGORY_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_PASSWORD_URL).permitAll().
                antMatchers(HttpMethod.PUT, UPDATE_ACTIVE_URL).permitAll().
                antMatchers(HttpMethod.PUT, DELETE_LOGGED_USER_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_INACTIVE_COMPANY_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_NOT_LOGED_COMPANY_URL).permitAll().
                antMatchers(HttpMethod.PUT, ACTIVE_COMPANIES_URL).permitAll().
                antMatchers(HttpMethod.PUT, ACTIVE_LOGGED_USER_URL).permitAll().
                antMatchers(HttpMethod.PUT, INACTIVE_LOGGED_USER_URL).permitAll().
                antMatchers(HttpMethod.PUT, INACTIVE_COMPANIES_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_ACTIVE_COMPANIES_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_LOGGED_COMPANIES_URL).permitAll().
                antMatchers(HttpMethod.GET, CONFIRM_MAIL_COMPANY_URL).permitAll().
                antMatchers(HttpMethod.GET, EXIST_PROFILE_URL).permitAll().
                antMatchers(HttpMethod.GET, SINGLE_COMPANY_OFFER_URL).permitAll().
                antMatchers(HttpMethod.GET, GET_COMPANY_OFFER_DATA).permitAll().
                anyRequest().authenticated();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}