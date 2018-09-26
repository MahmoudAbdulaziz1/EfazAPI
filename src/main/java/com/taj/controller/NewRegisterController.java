package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.NewModelDto;
import com.taj.model.NewRegisterModel;
import com.taj.model.UserType;
import com.taj.model.new_register.CheckOrgNameNotFoundDto;
import com.taj.repository.NewRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 9/10/2018.
 */

@RequestMapping("/usr/register")
@RestController
@CrossOrigin
public class NewRegisterController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";

    @Autowired
    NewRegisterRepo registrationRepo;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/")
    public ResponseEntity<ObjectNode> addUserRegistration(@RequestBody @Valid NewRegisterModel registrationModel, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else if (registrationRepo.checkIfEmailExist(registrationModel.getRegisterationEmail(), registrationModel.getRegistrationRole())) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "Email is exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        if (registrationModel.getRegistrationRole().trim().compareTo(UserType.admin.toString()) == 0 ||
                registrationModel.getRegistrationRole().trim().compareTo(UserType.school.toString()) == 0 ||
                registrationModel.getRegistrationRole().trim().compareTo(UserType.company.toString()) == 0) {

            String encodedPassword = bCryptPasswordEncoder.encode(registrationModel.getRegisterationPassword());
            int model = registrationRepo.addUser(registrationModel.getRegisterationEmail(), encodedPassword,
                    registrationModel.getRegisterationUsername(), registrationModel.getRegisterationPhoneNumber(),
                    registrationModel.getRegistrationOrganizationName(), registrationModel.getRegistrationAddressDesc(),
                    registrationModel.getRegistrationWebsiteUrl(), registrationModel.getRegistrationRole(),
                    new Timestamp(System.currentTimeMillis()).getTime(), registrationModel.getCity(), registrationModel.getArea(),
                    registrationModel.getLng(), registrationModel.getLat());
            if (model == 1) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, 201);
                objectNode.put("registeration_email", registrationModel.getRegisterationEmail());
                objectNode.put("registeration_password", registrationModel.getRegisterationPassword());
                objectNode.put("registeration_username", registrationModel.getRegisterationUsername());
                objectNode.put("registeration_phone_number", registrationModel.getRegisterationPhoneNumber());
                objectNode.put("registration_organization_name", registrationModel.getRegistrationOrganizationName());
                objectNode.put("registration_address_desc", registrationModel.getRegistrationAddressDesc());
                objectNode.put("registration_website_url", registrationModel.getRegistrationWebsiteUrl());
                objectNode.put("registration_isActive", registrationModel.getRegistrationIsActive());
                objectNode.put("registration_role", registrationModel.getRegistrationRole());
                objectNode.put("registration_date", new Timestamp(System.currentTimeMillis()).getTime());
                objectNode.put("city", registrationModel.getCity());
                objectNode.put("Area", registrationModel.getArea());
                objectNode.put("lng", registrationModel.getLng());
                objectNode.put("lat", registrationModel.getLat());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            } else if (model == -100) {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, 400);
                objectNode.put(MESSAGE, "This Name Is Already Exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put(STATUS, 400);
                objectNode.put(MESSAGE, "sign up error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        } else {

            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, 400);
            objectNode.put(MESSAGE, "not valid role");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }


    }

    @PostMapping("/org")
    public boolean IfOrgNameExist(@RequestBody CheckOrgNameNotFoundDto dto) {
        return registrationRepo.IfOrgNameExist(dto.getRegistration_organization_name());
    }

    @GetMapping("/getInActive")
    public List<NewRegisterModel> getInActiveCompanies() {
        return registrationRepo.getInActiveCompanies();
    }

    @PutMapping("Archive/{id}")
    public ResponseEntity<ObjectNode> archiveCompanyRequest(@PathVariable int id) {
        int respons = registrationRepo.archiveCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PutMapping("unArchive/{id}")
    public ResponseEntity<ObjectNode> unArchiveCompanyRequest(@PathVariable int id) {
        int respons = registrationRepo.unArchiveCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }


    @PutMapping("consider/{id}")
    public ResponseEntity<ObjectNode> considrateCompanyRequest(@PathVariable int id) {
        int respons = registrationRepo.considrateCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }

    @PutMapping("unconsider/{id}")
    public ResponseEntity<ObjectNode> unCosidrateCompanyRequest(@PathVariable int id) {
        int respons = registrationRepo.unCosidrateCompanyRequest(id);
        if (respons == 1) {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put(MESSAGE, SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        }

        ObjectNode nodes = mapper.createObjectNode();
        nodes.put(MESSAGE, FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
    }


    @GetMapping("/getInActive/archived")
    public List<NewRegisterModel> getInActiveCompaniesArchived() {
        return registrationRepo.getInActiveCompaniesArchived();
    }

    @GetMapping("/getInActive/consider")
    public List<NewRegisterModel> getInActiveCompaniesConsiderate() {
        return registrationRepo.getInActiveCompaniesConsiderate();
    }

    @GetMapping("/getInActive/both")
    public List<NewRegisterModel> getInActiveCompaniesBoth() {
        return registrationRepo.getInActiveCompaniesBoth();
    }

    @GetMapping("/confirm/{id}")
    public ObjectNode confirmEmail(@PathVariable int id) {
        int res = registrationRepo.confirmEmail(id);

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, SUCCESS);

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, FAILED);

            return objectNode;
        }
    }

    @GetMapping("/")
    public List<NewModelDto> getUsers() {
        return registrationRepo.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectNode> getUser(@PathVariable int id) {

        if (registrationRepo.IfEmailExist(id)) {
            NewRegisterModel model = registrationRepo.getUser(id);
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put("registration_id", model.getRegistrationId());
            nodes.put("registeration_email", model.getRegisterationEmail());
            //nodes.put("registeration_password": "$2a$10$ZhaDnjosb19LkCB5qHmpMO4B0DnZX5tpjjaOOULIpOgxKpam4nsH.");
            nodes.put("registeration_username", model.getRegisterationUsername());
            nodes.put("registeration_phone_number", model.getRegisterationPhoneNumber());
            nodes.put("registration_organization_name", model.getRegistrationOrganizationName());
            nodes.put("registration_address_desc", model.getRegistrationAddressDesc());
            nodes.put("registration_website_url", model.getRegistrationWebsiteUrl());
            nodes.put("registration_is_active", model.getRegistrationIsActive());
            nodes.put("registration_role", model.getRegistrationRole());
            nodes.put("registeration_date", model.getRegisterationDate());
            nodes.put("city", model.getCity());
            nodes.put("area", model.getArea());
            nodes.put("lng", model.getLng());
            nodes.put("lat", model.getLat());
            return ResponseEntity.status(HttpStatus.OK).body(nodes);
        } else {
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put("message", "No data for this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nodes);
        }


    }
}
