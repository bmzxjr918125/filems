package com.filems.util;

import java.awt.Image;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;  

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
   
public class SimpleImageUtils {  
 
    public static void scaleImage(int height, String savePath, File targetFile) throws Exception{
    	 File in = targetFile;      //原图片  
         File out = new File(savePath);       //目的图片  
         Image image= ImageIO.read(targetFile);
         int width = Math.round((image.getWidth(null) * height) / (height * 1.00f));
         ScaleParameter scaleParam = new ScaleParameter(width, height);  //将图像缩略到指定比例
            
         FileInputStream inStream = null;  
         FileOutputStream outStream = null;  
         WriteRender wr = null;  
         try {  
             inStream = new FileInputStream(in);  
             outStream = new FileOutputStream(out);  
             ImageRender rr = new ReadRender(inStream);  
             ImageRender sr = new ScaleRender(rr, scaleParam);  
             wr = new WriteRender(sr, outStream);  
             wr.render();                            //触发图像处理  
         } catch(Exception e) {  
             e.printStackTrace();  
         } finally {  
             IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭  
             IOUtils.closeQuietly(outStream);  
             if (wr != null) {  
                 try {  
                     wr.dispose();                   //释放simpleImage的内部资源  
                 } catch (SimpleImageException ignore) {  
                	 ignore.printStackTrace();
                 }  
             }  
         }  
    }
    
    
    
}  