package com.kingfood.backend.gateway.uploads;

import com.kingfood.backend.responseBuilder.ResponseEntityBuilder;
import com.kingfood.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UploadAPI {

    @Autowired
    private FileService fileService;

    /**
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/admin/upload/upload-files", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        List<String> listImg = fileService.listFiles(files);
        return ResponseEntityBuilder.getBuilder()
                .setDetails(listImg)
                .setMessage("Create a link successfully")
                .build();
    }
}
