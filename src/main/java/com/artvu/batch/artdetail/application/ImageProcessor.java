package com.artvu.batch.artdetail.application;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class ImageProcessor {


    public static String downloader(String urlPath, ImageType type) {

        String filePath = "";
        try {
            URL url = new URL(urlPath);

            //확장자
            String extension = urlPath.substring(urlPath.lastIndexOf(".")+1);
            //파일명
            String fileName = urlPath.substring(urlPath.lastIndexOf("/")+1);
            //업로드 경로
            if (type == ImageType.INTRO) {
                filePath = "/public/attech/images/intro/" + fileName;
            } else {
                filePath = "/public/attech/images/poster/" + fileName;
            }
            BufferedImage image = ImageIO.read(url);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            ImageIO.write(image, extension, file);
            log.info("file download and upload complete");


        } catch (IOException e) {
            log.error("file upload error", e);
        }

        return filePath;
    }

}
