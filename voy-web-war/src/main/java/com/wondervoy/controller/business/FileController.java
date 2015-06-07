package com.wondervoy.controller.business;

import cn.wondervoy.facade.IFileFacade;
import cn.wondervoy.service.bean.FileUploadResult;
import com.wondervoy.controller.business.utils.FileDealUtil;
import com.wondervoy.controller.response.FileRespones;
import com.wondervoy.controller.response.ResponseBean;
import com.wondervoy.controller.utils.StateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * Created by ckzhang
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    IFileFacade fileFacade;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) {

        try {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String fileName = file.getOriginalFilename();
            fileName = FileDealUtil.fileRename() + fileName.substring(fileName.lastIndexOf("."));

            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);

            FileUploadResult result = fileFacade.saveFileInQiNiu(fileName, targetFile);
            targetFile.delete();

            ResponseBean response = new ResponseBean(StateEnum.OK.getCode(), "");
            FileRespones fileRespones = new FileRespones();
            fileRespones.setUrl(result.getUrl());
            response.setData(fileRespones);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            ResponseBean response = new ResponseBean(StateEnum.FILE_UPLOAD_ERROR.getCode(), "Upload failed!");
            return response;
        }

    }


}
