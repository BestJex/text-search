package com.snowwave.textsearch.service;

import com.snowwave.textsearch.util.TextUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by zhangfuqiang on 2018/1/26.
 */
@Service
public class FileService {


    /**
     * 保存文件，成功就返回文件本地地址
     * @param file
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile file) throws IOException {

        //判断文件是否合法，通过文件后缀
        int dotPos = file.getOriginalFilename().lastIndexOf(".");
        if (dotPos < 0) {
            return null;
        }
        String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();

        //判断后缀
        if (!TextUtil.isFileAllowed(fileExt)) {
            return null;
        }


        String fileName = file.getOriginalFilename();
        //拷贝到本地
        Files.copy(file.getInputStream(), new File(TextUtil.IMAGE_DIR + fileName).toPath(),
                StandardCopyOption.REPLACE_EXISTING);

        return TextUtil.TOUTIAO_DOMAIN + "file?name=" + fileName;
    }


}