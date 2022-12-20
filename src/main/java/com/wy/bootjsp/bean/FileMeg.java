package com.wy.bootjsp.bean;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/20 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;FileMeg 结果文件
 */
public class FileMeg {
    /**
     * 文件的md5信息
     */
    private String fileMd5;

    /**
     * 总文件一共被分了几块
     */
    private Integer fileBlockSize;

    /**
     * 总文件的名字
     */
    private String fileName;

    /**
     * 文件的全路径名
     */
    private String pathName;

    /**
     * 文件总大小
     */
    private Long fileSize;

    public FileMeg() {
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public Integer getFileBlockSize() {
        return fileBlockSize;
    }

    public void setFileBlockSize(Integer fileBlockSize) {
        this.fileBlockSize = fileBlockSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public String toString() {
        return "FileMeg{" +
                "fileMd5='" + fileMd5 + '\'' +
                ", fileBlockSize=" + fileBlockSize +
                ", fileName='" + fileName + '\'' +
                ", pathName='" + pathName + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
