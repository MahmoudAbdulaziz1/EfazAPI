package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.RecievesNewsMailsModel;
import com.taj.repository.RecievesNewsMailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 9/10/2018.
 */
@RequestMapping("/receive/news/mail")
@RestController
@CrossOrigin
public class RecieveNewsMailController {
    private static final String STATUS = "status";
    private static final String MESSAGE = "status";
    @Autowired
    RecievesNewsMailRepo recievesNewsMailRepo;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/")
    public ResponseEntity<ObjectNode> addMail(@Valid @RequestBody RecievesNewsMailsModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "Validation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        int response = recievesNewsMailRepo.addMail(model.getMail());
        if (response == 1) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 200);
            node.put("mail", model.getMail());
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "Not Added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<RecievesNewsMailsModel>> getMails() {
        List<RecievesNewsMailsModel> mails = recievesNewsMailRepo.getAllMails();
        if (mails == null) {
            return ResponseEntity.status(HttpStatus.OK).body(mails);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mails);


    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectNode> getMail(@PathVariable int id) {

        if (!recievesNewsMailRepo.checkIfExist(id)) {
            ObjectNode node = mapper.createObjectNode();
            node.put(MESSAGE, "This id Not Found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else {
            RecievesNewsMailsModel mails = recievesNewsMailRepo.getMailById(id);
            ObjectNode node = mapper.createObjectNode();
            node.put("mail_id", mails.getMailId());
            node.put("mail", mails.getMail());
            return ResponseEntity.status(HttpStatus.OK).body(node);
        }

    }

    @PutMapping("/")
    public ResponseEntity<ObjectNode> updateMailById(@Valid @RequestBody RecievesNewsMailsModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "validation error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        int response = recievesNewsMailRepo.updateMailById(model.getMailId(), model.getMail());
        if (response == 1) {
            ObjectNode node = mapper.createObjectNode();
            node.put("mail_id", model.getMailId());
            node.put("mail", model.getMail());
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "Not updated");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> deleteMailById(@PathVariable int id) {
        int response = recievesNewsMailRepo.deleteMailById(id);
        if (response == 1) {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 200);
            node.put(MESSAGE, "Deleted");
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else {
            ObjectNode node = mapper.createObjectNode();
            node.put(STATUS, 400);
            node.put(MESSAGE, "Not deleted");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }
}
