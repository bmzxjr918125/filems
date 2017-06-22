package com.filems.api.action;

import java.io.File;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.filems.base.action.BaseAction;
import com.filems.config.FileConfig;
import com.filems.util.RequestUtils;
import com.filems.util.UploadFileUtils;
import com.util.pojo.Answer;

/**
 * <p>ClassName: filemsApiAction</p>
 * <p>@Description: 文件上传服务</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-1下午4:22:41</p>
 */
@Controller("filemsApiAction")
@Scope("prototype")
public class filemsApiAction extends BaseAction {
	private static final long serialVersionUID = -5089977593480468223L;
    private Answer answer;
    //单文件
    private File file;
    private String fileFileName;
	private String fileContentType;
	
	//多文件
	private List<File> fileList;
	private List<String> fileListFileName;
	private List<String> fileListContentType;
	
	//单图片
    private File image;
    private String imageFileName;
	private String imageContentType;
	
	//多图片
	private List<File> imageList;
	private List<String> imageListFileName;
	private List<String> imageListContentType;
    
	/**
	 * <p>@Description: 单文件上传</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-1下午4:35:32</p>
	 */
    public String UploadFile() throws Exception{
    	
    	//对应资源目录index
        int fileType=RequestUtils.getIntParamter("fileType");  
        String account=RequestUtils.getStrParamter("account");  
        
        if(account!=null && !"".equals(account.trim())){
        	
        	this.answer=UploadFileUtils.upload(this.file,this.fileContentType,this.fileFileName,fileType, account,0);
        }else{
        	
        	this.answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"账户参数不正确");
        }
        
        
    	
    	return super.ANSWER;
    }
    /**
	 * <p>@Description: 多文件上传</p>
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-1下午4:35:32</p>
	 */
    public String UploadFileList() throws Exception{
    	
    	//对应资源目录index
        int fileType=RequestUtils.getIntParamter("fileType");  
        String account=RequestUtils.getStrParamter("account");  
        
        if(account!=null && !"".equals(account.trim())){
        	
        	this.answer=UploadFileUtils.uploads(this.fileList,this.fileListContentType,this.fileListFileName,fileType, account,0);
        }else{
        	
        	this.answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"账户参数不正确");
        }
        
    	
    	return super.ANSWER;
    }
	/**
     * <p>@Description: 单图片上传</p>
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-1下午4:35:32</p>
     */
    public String UploadImage() throws Exception{
    	
    	//对应资源目录index
    	int fileType=RequestUtils.getIntParamter("fileType");  
    	String account=RequestUtils.getStrParamter("account");  
    	 
    	 if(account!=null && !"".equals(account.trim())){
         	
    		this.answer=UploadFileUtils.upload(this.image,this.imageContentType,this.imageFileName,fileType, account,1);
         }else{
         	
         	this.answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"账户参数不正确");
         }
    	
    	
    	return super.ANSWER;
    }
    /**
   	 * <p>@Description: 多图片上传</p>
   	 * <p>@param @return</p>   
   	 * <p>@return String</p> 
   	 * <p>@throws</p>
   	 * <p>@author BianMingZhou</p>
   	 * <p>@date 2016-7-1下午4:35:32</p>
   	 */
    public String UploadImageList() throws Exception{
       	
       	//对应资源目录index
        int fileType=RequestUtils.getIntParamter("fileType");  
        String account=RequestUtils.getStrParamter("account");  
        
         if(account!=null && !"".equals(account.trim())){
         	
        	this.answer=UploadFileUtils.uploads(this.imageList,this.imageListContentType,this.imageListFileName,fileType, account,1);
         }else{
         	
         	this.answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"账户参数不正确");
         }
        
         System.out.println("answer="+this.answer.toString());
       	return super.ANSWER;
       }
    
    /**
   	 * <p>@Description: 多图片上传</p>
   	 * <p>@param @return</p>   
   	 * <p>@return String</p> 
   	 * <p>@throws</p>
   	 * <p>@author BianMingZhou</p>
   	 * <p>@date 2016-7-1下午4:35:32</p>
   	 */
    public String CopyFile() throws Exception{
       	
       	//对应资源目录index
        int fileType=RequestUtils.getIntParamter("fileType");  
        String account=RequestUtils.getStrParamter("account");  
        
        //资源路径
        String olfFilePath=FileConfig.BASEPATH+RequestUtils.getStrParamter("olfFilePath");
        
        	//存入本地
            File f=new File(olfFilePath); 
            answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"转存成功");
			
        
			if(account!=null && !"".equals(account.trim())){
	         	
	    		this.answer=UploadFileUtils.upload(f,f.getName().substring(f.getName().lastIndexOf(".")+1,f.getName().length()),f.getName(),fileType, account,1);
	         }else{
	         	
	         	this.answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"账户参数不正确");
	         }
        
   	
       	return super.ANSWER;
       }
    
    /**
     * <p>@Description: 删除文件</p>
     * <p>@param @return
     * <p>@param @throws Exception</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-12上午10:03:13</p>
     * url:http://192.168.0.188:8080/filems/filemsApiDeleteFile?path=xx_file/userlogo/10000/8aaa6837-73e0-4ca8-b6f4-f208b4ee2722.png
     */
    public String DeleteFile() throws Exception{
    	String path=RequestUtils.getStrParamter("path");
    	
    	if(path!=null && !"".equals(path.trim())){
          	
    		String path_s=UploadFileUtils.createUploadFileNameSmall(path);
    		
    		
         	UploadFileUtils.delImage(FileConfig.BASEPATH + path);
         	
         	UploadFileUtils.delImage(FileConfig.BASEPATH + path_s);
         	
         	this.answer = new Answer(Answer.SUCCESS, Answer.SUCCESS_CODE, "删除成功");
        }else{
          	
          	this.answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"资源地址参数不正确");
        }
    	
    	return super.ANSWER;
    }

    
    public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	public List<String> getFileListFileName() {
		return fileListFileName;
	}
	public void setFileListFileName(List<String> fileListFileName) {
		this.fileListFileName = fileListFileName;
	}
	public List<String> getFileListContentType() {
		return fileListContentType;
	}
	public void setFileListContentType(List<String> fileListContentType) {
		this.fileListContentType = fileListContentType;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public List<File> getImageList() {
		return imageList;
	}
	public void setImageList(List<File> imageList) {
		this.imageList = imageList;
	}
	public List<String> getImageListFileName() {
		return imageListFileName;
	}
	public void setImageListFileName(List<String> imageListFileName) {
		this.imageListFileName = imageListFileName;
	}
	public List<String> getImageListContentType() {
		return imageListContentType;
	}
	public void setImageListContentType(List<String> imageListContentType) {
		this.imageListContentType = imageListContentType;
	}
}
