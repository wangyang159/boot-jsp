package com.wy.bootjsp.bean;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/12/20 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;FileBlock 文件块数据的信息类
 */
public class FileBlock {
    /**
     * 总文件的md5信息
     */
    private String fileMd5;

    /**
     * 总文件一共被分了几块
     */
    private Integer fileBlockSize;

    /**
     * 该块文件的顺序号
     */
    private Integer blockIndex;

    /**
     * 总文件的名字
     */
    private String fileName;

    /**
     * 该文件块的全路径名
     */
    private String blockPathName;

    public FileBlock() {
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

    public Integer getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(Integer blockIndex) {
        this.blockIndex = blockIndex;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBlockPathName() {
        return blockPathName;
    }

    public void setBlockPathName(String blockPathName) {
        this.blockPathName = blockPathName;
    }

    @Override
    public String toString() {
        return "FileBlock{" +
                "fileMd5='" + fileMd5 + '\'' +
                ", fileBlockSize=" + fileBlockSize +
                ", blockIndex=" + blockIndex +
                ", fileName='" + fileName + '\'' +
                ", blockPathName='" + blockPathName + '\'' +
                '}';
    }
}
