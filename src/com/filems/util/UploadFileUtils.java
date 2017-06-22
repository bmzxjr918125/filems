package com.filems.util;

import java.io.File;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.filems.config.FileConfig;
import com.filems.util.pojo.FilePath;
import com.util.pojo.Answer;

/**
 * <p>ClassName: FileUtils</p>
 * <p>@Description: 图片处理公共方法类</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-1下午5:11:54</p>
 */
public class UploadFileUtils {
	protected static final Logger log = Logger.getLogger(UploadFileUtils.class);
	 /**
	  * <p>@Description: 判断图片格式是否是.jpg.png.jpeg</p>
	  * <p>@param @param filename
	  * <p>@param @return</p>   
	  * <p>@return boolean</p> 
	  * <p>@throws</p>
	  * <p>@author BianMingZhou</p>
	  * <p>@date 2016-7-1下午5:05:36</p>
	  */
	public static boolean isImage(String filename) {
		
		return filename.toLowerCase().endsWith(".jpg")
				|| filename.toLowerCase().endsWith(".png")
				|| filename.toLowerCase().endsWith(".jpeg")
				|| filename.toLowerCase().endsWith(".tmp");
	}
	
	/**
	 * <p>@Description: 判断文件格式是否是.doc.txt.zip.apk</p>
	 * <p>@param @param filename
	 * <p>@param @return</p>   
	 * <p>@return boolean</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-1下午5:05:36</p>
	 */
	public static boolean isFile(String filename) {
		
		return filename.toLowerCase().endsWith(".doc")
				|| filename.toLowerCase().endsWith(".txt")
				|| filename.toLowerCase().endsWith(".apk")
				|| filename.toLowerCase().endsWith(".zip")
				|| filename.toLowerCase().endsWith(".tmp");
	}
	
    /**
     * <p>@Description: UUID随机生成文件名称</p>
     * <p>@param @param fileName 文件名称
     * <p>@param @return</p>   
     * <p>@return String</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-1下午5:08:39</p>
     * @param isImage  是否是图片
     * @param fileType  文件后缀名
     */
	public static String createUploadFileName(String fileName, String fileType, int isImage) {
		
		String ext = fileName.substring(
				fileName.lastIndexOf("."),
				fileName.length());
		if(isImage == 1){
			if(".tmp".equals(ext)){
				ext=".png";
			}
		}else{
			if(".tmp".equals(ext)){
				ext="."+fileType;
			}
		}
		return UUID.randomUUID().toString() + ext;
	}
	/**
	 * <p>@Description: UUID随机生成文件名称(带特殊后缀 如小图名)</p>
	 * <p>@param @param fileName 文件名称
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-1下午5:08:39</p>
	 */
	public static String createUploadFileNameSmall(String fileName) {
		
		String ext = fileName.substring(
				fileName.lastIndexOf("."),
				fileName.length());
		if(".tmp".equals(ext)){
			ext=".png";
		}
		return fileName.replace(ext,"")+ "_s" + ext;
	}

	/**
	 * <p>@Description: 删除文件</p>
	 * <p>@param @param filePath 文件路径</p>   
	 * <p>@return void</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-1下午5:10:18</p>
	 */
	public static void delImage(String filePath){

		log.info("=====>删除文件：" + filePath + "<========");

		File f = new File(filePath);
		f.delete();
	}
	
	
	/**
     * <p>@Description: 单文件上传方法</p>
     * <p>@param @param fileType 资源目录枚举index
     * <p>@param @param account  上传账户
     * <p>@param @param isImage  1图片 0 文件
     * <p>@param @throws IOException</p>   
     * <p>@return void</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-1下午9:18:44</p>
     * @param file  文件
     * @param fileContentType  文件类型
     * @param fileFileName 名称
     */
	public static Answer upload(File file, String fileContentType, String fileFileName, int fileType, String account,int isImage) throws Exception {
		
		Answer answer;
		//获取根目录地址 项目名_file/模块名/
        String filepath=FilePath.getName(fileType);
        
        int scaleHeight=FilePath.getScaleWidth(fileType);
        
		//验证
		answer = uploadFileCheck(file, fileFileName, fileType, isImage);
    	
		if(answer.get("code").toString().equals("200")){
			
			File f = null;
			
		    f = file;
			
		    //构建文件名称
			String imageName = UploadFileUtils.createUploadFileName(fileFileName,account,isImage);
			
			
			
			//存入数据库路径  项目名_file/模块名/账号/file名
			String savePath=filepath + account + FileConfig.DIR_LOC + imageName;
			
			
			FileUtils.copyFile(f, new File(FileConfig.BASEPATH + savePath));
			
			answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"上传成功");
			answer.put("path", savePath);
			
			if(isImage==1){
				
			    //构建小图	
				String imageName_s="";
				
				imageName_s=UploadFileUtils.createUploadFileNameSmall(imageName);	
				
				String savePath_s=filepath + account + FileConfig.DIR_LOC + imageName_s;
				
				//固定高度缩放
			//	ScaleImageUtils.resizeByHeight(scaleHeight,FileConfig.BASEPATH+savePath_s, f);
				SimpleImageUtils.scaleImage(scaleHeight, FileConfig.BASEPATH+savePath_s, f);
				answer.put("path_s", savePath_s);
			}
			

			
			
			
		}
		
			return answer;
	}
	/**
	 * <p>@Description: 文件验证</p>
	 * <p>@param @param file 文件
	 * <p>@param @param fileType 应资源目录枚举index
	 * <p>@param @param isImage 1图片 0 文件
	 * <p>@param @return</p>   
	 * <p>@return String</p> 
	 * <p>@throws</p>
	 * <p>@author BianMingZhou</p>
	 * <p>@date 2016-7-1下午9:37:05</p>
	 * @param fileFileName 
	 */
	private static Answer uploadFileCheck(File file, String fileFileName, int fileType, int isImage) {
		Answer answer;
		//获取根目录地址 项目名_file/模块名/
        String filepath=FilePath.getName(fileType);
       
    	if(filepath!=null){
    		
    		if(file!=null){
        		
    			if (isImage==0&&!UploadFileUtils.isFile(fileFileName)) {
        			
        			answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"选择正确格式文件"+fileFileName);
    			}else if(isImage==1&&!UploadFileUtils.isImage(fileFileName)){
    				
    				answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"选择正确格式图片"+fileFileName);
    			}else if(isImage==0&&file.length()>FileConfig.MAX_FILE_SIZE_500MB){
    				
    				answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"文件大小不能大于 500MB"+fileFileName);
    			}else if(isImage==1&&file.length()>FileConfig.MAX_FILE_SIZE_1MB){
    				System.out.println(file.length());
    				answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"图片大小不能大于 1MB"+fileFileName);
    			}else{
    				
    				answer=new Answer(Answer.SUCCESS,Answer.SUCCESS_CODE,"验证成功");
    			}
    			
        	}else{
        		
        		answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"上传文件为空");
        	}
    	}else{
    		
    		answer=new Answer(Answer.ERROR,Answer.ERROR_CODE,"未知上传文件存放地址");
    	}
    	
		return answer;
	}
    
    /**
     * <p>@Description: 多文件上传方法</p>
     * <p>@param @param fileList 文件集合
     * <p>@param @param fileListContentType 文件类型集合 
     * <p>@param @param fileListFileName 文件名称集合
     * <p>@param @param fileType 应资源目录枚举index
     * <p>@param @param account  上传账户
     * <p>@param @param isImage  1图片 0 文件
     * <p>@param @return</p>   
     * <p>@return Answer</p> 
     * <p>@throws</p>
     * <p>@author BianMingZhou</p>
     * <p>@date 2016-7-1下午9:31:19</p>
     */
	 public static Answer uploads(List<File> fileList,List<String> fileListContentType, List<String> fileListFileName,
			int fileType, String account, int isImage) throws Exception {

		Answer answer = new Answer();

		String filepath = FilePath.getName(fileType);
		
		int scaleHeight=FilePath.getScaleWidth(fileType);

		if (fileList != null && fileList.size() > 0) {

			boolean flag = true;
			for (int i = 0; i < fileList.size(); i++) {
				File file = fileList.get(i);
				// 验证
				answer = uploadFileCheck(file, fileListFileName.get(i),
						fileType, isImage);
				if (answer.get("code").toString().equals("400")) {
					flag = false;
					break;
				}
			}

			if (flag) {

				String path = "";
				String path_s = "";

				for (int i = 0; i < fileList.size(); i++) {

					File f = null;

					f = fileList.get(i);

					// 构建文件名称
					String imageName = UploadFileUtils
							.createUploadFileName(fileListFileName.get(i),account,isImage);

					// 存入数据库路径 项目名_file/模块名/账号/file名
					String savePath = filepath + account + FileConfig.DIR_LOC
							+ imageName;

					FileUtils.copyFile(f, new File(FileConfig.BASEPATH
							+ savePath));
					
					path = path + savePath + ",";
					
					if(isImage==1){
						
					    //构建小图	
						String imageName_s="";
						
						imageName_s=UploadFileUtils.createUploadFileNameSmall(imageName);	
						
						String savePath_s=filepath + account + FileConfig.DIR_LOC + imageName_s;
						
						//固定高度缩放
						//ScaleImageUtils.resizeByHeight(scaleHeight,FileConfig.BASEPATH+savePath_s, f);
						SimpleImageUtils.scaleImage(scaleHeight, FileConfig.BASEPATH+savePath_s, f);
						path_s=path_s + savePath_s + ",";
						
					}

					
				}

				answer = new Answer(Answer.SUCCESS, Answer.SUCCESS_CODE, "上传成功");
				answer.put("path", path);
				
				if(isImage==1){
					
					answer.put("path_s", path_s);
				}
				
			}
		} else {

			answer = new Answer(Answer.ERROR, Answer.ERROR_CODE, "上传文件列表为空");
		}

		return answer;
	}

}
