package com.taj.controller;

import com.taj.model.CompanyProfileModel;
import com.taj.model.SchoolProfileModel;
import com.taj.repository.SchoolProfileRepo;
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
@RequestMapping("/schoolProfile")
@RestController
@CrossOrigin
public class SchoolProfileController {


    @Autowired
    private SchoolProfileRepo repo;

    @PostMapping("/addProfile")
    public int AddUserProfile(@RequestBody SchoolProfileModel model){
        File imgPath = new File("cv.jpg");
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

        return repo.addSchoolProfile(model.getSchool_id(), model.getSchool_name(), data.getData(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url());


    }

    @GetMapping("/getProfiles")
    public List<SchoolProfileModel> getProfiles(){
        return repo.getSchoolSProfiles();
    }


    @GetMapping("/getProfile/{id}")
    public SchoolProfileModel getProfile(@PathVariable int id){
        return repo.getSchoolProfile(id);
    }


    @PutMapping("/updateProfile/{id}")
    public int updateProfile(@PathVariable int id, @RequestBody SchoolProfileModel model){


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


        return repo.updateProfile(id, model.getSchool_name(), data.getData(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url());
    }



}
