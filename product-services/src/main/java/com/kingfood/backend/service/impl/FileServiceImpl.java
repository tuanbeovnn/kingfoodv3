package com.kingfood.backend.service.impl;


import com.kingfood.backend.FileUtils.FileUtils;
import com.kingfood.backend.exceptionsv2.AppException;
import com.kingfood.backend.exceptionsv2.ErrorCode;
import com.kingfood.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileUtils fileUtils;

    private static final String PATHIMG = "/lms/images/";
    private static final String PATHPDF = "/lms/pdf/";
    private static final String PATHVIDEO = "/lms/video/";

    @Override
    public List<String> listFiles(MultipartFile[] files) {
        List<String> listFiles = new ArrayList<>();
        String fileName = "";
        try {
            if (files != null) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        String nameFileServer = fileUtils.generateFileName(file.getOriginalFilename());
                        if (nameFileServer.matches(".+(pdf|txt|csv)")) {
                            fileName = fileUtils.save(file, PATHPDF, nameFileServer);
                        } else if (nameFileServer.matches(".+(jpg|png|jpeg|PNG|JPG|JPEG)")) {
                            fileName = fileUtils.save(file, PATHIMG, nameFileServer);
                        } else if (nameFileServer.matches(".+(mp4|MP4)")) {
                            fileName = fileUtils.save(file, PATHVIDEO, nameFileServer);
                        } else {
                            throw new AppException(ErrorCode.UNSUPPORT_FILE_EXTENSION);
                        }
                        listFiles.add(fileUtils.genFilePath(fileName));
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.getMessage();
        }
        return listFiles;
    }

    @Override
    public String singleFile(MultipartFile file) {
        String img = null;
        String fileName = "";
        try {
            if (!file.isEmpty()) {
                String nameFileServer = fileUtils.generateFileName(file.getOriginalFilename());
                if (nameFileServer.matches(".+(jpg|png|jpeg|PNG|JPG|JPEG)")) {
                    fileName = fileUtils.save(file, PATHIMG, nameFileServer);
                    img = fileUtils.genFilePath(fileName);
                } else {
                    throw new AppException(ErrorCode.UNSUPPORT_FILE_EXTENSION);
                }
            }
        } catch (URISyntaxException e) {
            e.getMessage();
        }

        return img;
    }

}
