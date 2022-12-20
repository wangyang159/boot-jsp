package com.wy.bootjsp.controller;

import com.wy.bootjsp.bean.FileBlock;
import com.wy.bootjsp.bean.FileMeg;
import com.wy.bootjsp.service.FileBlockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/18 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;TestContrller
 */
@Controller
public class TestContrller {

    @Resource
    private FileBlockService fileBlockService;

    /**
     * 接收数据块的控制器，并支持断点续传
     * @param response
     * @param fileBlock
     * @param fileBlockMeg
     */
    @RequestMapping("/upload")
    @ResponseBody
    public void test(HttpServletResponse response, @RequestParam("fileBlock") MultipartFile fileBlock, FileBlock fileBlockMeg){
        //断点续传:用总文件的md5和文件块序号去数据库中查，如果有则跳过存储该数据块
        Integer blockId = fileBlockService.getFileBlockByMd5AndIndex(fileBlockMeg);
        if(blockId != null){
            response.addHeader("meg","0");
            return;
        }

        //如果秒传没有发现已有数据块就保存
        String path = null;
        try {
            path = fileBlockService.saveFile(fileBlock, fileBlockMeg);
        } catch (IOException e) {
            response.addHeader("meg","1");
            e.printStackTrace();
            return;
        }
        fileBlockMeg.setBlockPathName(path);
        //把块文件信息写在数据库里面
        fileBlockService.insertFileBlockMeg(fileBlockMeg);

        response.addHeader("meg","0");
    }

    /**
     * 通过文件的md5查询文件信息表是否有记录，如果有则视为本次上传为秒传
     * @param fileMd5
     * @return
     */
    @RequestMapping("/minupload")
    @ResponseBody
    public Boolean minonload(String fileMd5){
        Integer id = fileBlockService.getFileMegByMd5(fileMd5);
        if(id != null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 文件合并，并把结果文件的信息保存在数据库中
     * @param fileMd5
     * @param fileSize
     * @param fileName
     * @return
     */
    @RequestMapping("/allhb")
    @ResponseBody
    public Boolean allhb(String fileMd5,long fileSize,String fileName){
        //先查出所有文件片路径信息
        List<FileBlock> fileBlocksPath = fileBlockService.getFileBlocksPath(fileMd5);

        //获取到这些片文件,按顺序放到数组中
        File[] fileBlocks = new File[fileBlocksPath.size()];
        for (int i = 0 ; i < fileBlocksPath.size() ; i++){
            FileBlock f = fileBlocksPath.get(i);
            //下标要减一，因为片文件保存的时候是从1开始的
            fileBlocks[f.getBlockIndex()-1] = new File(f.getBlockPathName());
        }

        //合并这些文件,注意保存文件的时候文件名主体使用md5
        String savePath = fileBlockService.allhb(fileBlocks, fileMd5 + "." + fileName.split("\\.")[1]);

        //如果合并成功返回的应该是结果文件的路径，需要和其他关键信息一起保存到数据库中
        if(!savePath.equals("error")){
            FileMeg fileMeg = new FileMeg();
            fileMeg.setFileMd5(fileMd5);
            fileMeg.setFileName(fileName);//保存文件信息时保存的是文件的本来名字
            fileMeg.setFileSize(fileSize);
            fileMeg.setFileBlockSize(fileBlocks.length);
            fileMeg.setPathName(savePath);
            fileBlockService.insertFileMeg(fileMeg);
            return true;
        }else{
            return false;
        }

    }
}
