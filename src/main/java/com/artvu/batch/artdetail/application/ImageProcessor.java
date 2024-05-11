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

        String returnPath = "";
        String filePath = "";
        try {
            URL url = new URL(urlPath);

            //확장자
            String extension = urlPath.substring(urlPath.lastIndexOf(".")+1);
            //파일명
            String fileName = urlPath.substring(urlPath.lastIndexOf("/")+1);
            //업로드 경로
            if (type == ImageType.INTRO) {
                filePath = "/app/back-batch/attech/images/intro/" + fileName;
                returnPath = "/attech/images/intro/" + fileName;
            } else {
                filePath = "/app/back-batch/attech/images/poster/" + fileName;
                returnPath = "/attech/images/poster/" + fileName;
            }
            BufferedImage image;
            try {
                image = ImageIO.read(url);
            } catch (ArrayIndexOutOfBoundsException ex) {
                image = null;
            } catch (IOException ex) {
                image = null;
            }
            File file = new File(filePath);
            if (image != null) {
                ImageIO.write(image, extension, file);
            }
            log.info("file download and upload complete");


        } catch (IOException e) {
            log.error("file upload error", e);
        }

        return returnPath;
    }

}
