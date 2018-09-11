package com.taj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.*;
import com.taj.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by User on 9/5/2018.
 */
@RequestMapping("/evvaz")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DasboardsAPIControll {


    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";

    @Autowired
    AdminOrdersRepo adminRepo;
    @Autowired
    TakatafTenderNewRepo repo;
    @Autowired
    CategoryRepo catRepo;
    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    NewRegisterRepo newRegisterRepo;
    @Autowired
    TenderRequestRepo tenderRequestRepo;
    @Autowired
    TakatafTenderRequestRepo takatafTenderRequestRepo;

    @Autowired
    CollectiveTenderDetailsForCompanyRepo collectiveTenderDetailsForCompanyRepo;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    CollectiveTenderCompaniesRequestForCompanyRepo collectiveTenderCompaniesRequestForCompanyRepo;
    @Autowired
    AdminOrdersRepo adminOrdersRepo;
    @Autowired
    SchoolFollowCompanyRepo schoolFollowCompanyRepo;
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    SchoolRequestNewRepo schoolRequestNewRepo;
    @Autowired
    SchoolRequestRepo schoolRequestRepo;
    @Autowired
    SchoolRequestCategoryRepo schoolRequestCategoryRepo;
    @Autowired
    CompanyResponseSchoolRequestRepo companyResponseSchoolRequestRepo;
    @Autowired
    CustomCompanyOfferRepo customCompanyOfferRepo;
    @Autowired
    CompanyOfferRepo companyOfferRepo;
    @Autowired
    private SchoolProfileRepo schoolProfileRepo;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("admin/orders/history/")
    public List<AdminHistoryOrdersModel> getAllHistoryOrders() {
        return adminRepo.getAllHistoryOrders();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("takataf/tenders/")
    public ResponseEntity<ObjectNode> addTender(@RequestBody @Valid TakatafTenderNewModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = repo.addTender(model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_company_display_date(),
                model.getTender_company_expired_date(), model.getCats());

        if (res > 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date());
            objectNode.put("tender_expire_date", model.getTender_expire_date());
            objectNode.put("tender_company_display_date", model.getTender_company_display_date());
            objectNode.put("tender_company_expire_date", model.getTender_company_expired_date());


            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -100) {
            ObjectNode objectNode = mapper.createObjectNode();
            ArrayNode nodes = mapper.createArrayNode();
            for (int i = 0; i < model.getCats().size(); i++) {
                int categorys = repo.getCategoryId(model.getCats().get(i).getCategory_name());
                if (categorys > 0) {
                    nodes.add(model.getCats().get(i).getCategory_name());
                }
            }
            objectNode.set("cats", nodes);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("takataf/tenders/")
    public List<TakatafTenderPOJO> getTenders() {
        return repo.getTenders();

    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("takataf/tenders/{id}")
    public ResponseEntity<TakatafTenderPOJO> getTender(@PathVariable int id) {
        if (repo.isExist(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(repo.getTender(id));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repo.getTender(id));
        }


    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("takataf/tenders/admin")
    public List<TakatafMyTenderPageDTO> getAdminTenders() {
        return repo.getAdminTenders();

    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("takataf/tenders/")
    public ResponseEntity<ObjectNode> updateTender(@RequestBody TakatafTenderUpdatePOJO model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = repo.addTender(model.getTender_logo(), model.getTender_title(), model.getTender_explain(),
                model.getTender_display_date(), model.getTender_expire_date(), model.getTender_company_display_date(),
                model.getTender_company_expired_date(), model.getCats());

        if (res > 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("tender_id", model.getTender_id());
            objectNode.put("tender_logo", model.getTender_logo());
            objectNode.put("tender_title", model.getTender_title());
            objectNode.put("tender_explain", model.getTender_explain());
            objectNode.put("tender_display_date", model.getTender_display_date());
            objectNode.put("tender_expire_date", model.getTender_expire_date());
            objectNode.put("tender_company_display_date", model.getTender_company_display_date());
            objectNode.put("tender_company_expire_date", model.getTender_company_expired_date());


            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -100) {
            ObjectNode objectNode = mapper.createObjectNode();
            ArrayNode nodes = mapper.createArrayNode();
            for (int i = 0; i < model.getCats().size(); i++) {
                int categorys = repo.getCategoryId(model.getCats().get(i).getCategory_name());
                if (categorys > 0) {
                    nodes.add(model.getCats().get(i).getCategory_name());
                }
            }
            objectNode.set("cats", nodes);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("takataf/tenders/{id}")
    public int deleteTenderWithItsResponse(@PathVariable int id) {
        return repo.deleteTenderWithItsResponse(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("cat/getAll")
    public List<CategoryModel> getCategories() {
        return catRepo.getCategories();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive")
    public List<RegistrationModel> getInActiveCompaines() {
        return registrationRepo.getInActiveCompanies();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActives")
    public List<NewRegisterModel> getInActiveCompainesNew() {
        return newRegisterRepo.getInActiveCompanies();
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/confirm/{id}")
    public ObjectNode confirmEmail(@PathVariable int id) {
        int res = newRegisterRepo.confirmEmail(id);

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/get/{id}")
    public NewRegisterModel getUser(@PathVariable int id) {
        return newRegisterRepo.getUser(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("request/tender/{id}")
    public TenderRequestTenderModel getTenderRequestObject(@PathVariable int id) {
        Map<TenderRequestSchoolModel, List<TenderRequestCategoriesModel>> res = new HashMap<>();
        List<Map<String, Object>> list = tenderRequestRepo.getTenderRequestObject(id);
        List<TenderRequestSchoolModel> schoolsList = new ArrayList<>();
        Set<TenderRequestSchoolModel> schools = new HashSet<>();
        List<TenderRequestCategoriesModel> test2Models = new ArrayList<>();
        for (Map<String, Object> map : list) {
            TenderRequestSchoolModel model = new TenderRequestSchoolModel();
            TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();


            long schoolId = (long) map.get("school_id");
            String schoolName = (String) map.get("school_name");
            byte[] schoolLogo = (byte[]) map.get("school_logo_image");
            Timestamp date = (Timestamp) map.get("t_date");
            long t_date = 0;
            if (date == null) {
                t_date = 0;
            } else {
                t_date = date.getTime();
            }

            //System.out.println(map.get("t_date")+" +++ "+ date+" ++++++ "+ date.getTime()+ " ++++ "+ new Timestamp(0));

            long categoryId = (long) map.get("id");
            String categoryName = (String) map.get("category_name");
            long count = (long) map.get("count");


            test2Model.setId(categoryId);
            test2Model.setCategory_name(categoryName);
            test2Model.setCount(count);

            model.setSchool_id(schoolId);
            model.setSchool_name(schoolName);
            model.setSchool_logo_image(Base64.getEncoder().encodeToString(schoolLogo));
            model.setT_date(t_date);
            //String encodedString = Base64.getEncoder().encodeToString(schoolLogo);
            schools.add(model);

            test2Models.add(test2Model);
        }

        for (TenderRequestSchoolModel obj : schools) {
            System.out.println(obj.toString());
//            res.put(obj,new ArrayList<Test2Model>());
//            List<Test2Model>  test2ModelArrayList=new ArrayList<>();
            List<TenderRequestCategoriesModel> test2ModelArrayList = null;
            test2ModelArrayList = new ArrayList<>();

            int i = 0;
            for (Map<String, Object> map : list) {
//                obj.getCategories().add(test2ModelList);


                if (res.get(obj) == null) {
                    res.put(obj, new ArrayList<TenderRequestCategoriesModel>());

                }
//                if (res.containsKey(obj)) {
                if (map.get("school_id").equals(obj.getSchool_id())) {
                    TenderRequestCategoriesModel test2Model = new TenderRequestCategoriesModel();
                    long categoryId = (long) map.get("id");
                    String categoryName = (String) map.get("category_name");
                    long count = (long) map.get("count");


                    test2Model.setId(categoryId);
                    test2Model.setCategory_name(categoryName);
                    test2Model.setCount(count);

                    test2ModelArrayList.add(test2Model);
                    res.get(obj).add(i, test2Model);
                    i++;
                }

            }

            obj.setCategories(test2ModelArrayList);
            schoolsList.add(obj);
        }

        if (Long.parseLong(list.get(0).get("response_count").toString()) == 0) {
            TenderRequestTenderModel mainModel = new TenderRequestTenderModel(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(), Long.parseLong(list.get(0).get("response_count").toString()), null);


            return mainModel;
        } else {
            TenderRequestTenderModel mainModel = new TenderRequestTenderModel(Long.parseLong(list.get(0).get("tender_id").toString()),
                    (String) list.get(0).get("tender_title"),
                    (String) list.get(0).get("tender_explain"), ((Timestamp) (list.get(0).get("tender_display_date"))).getTime(),
                    ((Timestamp) list.get(0).get("tender_expire_date")).getTime(), Long.parseLong(list.get(0).get("response_count").toString()), schoolsList);


            return mainModel;
        }


    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/register/Archive/{id}")
    public ResponseEntity<ObjectNode> archiveCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.archiveCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("register/unArchive/{id}")
    public ResponseEntity<ObjectNode> unArchiveCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.unArchiveCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("register/consider/{id}")
    public ResponseEntity<ObjectNode> considrateCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.considrateCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("register/unconsider/{id}")
    public ResponseEntity<ObjectNode> unCosidrateCompanyRequest(@PathVariable int id) {
        int respons = newRegisterRepo.unCosidrateCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive/archived")
    public List<NewRegisterModel> getInActiveCompaniesArchived() {
        return newRegisterRepo.getInActiveCompaniesArchived();
    }
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive/consider")
    public List<NewRegisterModel> getInActiveCompaniesConsiderate() {
        return newRegisterRepo.getInActiveCompaniesConsiderate();
    }
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("register/getInActive/both")
    public List<NewRegisterModel> getInActiveCompaniesBoth() {
        return newRegisterRepo.getInActiveCompaniesBoth();
    }


    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/tender/request/{id}")
    public GetSingCollectiveTenderById getAllRequestsWithNameById(@PathVariable int id) {
        List<TakatafSinfleSchoolRequestDTO> tenderList = takatafTenderRequestRepo.getAllRequestsWithNameByTender(id);

        GetSingleCollectiveByIdPartOneDTO data = new GetSingleCollectiveByIdPartOneDTO(tenderList.get(0).getTender_id(), tenderList.get(0).getTender_title(),
                tenderList.get(0).getTender_explain(), tenderList.get(0).getTender_display_date(),
                tenderList.get(0).getTender_expire_date(), tenderList.get(0).getResponse_count());
        List<GetSingleCollectiveByIdPartTwoDTO> schools = new ArrayList<>();
        List<TenderCollectiveByIdPart3DTO> categories = new ArrayList<>();
        if (tenderList.get(0).getResponse_count() > 0) {


            TenderCollectiveByIdPart3DTO category = new TenderCollectiveByIdPart3DTO(tenderList.get(0).getId(),
                    tenderList.get(0).getCategory_name(), tenderList.get(0).getCount());
            categories.add(category);
            int i = 1;
            while (i < tenderList.size()) {
                if (tenderList.get(i).getTender_id() == tenderList.get(i - 1).getTender_id()) {
                    TenderCollectiveByIdPart3DTO categorys = new TenderCollectiveByIdPart3DTO(tenderList.get(i).getId(),
                            tenderList.get(i).getCategory_name(), tenderList.get(i).getCount());
                } else {
                    GetSingleCollectiveByIdPartTwoDTO school = new GetSingleCollectiveByIdPartTwoDTO
                            (tenderList.get(i).getSchool_name(), tenderList.get(i).getSchool_logo_image(), categories);
                }
                i++;
            }


        }


        GetSingCollectiveTenderById tener = new GetSingCollectiveTenderById(data, schools);
        return tener;
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('school')")
    @GetMapping("/tender/request/get/{id}")
    public GetTakatafTenderForScoolRequestDTO getRequestWithNameById(@PathVariable int id) {
        List<TakatafSingleSchoolRequestByIDDTO> tenderList = takatafTenderRequestRepo.getAllRequestsWithNameByIdzs(id);

        GetSingleCollectiveByIdPartOneDTO data = new GetSingleCollectiveByIdPartOneDTO(tenderList.get(0).getTender_id(), tenderList.get(0).getTender_title(),
                tenderList.get(0).getTender_explain(), tenderList.get(0).getTender_display_date(),
                tenderList.get(0).getTender_expire_date(), tenderList.get(0).getResponse_count());
        List<GetGetTakatafTenderSchollPrt2DTO> categories = new ArrayList<>();

        for (TakatafSingleSchoolRequestByIDDTO obj : tenderList) {
            GetGetTakatafTenderSchollPrt2DTO category = new GetGetTakatafTenderSchollPrt2DTO(obj.getId(), obj.getCategory_name());
            categories.add(category);
        }
        GetTakatafTenderForScoolRequestDTO tener = new GetTakatafTenderForScoolRequestDTO(data, categories);
        return tener;
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/tender/details/company/{id}")
    public List<CollectiveTenderDetailsForCompanyModel> getTenderDetails(@PathVariable int id) {
        return collectiveTenderDetailsForCompanyRepo.getTenderDetails(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/tender/companies/{id}")
    public List<CollectiveTenderCompaniesRequestForCompanyModel> getTenderCompanies(@PathVariable int id) {
        return collectiveTenderCompaniesRequestForCompanyRepo.getTenderCompanies(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/tender/companies/{company_id}/{request_id}")
    public int approveRequest(@PathVariable int company_id, @PathVariable int request_id) {
        return collectiveTenderCompaniesRequestForCompanyRepo.approveRequest(company_id, request_id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/orders/")
    public List<AdminOrdersModel> getAllOrders() {
        return adminOrdersRepo.getAllOrders();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/orders/{id}")
    public AdminSingleOrderModel getOrder(@PathVariable int id) {
        return adminOrdersRepo.getOrder(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/orders/history/{id}")
    public AdminSingleOrderHistoryModel getHistoryOrder(@PathVariable int id) {
        return adminOrdersRepo.getHistoryOrder(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/admin/orders/ship")
    public int addShipping(@RequestBody ShippingDTO dto) {
        return adminOrdersRepo.addShipping(dto.getShip(), dto.getShip_company_offer_id());
    }

    @PreAuthorize("hasAuthority('school')  or hasAuthority('company')")
    @GetMapping("/follow/school/{id}")
    public List<FollowSchoolProfilesDto> getSchoolsWithFollow(@PathVariable int id) {
        return schoolFollowCompanyRepo.getSchoolsWithFollow(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/follow/company/{id}")
    public List<getCompaniesWithFollowDTo> getCompaniesWithFollow(@PathVariable int id) {
        return schoolFollowCompanyRepo.getCompaniesWithFollow(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/follow/school/followers/{schoolId}")
    public ResponseEntity<List<CompanyFollowSch0oolDto>> getSchoolAllFollowers(@PathVariable int schoolId) {

        if (schoolFollowCompanyRepo.isExistFollwer(schoolId)) {
            List<CompanyFollowSch0oolDto> followed = schoolFollowCompanyRepo.getSchoolAllFollowersNew(schoolId);
            return ResponseEntity.status(HttpStatus.OK).body(followed);
        } else {
            List<CompanyFollowSch0oolDto> followed = schoolFollowCompanyRepo.getSchoolAllFollowersNew(schoolId);
            return ResponseEntity.status(HttpStatus.OK).body(followed);
        }
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('company')")
    @GetMapping("/orders/{id}")
    public ResponseEntity<List<CompanyBackOrderModel>> getOrdersForCompany(@PathVariable int id) {
        return ordersRepo.getOrdersForCompany(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @PostMapping("/school/profile/addProfile")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ResponseEntity<ObjectNode> AddUserProfile(@Valid @RequestBody SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (schoolProfileRepo.isExistInLogin(model.getSchool_id(), "school")) {

            if (!repo.isExist(model.getSchool_id())) {
                int res = schoolProfileRepo.addSchoolProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                        model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                        model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image(), model.getSchool_phone_number());
                if (res == 1) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("school_name", model.getSchool_name());
                    objectNode.put("school_logo_image", model.getSchool_logo_image());
                    objectNode.put("school_address", model.getSchool_address());
                    objectNode.put("school_service_desc", model.getSchool_service_desc());
                    objectNode.put("school_link_youtube", model.getSchool_link_youtube());
                    objectNode.put("school_website_url", model.getSchool_website_url());
                    objectNode.put("school_lng", model.getSchool_lng());
                    objectNode.put("school_lat", model.getSchool_lat());
                    objectNode.put("school_cover_image", model.getSchool_cover_image());
                    objectNode.put("school_phone_number", model.getSchool_phone_number());
                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "not success");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "id Already has profile");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/school/profile/get/{id}")
    public CustomSchoolProfileModel getProfileForAdmin(@PathVariable int id) {
        return schoolProfileRepo.getSchoolProfileForAdmin(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/school/profile/update")
    public ResponseEntity<ObjectNode> updateProfileForAdmin(@Valid @RequestBody SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = schoolProfileRepo.updateProfileForAdmin(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_cover_image(), model.getSchool_phone_number());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            objectNode.put("school_phone_number", model.getSchool_phone_number());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/tenders/school/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestNewDto> getSchoolRequestsBySchool(@PathVariable int id) {
        return schoolRequestNewRepo.getRequestsBySchoolID(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/tenders/")
    public List<SchoolRequestNewDto> getSchoolSingleRequest() {
        return schoolRequestNewRepo.getRequestsAll();
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/school/tenders/")
    public JsonNode updateRequestModel(@Valid @RequestBody SchoolRequestsDTO model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return objectNode;
        }
        int res = schoolRequestNewRepo.updateRequest(model.getRequest_id(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return objectNode;
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @PostMapping("/school/tenders/")
    public ResponseEntity<JsonNode> addSchoolRequest(@RequestBody @Valid SchoolRequestsDTO model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        int res = schoolRequestNewRepo.addRequest(model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getSchool_id(), model.getRequest_category_id());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();

            objectNode.put("request_title", model.getRequest_title());
            objectNode.put("request_explaination", model.getRequest_explaination());
            objectNode.put("request_display_date", model.getRequest_display_date());
            objectNode.put("request_expired_date", model.getRequest_expired_date());
            objectNode.put("school_id", model.getSchool_id());
            objectNode.put("request_category_id", model.getRequest_category_id());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/tenders/request/school/{id}")
    public GetCollectiveTenders getRequestOfSchoolByID(@PathVariable int id) {
        List<getSchoolCustomRequestById> obj = schoolRequestNewRepo.getRequestOfSchoolByID(id);
        GetCollectiveTenderPartOneDTO tender = new GetCollectiveTenderPartOneDTO(obj.get(0).getRequest_id(), obj.get(0).getRequest_title(),
                obj.get(0).getRequest_explaination(), obj.get(0).getRequest_display_date(), obj.get(0).getRequest_expired_date(),
                obj.get(0).getSchool_id(), obj.get(0).getResponse_count());


        List<GetCollectiveTenderPartYTwoDTO> companies = new ArrayList<>();
        if (obj.get(0).getResponse_count() > 0) {
            for (getSchoolCustomRequestById one : obj) {
                //if( one.getRequest_category_name().equals(null) )
                GetCollectiveTenderPartYTwoDTO part2 = new GetCollectiveTenderPartYTwoDTO(one.getRequest_category_name() + "", one.getCompany_name() + "",
                        one.getCompany_logo_image(), one.getCategory_name() + "", one.getResponsed_cost(), one.getResponse_date(),
                        one.getResponse_id(), one.getResponsed_company_id());
                companies.add(part2);
            }
        }

        if (obj.get(0).getResponse_count() == 0) {
            GetCollectiveTenders tenders = new GetCollectiveTenders(tender, null);
            return tenders;
        } else {
            GetCollectiveTenders tenders = new GetCollectiveTenders(tender, companies);
            return tenders;
        }


    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/requests/get/history/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestHistoryDto> getSchoolHistoryRequestBySchoolId(@PathVariable int id) {
        return schoolRequestRepo.getHistoryRequestsBySchoolId(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/requests/get/order/{id}")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestHistoryDto> getSchoolOrderRequestBySchoolId(@PathVariable int id) {
        return schoolRequestRepo.getOrderRequestsBySchoolId(id);
    }

    @PreAuthorize("hasAuthority('school')")
    @GetMapping("/school/category/getAll")
//    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestCategoryModel> getSchoolCategories() {
        return schoolRequestCategoryRepo.getSchoolRequestCategories();
    }

    @PreAuthorize("hasAuthority('school')")
    @PostMapping("/tender/request/add")
    public ResponseEntity<ObjectNode> addTenderRequest(@Valid @RequestBody TakatafTenderRequestModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = takatafTenderRequestRepo.add$Request(model.getRequest_school_id(), model.getRequest_tender_id(),
                model.getIs_aproved(), model.getDate(), model.getCategory());
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("request_id", model.getRequest_id());
            objectNode.put("request_school_id", model.getRequest_school_id());
            objectNode.put("request_tender_id", model.getRequest_tender_id());
            objectNode.put("is_aproved", model.getIs_aproved());
            objectNode.put("date", model.getDate());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "you already request this");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

    @PreAuthorize("hasAuthority('school')")
    @PutMapping("/response/school/request/accept/{id}")
    public JsonNode acceptCompanyResponseSchoolRequest(@PathVariable int id) {

        int res = companyResponseSchoolRequestRepo.acceptResponseSchoolRequest(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "accepted");
            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "failed");
            return objectNode;

        }
    }

    @PreAuthorize("hasAuthority('school')")
    @DeleteMapping("/response/school/request/delete/{id}")
    public ObjectNode deleteCompanyResponseSchoolRequest(@PathVariable int id) {
        int res = companyResponseSchoolRequestRepo.deleteResponseSchoolRequest(id);

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @PreAuthorize("hasAuthority('company')")
    @PostMapping("/company/offer/")
    public ResponseEntity<ObjectNode> addCompanyOffers(@Valid @RequestBody CustomCompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            int res = customCompanyOfferRepo.addOfferEdeited(model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(), model.getOffer_title(), model.getOffer_explaination(),
                    model.getOffer_cost(), model.getOffer_display_date(), model.getOffer_expired_date(), model.getOffer_deliver_date(),
                    model.getCompany_id(), model.getOffer_count());
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                //objectNode.put("offer_images_id", model.getOffer_images_id());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date().toString());
                objectNode.put("offer_expired_date", model.getOffer_expired_date().toString());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date().toString());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }

    }

    @PreAuthorize("hasAuthority('company')")
    @GetMapping("/company/offer/{id}")
    public ResponseEntity<getCustomeOffer> getCompanyOffer(@PathVariable int id) {
        if (customCompanyOfferRepo.checkIfExist(id)) {
            CustomCompanyModelWithView model = customCompanyOfferRepo.getCompanyOffer(id);

            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeOffer("200", model));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeOffer("400", null));
        }

    }

    @PreAuthorize("hasAuthority('company')")
    @GetMapping("/company/offer/{id}/company")
    public ResponseEntity<getCustomeCompanyOffer> getSingleCompanyOffer(@PathVariable int id) {
        List<CustomCompanyOfferModel> offers = customCompanyOfferRepo.getCompanyOffers(id);
        if (offers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new getCustomeCompanyOffer("200", offers));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new getCustomeCompanyOffer("400", offers));
        }

    }

    @PreAuthorize("hasAuthority('company')")
    @PutMapping("/company/offer/")
    public ResponseEntity<JsonNode> updateCompanyOfferWithImage(@Valid @RequestBody CustomCompanyOfferModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (customCompanyOfferRepo.checkIfExist(model.getOffer_id())) {
            int res = customCompanyOfferRepo.updateCompanyOfferWithImages(model.getOffer_id(), model.getImage_one(), model.getImage_two(), model.getImage_third(), model.getImage_four(),
                    model.getOffer_title(), model.getOffer_explaination(), model.getOffer_cost(), model.getOffer_display_date(),
                    model.getOffer_expired_date(), model.getOffer_deliver_date(), model.getCompany_id(), model.getOffer_count());

            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 200);
                objectNode.put("offer_id", model.getOffer_id());
                objectNode.put("image_one", model.getImage_one());
                objectNode.put("image_two", model.getImage_two());
                objectNode.put("image_three", model.getImage_third());
                objectNode.put("image_four", model.getImage_four());
                objectNode.put("offer_title", model.getOffer_title());
                objectNode.put("offer_explaination", model.getOffer_explaination());
                objectNode.put("offer_cost", model.getOffer_cost());
                objectNode.put("offer_display_date", model.getOffer_display_date());
                objectNode.put("offer_expired_date", model.getOffer_expired_date());
                objectNode.put("offer_deliver_date", model.getOffer_deliver_date());
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("offer_count", model.getOffer_count());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @PreAuthorize("hasAuthority('company')")
    @DeleteMapping("/companyOffer/delete/{id}")
    public ResponseEntity<ObjectNode> deleteCompanyOffer(@PathVariable int id) {

        if (companyOfferRepo.checkIfExist(id)) {
            int res = companyOfferRepo.deleteCompanyOffer(id);
            if (res == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "success");

                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }


    @PreAuthorize("hasAuthority('company')")
    @PostMapping("/follow/add")
    public ResponseEntity<ObjectNode> addFollower(@RequestBody @Valid SchoolFollowCompany model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (repo.isExist(model.getFollow_id())) {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id exist");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            if (schoolFollowCompanyRepo.isExistFollwing(model.getOrganization_id()) && schoolFollowCompanyRepo.isExistFollwer(model.getFollower_id())) {
                int res = schoolFollowCompanyRepo.addFollower(model.getOrganization_id(), model.getFollower_id());
                if (res == 1) {

                    int id = schoolFollowCompanyRepo.getId(model.getOrganization_id(), model.getFollower_id());

                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 200);
                    objectNode.put("follow_id", id);
                    objectNode.put("Organization_id", model.getOrganization_id());
                    objectNode.put("Follower_id", model.getFollower_id());

                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else if (res == 0) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "not success");

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "is already follow it");

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }

            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "organization not exist");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }

        }


    }


}
