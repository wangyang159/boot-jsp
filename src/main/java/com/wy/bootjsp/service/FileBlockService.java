package com.wy.bootjsp.service;

import com.wy.bootjsp.bean.FileBlock;
import com.wy.bootjsp.bean.FileMeg;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/20 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;FileBlockService
 */
public interface FileBlockService {

    /**
     * 通过md5和文件序号查询一条分片信息
     * @param fileBlock
     * @return
     */
    Integer getFileBlockByMd5AndIndex(FileBlock fileBlock);

    /**
     * 文件存储
     * @param file
     * @param fileBlock
     * @return
     */
    String saveFile (MultipartFile file,FileBlock fileBlock) throws IOException;

    /**
     * 通过md5获得一个文件信息
     * @param fileMd5
     * @return
     */
    Integer getFileMegByMd5(String fileMd5);

    /**
     * 保存文件块信息
     * @param fileBlock
     * @return
     */
    void insertFileBlockMeg(FileBlock fileBlock);

    /**
     * 获取所有文件的路径信息
     * @param fileMd5
     * @return
     */
    List<FileBlock> getFileBlocksPath(String fileMd5);

    /**
     * 合并所有文件的方法
     * @param files
     * @param savaFileName 合并结果的文件名
     * @return
     */
    String allhb(File[] files,String savaFileName);

    /**
     * 保存完整文件信息
     * @param fileMeg
     * @return
     */
    void insertFileMeg(FileMeg fileMeg);
}
