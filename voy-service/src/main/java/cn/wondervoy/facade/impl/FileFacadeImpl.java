package cn.wondervoy.facade.impl;

import cn.wondervoy.facade.IFileFacade;
import cn.wondervoy.service.bean.FileUploadResult;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.URLUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author ckzhang
 *
 */
@Service("fileUploadService")
public class FileFacadeImpl implements IFileFacade {

    private static final String DOMAIN = "7xj961.com1.z0.glb.clouddn.com";
    private static final String BUCKET_NAME = "funvoy";

    static {
        /** 个人账号 */
        // Config.ACCESS_KEY = "RlAcT6Sr2AMj9-hR3F2zMcHRK-UWF7O109TQCQIt";
        // Config.SECRET_KEY = "VAsTtWrEoZXLlUR_KROukuf8-VjdAX9q9GzgDaQK";
        /** 公司账号 */
        Config.ACCESS_KEY = "JQJJ5og0qpf8ETs1h6quFxfYtUg0v2zIFsn97NVY";
        Config.SECRET_KEY = "lkAUByQ3sDeZ84HMM5Xg_I4uec5Yd4LjJWvZ0MLd";
    }

    /**
     * @throws Exception
     * */
    @Override
    public String getFileByName(String fileName) throws Exception {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        String baseUrl = URLUtils.makeBaseUrl(DOMAIN, fileName);
        GetPolicy getPolicy = new GetPolicy();
        return getPolicy.makeRequest(baseUrl, mac);
    }

    /**
     */
    @Override
    public FileUploadResult saveFileInQiNiu(String fileName, String localFile)
            throws Exception {

        FileUploadResult result = new FileUploadResult();

        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.putFile(uptoken, fileName, localFile, extra);
        if (ret.statusCode == 200) {
            // 数据库最好将文件名和文件的uri都存储起来
            String uri = getFileByName(fileName);
            result.setUrl(uri.substring(0, uri.indexOf("?")));
        }

        return result;
    }

    /**
     *  */
    @Override
    public FileUploadResult saveFileInQiNiu(String fileName, File file)
            throws Exception {
        FileUploadResult result = saveFileInQiNiu(fileName, file.getPath());
        try {
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int width = bi.getWidth();
            int height = bi.getHeight();
            result.setHeight(height);
            result.setWidth(width);
        }catch (Exception e){

        }
        return result;
    }
}
