//package com.taj.config;
//
///**
// * Created by MahmoudAhmed on 5/27/2018.
// */
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@EnableWebSecurity
//public class ApiSecurity extends WebSecurityConfigurerAdapter {
//
//
//    public static final String ADD_USER_URL = "/register/add"; // here where error appear
//    public static final String GET_ALL_URL = "/register/getAll";
//    public static final String UPDATE_USER_URL = "/register/update";
//    public static final String DELETE_USER_URL = "/register/delete/{id}";
//    public static final String GET_USER_URL = "/register/get/{id}";
//    public static final String GET_INACTIVE_COMPANY_URL = "/register/getInActive";
//    public static final String ACTIVE_COMPANIES_URL = "/register/activeCompanyUser/{id}";
//    public static final String INACTIVE_COMPANIES_URL = "/register/inActiveCompanyUser/{id}";
//    public static final String GET_ACTIVE_COMPANIES_URL = "/register/getActive";
//    public static final String CONFIRM_MAIL_COMPANY_URL = "/register/confirm/{id}";
//    public static final String LOGIN_USER_URL = "/login/loginUser";
//    public static final String LOGGED_USER_URL = "/login/getAll";
//    public static final String LOGGED_1USER_URL = "/login/get/{id}";
//    public static final String ADD_PROFILE_URL = "/profile/addProfile"; // here where error appear
//    public static final String GET_PROFILES_URL = "/profile/getProfiles"; // here where error appear
//    public static final String UPDATE_PROFILE_URL = "/profile/updateProfile";//updateProfile
//    public static final String GET_PROFILE_URL = "/profile/getProfile/{id}";//updateProfile
//    public static final String EXIST_PROFILE_URL = "/profile/profileExist/{id}";//updateProfile
//    public static final String ADD_SCHOOL_PROFILE_URL = "/schoolProfile/addProfile";//updateProfile
//    public static final String GET_SCHOOL_PROFILES_URL = "/schoolProfile/getProfiles"; // here where error appear
//    public static final String GET_SCHOOL_PROFILE_URL = "/schoolProfile/getProfile/{id}";
//    public static final String EXIST_SCHOOL_PROFILE_URL = "/schoolProfile/profileExist/{id}";
//    public static final String DELETE_SCHOOL_PROFILE_URL = "/schoolProfile/delete/{id}";
//    public static final String UPDATE_SCHOOL_PROFILE_URL = "/schoolProfile/updateProfile";//updateProfile
//    public static final String ADD_SCHOOL_REQUEST_URL = "/schoolRequest/addRequest";
//    public static final String GET_SCHOOL_ALL_REQUESTS = "/schoolRequest/getRequests";
//    public static final String GET_SCHOOL_REQUEST_URL = "/schoolRequest/getSchoolRequests/{id}";
//    public static final String GET_SCHOOL_REQUESTS_URL = "/schoolRequest/getRequest/{id}";
//    public static final String UPDATE_SCHOOL_REQUEST_URL = "/schoolRequest/updateRequest";
//    public static final String DELETE_SCHOOL_REQUEST_URL = "/schoolRequest/deleteRequest/{id}";
//    public static final String FILTER_IS_AVAILABLE_URL = "/schoolRequest/filterAvailable/{isAvailable}";
//    public static final String FILTER_IS_CONFIRM_URL = "/schoolRequest/filterConfirm/{isConfirm}";
//    public static final String FILTER_TITLE_URL = "/schoolRequest/filterTitle/{title}";
//    public static final String FILTER_EXPLAIN_URL = "/schoolRequest/filterExplain/{explain}";
//    public static final String FILTER_CAT_URL = "/schoolRequest/filterCat/{cat}";
//    public static final String FILTER_PLACE_URL = "/schoolRequest/filterPlace/{place}";
//    public static final String ADD_COMPANY_OFFER_URL = "/companyOffer/addOffer";
//    public static final String GET_COMPANY_OFFERS_URL = "/companyOffer/getOffers";
//    public static final String SINGLE_COMPANY_OFFER_URL = "/companyOffer/getOffers/company/{id}";
//    public static final String GET_COMPANY_OFFER_URL = "/companyOffer/getOffer/{id}";
//    public static final String UPDATE_COMPANY_OFFERS_URL = "/companyOffer/updateOffer";
//    public static final String DELETE_COMPANY_OFFERS_URL = "/companyOffer/deleteOffer/{id}";
//    public static final String GET_ALL_CATEGORIES = "/cat/getCategories";
//    public static final String GET_ALL_CATEGORY = "/cat/getCategory/{id}";
//    public static final String ADD_LOGIN_DETAILS_URL = "/details/add";
//    public static final String GET_LOGIN_DETAILS_URL = "/details/getAll";
//    public static final String GET_LOGIN_DETAIL_URL = "/details/get/{id}";
//    public static final String GET_LOGIN_LIST_DETAIL_URL = "/details/getCompanyDetails/{id}";
//    public static final String CHECK_USER_URL = "/login/isLogged";
//    public static final String GET_USER_ID = "/login/getLoginId";
//    public static final String ADD_CATEGORY_URL = "/cat/addCategory";
//    public static final String UPDATE_CATEGORY_URL = "/cat/updateCategory";
//    public static final String DELETE_CATEGORY_URL = "/cat/deleteCategory";
//    public static final String UPDATE_PASSWORD_URL = "/login/updatePassword";
//    public static final String UPDATE_ACTIVE_URL = "/login/updateActiveState";
//    public static final String DELETE_LOGGED_USER_URL = "/login/deleteUser";
//    public static final String GET_NOT_LOGED_COMPANY_URL = "/login/inactiveCompanies";
//    public static final String GET_LOGGED_COMPANIES_URL = "/login/activeCompanies";
//    public static final String ACTIVE_LOGGED_USER_URL = "/login/activeUser/{id}";
//    public static final String INACTIVE_LOGGED_USER_URL = "/login/inActiveUser/{id}";
//    public static final String GET_COMPANY_OFFER_DATA = "/companyOffer/getData/{id}";
//    public static final String ADD_TAKATAF_CATEGORY_URL = "/takatafCategory/addCategory";
//    public static final String GET_TAKATAF_CATEGORIES_URL = "/takatafCategory/getCategories";
//    public static final String GET_TAKATAF_CATEGORY_URL = "/takatafCategory/getCategory/{id}";
//    public static final String UPDATE_TAKATAF_CATEGORY_URL = "/takatafCategory/updateCategory";
//    public static final String DELETE_TAKATAF_CATEGORY_URL = "/takatafCategory/deleteCategory";
//    public static final String ADD_SCHOOL_CATEGORY_URL = "/schoolCategory/addCategory";
//    public static final String GET_SCHOOL_CATEGORIES_URL = "/schoolCategory/getCategories";
//    public static final String GET_SCHOOL_CATEGORY_URL = "/schoolCategory/getCategory/{id}";
//    public static final String UPDATE_SCHOOL_CATEGORY_URL = "/schoolCategory/updateCategory";
//    public static final String DELETE_SCHOOL_CATEGORY_URL = "/schoolCategory/deleteCategory";
//    public static final String ADD_COMPANY_SEE_REQUEST_URL = "/seen/add";//ADD_COMPANY_SEE_REQUEST_URL
//    public static final String GET_COMPANY_SEE_REQUEST_URL = "/seen/getAll";
//    public static final String GET_COMPANY_SEE_REQUEST_BYR_URL = "/seen/getRequest/{id}";//GET_COMPANY_SEE_REQUEST_BYR_URL
//    public static final String GET_COMPANY_SEE_REQUEST_BYC_URL = "/seen/getCompany/{id}";
//    public static final String GET_COMPANY_SEE_REQUEST_BYS_URL = "/seen/getSeen/{id}";
//    public static final String GET_COMPANY_SEE_REQUEST_BYB_URL = "/seen/getBoth/{requestId}/{companyId}";
//    public static final String UPDATE_COMPANY_SEE_REQUEST_URL = "/seen/update";
//    public static final String DElETE_COMPANY_SEE_REQUEST_URL = "/seen/delete";
//    public static final String ADD_REQUEST_ENQUIRY_URL = "/enquiry/add";
//    public static final String GET_REQUEST_ENQUIRIES_URL = "/enquiry/getAll";
//    public static final String GET_REQUEST_ENQUIRY_URL = "/enquiry/get/{id}";
//    public static final String GET_REQUEST_ENQUIRY_ID_URL = "/enquiry/getReq/{id}";
//    public static final String UPDATE_REQUEST_ENQUIRY_URL = "/enquiry/updateEnquiry";
//    public static final String DELETE_REQUEST_ENQUIRY_URL = "/enquiry/deleteEnquiry";
//    public static final String ADD_RESPONSE_ENQUIRY_URL = "/response/add";
//    public static final String GET_RESPONSE_ENQUIRIES_URL = "/response/getAll";
//    public static final String GET_RESPONSE_ENQUIRY_URL = "/response/get/{id}";
//    public static final String GET_RESPONSE_ENQUIRY_ID_URL = "/response/getReq/{id}";
//    public static final String UPDATE_RESPONSE_ENQUIRY_URL = "/response/updateEnquiry";
//    public static final String DELETE_RESPONSE_ENQUIRY_URL = "/response/deleteEnquiry";
//    public static final String ADD_RECEIVE_PLACE_URL = "/receivePlace/add";
//    public static final String GET_RECEIVE_PLACES_URL = "/receivePlace/getAll";
//    public static final String GET_RECEIVE_PLACE_URL = "/receivePlace/get/{id}";
//    public static final String UPDATE_RECEIVE_PLACE_URL = "/receivePlace/update";
//    public static final String DELETE_RECEIVE_PLACE_URL = "/receivePlace/delete";
//    public static final String ADD_FIRST_PRICE_URL = "/takatafFirst/addPrice";
//    public static final String GET_FIRST_PRICES_URL = "/takatafFirst/getPrices";
//    public static final String GET_FIRST_PRICE_URL = "/takatafFirst/getPrice/{id}";
//    public static final String UPDATE_FIRST_PRICE_URL = "/takatafFirst/updatePrice";
//    public static final String DELETE_FIRST_PRICE_URL = "/takatafFirst/deletePrice";
//    public static final String ADD_SECOND_PRICE_URL = "/takatafSecond/addPrice";
//    public static final String GET_SECOND_PRICES_URL = "/takatafSecond/getPrices";
//    public static final String GET_SECOND_PRICE_URL = "/takatafSecond/getPrice/{id}";
//    public static final String UPDATE_SECOND_PRICE_URL = "/takatafSecond/updatePrice";
//    public static final String DELETE_SECOND_PRICE_URL = "/takatafSecond/deletePrice";
//    public static final String ADD_THIRD_PRICE_URL = "/takatafThird/addPrice";
//    public static final String GET_THIRD_PRICES_URL = "/takatafThird/getPrices";
//    public static final String GET_THIRD_PRICE_URL = "/takatafThird/getPrice/{id}";
//    public static final String UPDATE_THIRD_PRICE_URL = "/takatafThird/updatePrice";
//    public static final String DELETE_THIRD_PRICE_URL = "/takatafThird/deletePrice";
//    public static final String ADD_TAKATF_TENDER_URL = "/takatafTender/add";
//    public static final String GET_TAKATF_TENDERS_URL = "/takatafTender/getAll";
//    public static final String GET_TAKATF_TENDER_URL = "/takatafTender/get/{id}";
//    public static final String GET_TAKATF_TENDER_C_URL = "/takatafTender/getCompany/{id}";
//    public static final String GET_TAKATF_TENDER_CAT_URL = "/takatafTender/getCategory/{id}";
//    public static final String GET_TAKATF_TENDER_AVAL_URL = "/takatafTender/getAvailable/{id}";
//    public static final String GET_TAKATF_TENDER_CONFIRM_URL = "/takatafTender/getConfirm/{id}";
//    public static final String FILTER_TAKATF_TENDER_TITLE_URL = "/takatafTender/getTitle/{title}";
//    public static final String FILTER_TAKATF_TENDER_EXPLAIN_URL = "/takatafTender/getExplain/{explain}";
//    public static final String UPDATE_TAKATF_TENDER_URL = "/takatafTender/update";
//    public static final String DELETE_TAKATF_TENDER_URL = "/takatafTender/delete/{id}";
//    public static final String ADD_TAKATF_TENDER_SEE_URL = "/tenderSeen/add";
//    public static final String GET_TAKATF_TENDERS_SEE_URL = "/tenderSeen/getAll";
//    public static final String GET_TAKATF_TENDER_SEE_URL = "/tenderSeen/get/{id}";
//    public static final String GET_TAKATF_TENDERS_SEE_SCHOOL_URL = "/tenderSeen/getSchool/{schoolId}";
//    public static final String GET_TAKATF_TENDERS_SEE_TENDER_URL = "/tenderSeen/getTender/{tenderId}";
//    public static final String UPDATE_TAKATF_TENDERS_SEE_URL = "/tenderSeen/update";
//    public static final String DELETE_TAKATF_TENDERS_SEE_URL = "/tenderSeen/delete/{id}";
//    public static final String ADD_TAKATF_TENDER_REQUEST_URL = "/tenderRequest/add";
//    public static final String GET_TAKATF_TENDER_REQUESTS_URL = "/tenderRequest/getAll";
//    public static final String GET_TAKATF_TENDER_REQUEST_URL = "/tenderRequest/get/{id}";
//    public static final String GET_TAKATF_TENDER_REQUEST_APROVE_URL = "/tenderRequest/getAprove/{aproveId}";
//    public static final String GET_TAKATF_TENDER_REQUESTS_SCHOOL_URL = "/tenderRequest/getSchool/{schoolId}";
//    public static final String GET_TAKATF_TENDER_REQUESTS_TENDER_URL = "/tenderRequest/getTender/{tenderId}";
//    public static final String UPDATE_TAKATF_TENDER_REQUEST_URL = "/tenderRequest/update";
//    public static final String ACCEPT_TAKATF_TENDER_REQUEST_URL = "/tenderRequest/accept/{id}";
//    public static final String REFUSE_TAKATF_TENDER_REQUEST_URL = "/tenderRequest/refuse/{id}";
//    public static final String DELETE_TAKATF_TENDER_REQUEST_URL = "/tenderRequest/delete/{id}";
//    public static final String ADD_COMAPNY_RESPONSE_SCHOOL_REQ_URL = "/response/school/request/add";
//    public static final String GET_COMAPNY_RESPONSE_SCHOOL_REQS_URL = "/response/school/request/getAll";
//    public static final String GET_COMAPNY_RESPONSE_SCHOOL_REQ_URL = "/response/school/request/get/{id}";
//    public static final String GET_COMAPNY_RESPONSE_SCHOOL_REQ_COMPANY_URL = "/response/school/request/getCompany/{companyId}";
//    public static final String GET_COMAPNY_RESPONSE_SCHOOL_REQ_REQUEST_URL = "/response/school/request/getRequest/{requestId}";
//    public static final String GET_COMAPNY_RESPONSE_SCHOOL_REQ_ACCEPT_URL = "/response/school/request/getAccept/{accept}";
//    public static final String ACCEPT_COMAPNY_RESPONSE_SCHOOL_REQ_URL = "/response/school/request/accept/{id}";
//    public static final String REFUSE_COMAPNY_RESPONSE_SCHOOL_REQ_URL = "/response/school/request/refuse/{id}";
//    public static final String UPDATE_COMAPNY_RESPONSE_SCHOOL_REQ_URL = "/response/school/request/update";
//    public static final String DELETE_COMAPNY_RESPONSE_SCHOOL_REQ_URL = "/response/school/request/delete/{id}";
//    public static final String ADD_SCHOOL_REQUEST_OFFER_URL = "/school/request/offer/add";
//    public static final String GET_SCHOOL_REQUESTS_OFFER_URL = "/school/request/offer/getAll";
//    public static final String GET_SCHOOL_REQUEST_OFFER_URL = "/school/request/offer/get/{id}";
//    public static final String GET_SCHOOL_REQUEST_OFFER_SCHOOL_URL = "/school/request/offer/getSchool/{schoolId}";
//    public static final String GET_SCHOOL_REQUEST_OFFER_OFFER_URL = "/school/request/offer/getOffer/{offerId}";
//    public static final String GET_SCHOOL_REQUEST_OFFER_ACCEPT_URL = "/school/request/offer/getAccept/{accept}";
//    public static final String ACCEPT_SCHOOL_REQUEST_OFFER_URL = "/school/request/offer/accept/{id}";
//    public static final String REFUSE_SCHOOL_REQUEST_OFFER_URL = "/school/request/offer/refuse/{id}";
//    public static final String UPDATE_SCHOOL_REQUEST_OFFER_URL = "/school/request/offer/update";
//    public static final String DELETE_SCHOOL_REQUEST_OFFER_URL = "/school/request/offer/delete/{id}";
//    public static final String ADD_SCHOOL_SEE_OFFER_URL = "/offerSeen/add";
//    public static final String GET_SCHOOL_SEE_OFFERS_URL = "/offerSeen/getAll";
//    public static final String GET_SCHOOL_SEE_OFFER_URL = "/offerSeen/get/{id}";
//    public static final String GET_SCHOOL_SEE_OFFER_SCHOOL_URL = "/offerSeen/getSchool/{schoolId}";
//    public static final String GET_SCHOOL_SEE_OFFER_OFFER_URL = "/offerSeen/getOffer/{offerId}";
//    public static final String UPDATE_SCHOOL_SEE_OFFER_URL = "/offerSeen/update";
//    public static final String DELETE_SCHOOL_SEE_OFFER_URL = "/offerSeen/delete/{id}";
//    public static final String LOGIN_DETAILS_PAGINATION_URL = "/admin/login/details/getAll?page={page}&limit={limit}";
//    public static final String LOGIN_DETAILS_PAGINATIONS_URL = "/admin/login/details/getAll";
//    public static final String SCHOOL_SEE_OFFER_PAGINATION_URL = "/admin/get/schools/see/requests?page={page}&limit={limit}";
//    public static final String SCHOOL_SEE_OFFER_PAGINATIONS_URL = "/admin/get/schools/see/requests";
//    public static final String SCHOOL_REQUEST_OFFER_PAGINATION_URL = "/admin/get/school/request/offer?page={page}&limit={limit}";
//    public static final String SCHOOL_REQUEST_OFFER_PAGINATIONS_URL = "/admin/get/school/request/offer";
//    public static final String COMPANY_SEE_REQUEST_PAGINATION_URL = "/admin/get/company/see/request?page={page}&limit={limit}";
//    public static final String COMPANY_SEE_REQUEST_PAGINATIONS_URL = "/admin/get/company/see/request";
//    public static final String COMPANY_RESPONSE_REQUEST_PAGINATION_URL = "/admin/get/company/response/request?page={page}&limit={limit}";
//    public static final String COMPANY_RESPONSE_REQUEST_PAGINATIONS_URL = "/admin/get/company/response/request";
//    public static final String SCHOOL_SEE_TENDER_PAGINATION_URL = "/admin/get/school/see/tender?page={page}&limit={limit}";
//    public static final String SCHOOL_SEE_TENDER_PAGINATIONS_URL = "/admin/get/school/see/tender";
//    public static final String SCHOOL_REQUEST_TENDER_PAGINATION_URL = "/admin/get/school/request/tender?page={page}&limit={limit}";
//    public static final String SCHOOL_REQUEST_TENDER_PAGINATIONS_URL = "/admin/get/school/request/tender";
//    public static final String TOKEN = "/token";
//    public static final String HELLO = "/rest/hello";
//
//
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    //isLogged
//
//
//    public ApiSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().
//                antMatchers(HttpMethod.POST, ADD_USER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_ALL_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_USER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_USER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_USER_URL).permitAll().
//                antMatchers(HttpMethod.POST, LOGIN_USER_URL).permitAll().
//                antMatchers(HttpMethod.GET, LOGGED_USER_URL).permitAll().
//                antMatchers(HttpMethod.GET, LOGGED_1USER_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_PROFILES_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_SCHOOL_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_PROFILES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_SCHOOL_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_ALL_REQUESTS).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUESTS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_SCHOOL_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_COMPANY_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_OFFERS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_COMPANY_OFFERS_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_COMPANY_OFFERS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_ALL_CATEGORIES).permitAll().
//                antMatchers(HttpMethod.GET, GET_ALL_CATEGORY).permitAll().
//                antMatchers(HttpMethod.POST, ADD_LOGIN_DETAILS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_LOGIN_DETAILS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_LOGIN_DETAIL_URL).permitAll().
//                antMatchers(HttpMethod.POST, CHECK_USER_URL).permitAll().
//                antMatchers(HttpMethod.POST, GET_USER_ID).permitAll().
//                antMatchers(HttpMethod.POST, ADD_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_PASSWORD_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_ACTIVE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_LOGGED_USER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_INACTIVE_COMPANY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_NOT_LOGED_COMPANY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, ACTIVE_COMPANIES_URL).permitAll().
//                antMatchers(HttpMethod.PUT, ACTIVE_LOGGED_USER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, INACTIVE_LOGGED_USER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, INACTIVE_COMPANIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_ACTIVE_COMPANIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_LOGGED_COMPANIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, CONFIRM_MAIL_COMPANY_URL).permitAll().
//                antMatchers(HttpMethod.GET, EXIST_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.GET, SINGLE_COMPANY_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_OFFER_DATA).permitAll().
//                antMatchers(HttpMethod.GET, GET_LOGIN_LIST_DETAIL_URL).permitAll().
//                antMatchers(HttpMethod.GET, EXIST_SCHOOL_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_SCHOOL_PROFILE_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_IS_AVAILABLE_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_IS_CONFIRM_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_TITLE_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_EXPLAIN_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_CAT_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_PLACE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_SCHOOL_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_CATEGORIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_SCHOOL_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_COMPANY_SEE_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_SEE_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_SEE_REQUEST_BYR_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_SEE_REQUEST_BYC_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_SEE_REQUEST_BYS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMPANY_SEE_REQUEST_BYB_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_COMPANY_SEE_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DElETE_COMPANY_SEE_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_REQUEST_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_REQUEST_ENQUIRIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_REQUEST_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_REQUEST_ENQUIRY_ID_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_REQUEST_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_REQUEST_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_RESPONSE_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_RESPONSE_ENQUIRIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_RESPONSE_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_RESPONSE_ENQUIRY_ID_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_RESPONSE_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_RESPONSE_ENQUIRY_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_RECEIVE_PLACE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_RECEIVE_PLACES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_RECEIVE_PLACE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_RECEIVE_PLACE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_RECEIVE_PLACE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_TAKATAF_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATAF_CATEGORIES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATAF_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_TAKATAF_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_TAKATAF_CATEGORY_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_TAKATF_TENDER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDERS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_C_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_CAT_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_AVAL_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_CONFIRM_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_TAKATF_TENDER_TITLE_URL).permitAll().
//                antMatchers(HttpMethod.GET, FILTER_TAKATF_TENDER_EXPLAIN_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_TAKATF_TENDER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_TAKATF_TENDER_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_FIRST_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_FIRST_PRICES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_FIRST_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_FIRST_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_FIRST_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_SECOND_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SECOND_PRICES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SECOND_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_SECOND_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_SECOND_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_THIRD_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_THIRD_PRICES_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_THIRD_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_THIRD_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_THIRD_PRICE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_TAKATF_TENDER_SEE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDERS_SEE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_SEE_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDERS_SEE_SCHOOL_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDERS_SEE_TENDER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_TAKATF_TENDERS_SEE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_TAKATF_TENDERS_SEE_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_TAKATF_TENDER_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_REQUESTS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_REQUESTS_SCHOOL_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_REQUESTS_TENDER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_TAKATF_TENDER_REQUEST_APROVE_URL).permitAll().
//                antMatchers(HttpMethod.PUT, ACCEPT_TAKATF_TENDER_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.PUT, REFUSE_TAKATF_TENDER_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_TAKATF_TENDER_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_TAKATF_TENDER_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_COMAPNY_RESPONSE_SCHOOL_REQ_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMAPNY_RESPONSE_SCHOOL_REQS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMAPNY_RESPONSE_SCHOOL_REQ_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMAPNY_RESPONSE_SCHOOL_REQ_COMPANY_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMAPNY_RESPONSE_SCHOOL_REQ_REQUEST_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_COMAPNY_RESPONSE_SCHOOL_REQ_ACCEPT_URL).permitAll().
//                antMatchers(HttpMethod.PUT, ACCEPT_COMAPNY_RESPONSE_SCHOOL_REQ_URL).permitAll().
//                antMatchers(HttpMethod.PUT, REFUSE_COMAPNY_RESPONSE_SCHOOL_REQ_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_COMAPNY_RESPONSE_SCHOOL_REQ_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_COMAPNY_RESPONSE_SCHOOL_REQ_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_SCHOOL_REQUEST_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUESTS_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUEST_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUEST_OFFER_SCHOOL_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUEST_OFFER_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_REQUEST_OFFER_ACCEPT_URL).permitAll().
//                antMatchers(HttpMethod.PUT, ACCEPT_SCHOOL_REQUEST_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, REFUSE_SCHOOL_REQUEST_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_REQUEST_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_SCHOOL_REQUEST_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.POST, ADD_SCHOOL_SEE_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_SEE_OFFERS_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_SEE_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_SEE_OFFER_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, GET_SCHOOL_SEE_OFFER_SCHOOL_URL).permitAll().
//                antMatchers(HttpMethod.PUT, UPDATE_SCHOOL_SEE_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.PUT, DELETE_SCHOOL_SEE_OFFER_URL).permitAll().
//                antMatchers(HttpMethod.GET, LOGIN_DETAILS_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, LOGIN_DETAILS_PAGINATIONS_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_SEE_OFFER_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_SEE_OFFER_PAGINATIONS_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_REQUEST_OFFER_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_REQUEST_OFFER_PAGINATIONS_URL).permitAll().
//                antMatchers(HttpMethod.GET, COMPANY_SEE_REQUEST_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, COMPANY_SEE_REQUEST_PAGINATIONS_URL).permitAll().
//                antMatchers(HttpMethod.GET, COMPANY_RESPONSE_REQUEST_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, COMPANY_RESPONSE_REQUEST_PAGINATIONS_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_SEE_TENDER_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_SEE_TENDER_PAGINATIONS_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_REQUEST_TENDER_PAGINATION_URL).permitAll().
//                antMatchers(HttpMethod.GET, SCHOOL_REQUEST_TENDER_PAGINATIONS_URL).permitAll().
//
//                anyRequest().authenticated();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//}