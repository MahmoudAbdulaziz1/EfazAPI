package com.taj.controller;

import com.taj.model.LoginModel;
import com.taj.model.ProfileModel;
import com.taj.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.sql.Types;
import java.util.List;

/**
 * Created by MahmoudAhmed on 5/31/2018.
 */
@RequestMapping("/profile")
@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    ProfileRepo profileRepo;

    @PostMapping("/addProfile")
    public int AddUserProfile(@RequestBody ProfileModel model){
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

        return profileRepo.addProfile(model.getCompany_id(), model.getCompany_name(), data.getData(),
                model.getCompany_address(), model.getCompany_service_desc(), model.getCompany_link_youtube(),
                model.getCompany_website_url());


    }

    @GetMapping("/getProfiles")
    public List<ProfileModel> getProfiles(){
        return profileRepo.getProfiles();
    }
    @PutMapping("/updateProfile/{id}")
    public int updateProfile(@PathVariable int id, @RequestBody ProfileModel model){


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


        return profileRepo.updateProfile(id, model.getCompany_name(), data.getData(),
                model.getCompany_address(), model.getCompany_service_desc(), model.getCompany_link_youtube(),
                model.getCompany_website_url());
    }

    @GetMapping("/getProfile/{id}")
    public ProfileModel getProfile(@PathVariable int id){
        return profileRepo.getProfile(id);
    }

}
