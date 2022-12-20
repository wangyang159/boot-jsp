package com.wy.bootjsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.bootjsp.bean.FileBlock;
import com.wy.bootjsp.bean.FileMeg;

import java.util.List;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/20 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;FileBlockDao
 */
public interface FileBlockMapper extends BaseMapper<FileBlock> {

    /**
     * 通过md5和文件序号查询一条分片信息
     * @param fileBlock
     * @return
     */
    Integer getFileBlockByMd5AndIndex(FileBlock fileBlock);

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
     * 保存完整文件信息
     * @param fileMeg
     * @return
     */
    void insertFileMeg(FileMeg fileMeg);

}
