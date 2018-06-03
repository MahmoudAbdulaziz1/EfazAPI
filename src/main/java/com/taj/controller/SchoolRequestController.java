package com.taj.controller;

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

}
