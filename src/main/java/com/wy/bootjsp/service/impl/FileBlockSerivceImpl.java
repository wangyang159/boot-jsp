package com.wy.bootjsp.service.impl;

import com.wy.bootjsp.bean.FileBlock;
import com.wy.bootjsp.bean.FileMeg;
import com.wy.bootjsp.mapper.FileBlockMapper;
import com.wy.bootjsp.service.FileBlockService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/20 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;FileBlockSerivceImpl
 */
@Service
public class FileBlockSerivceImpl implements FileBlockService {

    @Resource
    private FileBlockMapper fileBlockMapper;

    @Override
    public Integer getFileBlockByMd5AndIndex(FileBlock fileBlock) {
        return fileBlockMapper.getFileBlockByMd5AndIndex(fileBlock);
    }

    @Override
    public String saveFile(MultipartFile file,FileBlock fileBlock) throws IOException {
        //结果文件名称
        String fileName = fileBlock.getFileMd5() + fileBlock.getBlockIndex() + ".ext";
        //创建新文件对象
        File destFile = new File("D:\\pic", fileName);

        //确保目标的父文件目录存在
        if (!destFile.getParentFile().exists()) {
            destFile.mkdirs();
        }
        //执行拷贝过程
        file.transferTo(destFile);
        //返回文件的全路径名
        return destFile.getPath();
    }

    /**
     * 通过md5获得一个文件信息
     *
     * @param fileMd5
     * @return
     */
    @Override
    public Integer getFileMegByMd5(String fileMd5) {
        return fileBlockMapper.getFileMegByMd5(fileMd5);
    }

    /**
     * 保存文件块信息
     *
     * @param fileBlock
     * @return
     */
    @Override
    public void insertFileBlockMeg(FileBlock fileBlock) {
        fileBlockMapper.insertFileBlockMeg(fileBlock);
    }

    /**
     * 获取所有文件的路径信息
     *
     * @param fileMd5
     * @return
     */
    @Override
    public List<FileBlock> getFileBlocksPath(String fileMd5) {
        return fileBlockMapper.getFileBlocksPath(fileMd5);
    }

    /**
     * 合并所有文件的方法,返回保存路径
     *
     * @return
     */
    @Override
    public String allhb(File[] files,String savaFileName) {
        String resultPath = "D:\\pic\\"+savaFileName;
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            //新建一个目标文件对象
            File target = new File(resultPath);
            //打开文件流输出对象
            out = new FileOutputStream(target);

            //循环读取要合并的文件集合
            for(File f : files) {
                byte[] buf = new byte[1024];
                int len = 0;
                in = new FileInputStream(f);
                while ((len = in.read(buf)) != -1) {
                    //写出数据
                    out.write(buf);
                }
                //写完之后把片文件的输入流关掉
                if (in != null) {
                    in.close();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }finally {
            //把结果文件的输出流关掉
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultPath;
    }

    /**
     * 保存完整文件信息
     *
     * @param fileMeg
     * @return
     */
    @Override
    public void insertFileMeg(FileMeg fileMeg) {
        fileBlockMapper.insertFileMeg(fileMeg);
    }
}
