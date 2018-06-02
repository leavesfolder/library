package com.library.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/6 0006.
 * 文件的分割与合并练习
 */
public class FileUtil {
    private String filePath;
    private String fileName;
    private long fileSize;
    //块数
    private int size;
    //每一块的大小
    private long blockSize;
    //每一块的名称
    private List<String> partPath;
    private String newFilePath;

    public FileUtil(){
        partPath = new ArrayList<String>();
    }

    public FileUtil(String filePath,String newFilePath) {
        this(filePath,newFilePath,1024);
    }

    public FileUtil(String filePath,String newFilePath,long blockSize){
        this();
        this.filePath=filePath;
        this.newFilePath = newFilePath;
        this.blockSize = blockSize;
        init();
    }

    /**
     * 计算块数，确定文件名
     */
    public void init(){
        File file = new File(filePath);
        if (filePath==null||!file.exists()){
            return;
        }
        if (file.isDirectory()){
            return;
        }
        this.fileName = file.getName();
        this.fileSize = file.length();
        //修正每块大小
        if(blockSize>=file.length()){
            this.blockSize=file.length();
        }
        //计算块数
        this.size=(int)Math.ceil(file.length()/this.blockSize);
        initPartName();
    }

    /**
     * 初始化名称
     */
    public void initPartName(){
        for (int i=0;i<size;i++){
            this.partPath.add(newFilePath+"/"+fileName+"_part"+i);
        }
    }

    /**
     * 文件分割：将文件进行分割，
     * 1.首先根据文件大小，确定合适的块数，count
     * 2.算出每块文件的字节数，blockSize
     * 3.计算每块开始和结束的字节数，最后一块可能跟其他的稍有不同,最后一块的大小：fileCount-(count-1)*blockSize
     * 4.创建写入流写入文件
     */
    public  void splitFile(){

        long beginPos = 0;
        long actBlockSize = blockSize;
        for(int i = 0;i<size;i++){
            if(i==size-1){
                actBlockSize = fileSize-beginPos;
            }else {
                beginPos+=actBlockSize;
            }
            splitDetail(i,beginPos,actBlockSize);
        }
    }

    /**
     * 分割文件细节
     * @param idx 文件第几块
     * @param beginPoint 文件开始位置
     * @param actualBlockSize 截取文件大小
     */

    public  void splitDetail(int idx,long beginPoint,long actualBlockSize){
        File file = new File(filePath); //源文件
        File newFile = new File(this.partPath.get(idx));//目标文件
        RandomAccessFile raf = null;
        BufferedOutputStream bos = null;
        try{
            raf = new RandomAccessFile(file,"r");
            bos = new BufferedOutputStream(new FileOutputStream(newFile));
            raf.seek(beginPoint);
            byte[] flush = new byte[1024];
            int len;
            while (-1!=(len=raf.read(flush))){//判断是否足够
                if (actualBlockSize-len>0){
                    bos.write(flush,0,len);
                    actualBlockSize -=len;//剩余量
                }else{
                    bos.write(flush,0,(int) actualBlockSize);
                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    /**
     * 文件的合并
     */

    public void mergerFile(String distPath){
        File neWFile = new File(distPath);
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try{
            bos = new BufferedOutputStream(new FileOutputStream(neWFile,true));
            for (int i = 0;i<partPath.size();i++){
                bis = new BufferedInputStream(new FileInputStream(partPath.get(i)));
                byte[] flush = new byte[1024];
                int len;
                while (-1!=(len=bis.read(flush))){
                    bos.write(flush,0,len);
                }
                bos.flush();
            }
            bis.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public static void main(String[] args){
        FileUtil util=new FileUtil("G:\\liuys\\createExcel.txt","G:\\liuys\\test555",20);
        //util.splitFile();
        util.mergerFile("G:\\liuys\\test888\\createExcel.txt");
    }

}
