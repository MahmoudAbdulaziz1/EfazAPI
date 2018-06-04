package com.taj.controller;

import com.taj.model.SchoolProfileModel;
import com.taj.model.SchoolRequestsModel;
import com.taj.repository.SchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@RequestMapping("/schoolRequest")
@RestController
@CrossOrigin
public class SchoolRequestController {

    @Autowired
    SchoolRequestRepo repo;

    @PostMapping("/addRequest")
    public int addSchoolRequest(@RequestBody SchoolRequestsModel model){
        File imgPath = new File("cv2.jpg");
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        //System.out.println("test \n"+data.getData().toString());
        return repo.addRequest(model.getRequest_id(), data.getData(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_category(), model.getRequest_display_date(), model.getRequest_expired_date(), model.getRequest_deliver_date());
    }

    @GetMapping("/getRequests")
    public List<SchoolRequestsModel> getSchoolRequests(){
        return repo.getRequests();
    }

    @GetMapping("/getRequest/{id}")
    public SchoolRequestsModel getSchoolRequest(@PathVariable int id){
        return repo.getRequest(id);
    }

    @PutMapping("/updateRequest/{id}")
    public int updateRequestModel(@PathVariable int id,@RequestBody SchoolRequestsModel model){
       return repo.updateRequest(id, model.getRequest_logo(), model.getRequest_title(),
               model.getRequest_explaination(), model.getRequest_category(), model.getRequest_display_date(),
               model.getRequest_expired_date(), model.getRequest_deliver_date());
    }

    @PutMapping("/deleteRequest/{id}")
    public int deleteSchoolRequest(@PathVariable int id){
        return repo.deleteSchoolRequest(id);
    }

}
