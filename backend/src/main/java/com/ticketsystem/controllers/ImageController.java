package com.ticketsystem.controllers;

import com.ticketsystem.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {
    private final ImageUtil imageUtil;

    @GetMapping
    public void getImage(@RequestParam("img") String image, HttpServletResponse response){
        InputStream inputStream = imageUtil.getImage(image);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try {
            StreamUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
