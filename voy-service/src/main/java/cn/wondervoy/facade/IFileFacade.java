package cn.wondervoy.facade;

import cn.wondervoy.service.bean.FileUploadResult;

import java.io.File;

/**
 * Created by ckzhang on 15-1-12.
 */
public interface IFileFacade {
    /**
     * 通过文件key去七牛获取文件 备注：文件名建议用userId+序列ID
     *
     * @author gfzhao
     * @param fileName
     * @return
     * @since v0.0.1
     */
    public String getFileByName(String fileName) throws Exception;

    /**
     * 将文件存入七牛
     *
     * @author gfzhao
     * @param fileName
     * @return
     * @since v0.0.1
     */
    public FileUploadResult saveFileInQiNiu(String fileName, String localFile) throws Exception;

    /**
     * 将文件存入七牛
     *
     * @author gfzhao
     * @param file
     * @return
     * @since v0.0.1
     */
    public FileUploadResult saveFileInQiNiu(String fileName, File file) throws Exception;
}
