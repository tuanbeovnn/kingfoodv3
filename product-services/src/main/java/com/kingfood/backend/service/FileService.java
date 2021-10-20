package com.kingfood.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<String> listFiles (MultipartFile[] files);
    String singleFile(MultipartFile file);


}
