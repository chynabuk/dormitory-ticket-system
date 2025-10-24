package com.ticketsystem.utils;

import com.ticketsystem.exceptions.impl.ExpectationFailedException;
import com.ticketsystem.exceptions.impl.ResourceDuplicateException;
import com.ticketsystem.exceptions.impl.ResourceNotFoundException;
import com.ticketsystem.models.entities.BaseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ImageUtil {
    private final static String uploadPath = "/ticket-images";
    public String saveImage(MultipartFile image, BaseEntity entity){
        File imagesFolder = new File(uploadPath);
        if (!imagesFolder.exists()) {
            imagesFolder.mkdir();
        }
        String entityPath = uploadPath + "/" + entity.getId();
        File entityFolder = new File(entityPath);
        if (!entityFolder.exists()) {
            entityFolder.mkdir();
        }

        if (!image.getOriginalFilename().equals("")){
            File imageFile = new File(entityPath + "/" + image.getOriginalFilename());
            if (imageFile.exists()){
                throw new ResourceDuplicateException("Image was not added because image is existed");
            }
            try {
                image.transferTo(imageFile);
                return image.getOriginalFilename();
            } catch (IOException e) {
                throw new RuntimeException("Image was not created");
            }
        }
        throw new ExpectationFailedException("Image was not created");
    }

    public InputStream getImage(String entityWithPhoto){
        try{
            return new FileInputStream(uploadPath + "/" + entityWithPhoto);
        }
        catch (FileNotFoundException e){
            throw new ResourceNotFoundException("Image is not found");
        }
    }
}
