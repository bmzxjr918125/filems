package com.filems.util.pojo;
/**
 * <p>ClassName: FilePath</p>
 * <p>@Description: 资源文件存放目录</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-7-1下午8:56:39</p>
 */
public enum FilePath {
	HEADERIMAGE("tmms_file/headerImage/", 1,320), 
	PRODUCTSCAN("tmms_file/productScan/", 2,320), 
	PRODUCTDESC("tmms_file/productDesc/", 3,750), 
	EXTENDATTRIBUTE("tmms_file/extendAttribute/", 4,400),
	SHOPBACKGROUND("tmms_file/shopBackground/", 5,400),
	SHOPLOGO("tmms_file/shopLogo/", 6,400),
	ECSHOPLOGO("tmms_file/ecShopLogo/", 7,400),
	ECSHOPBACKGROUND("tmms_file/ecShopBackground/", 8,400),
	EVALUATE("tmms_file/evaluate/", 9,400),
	APPFILE("tmms_file/appFile/", 10,400),
	CATEGORY_ICON("tmms_file/categoryIcon/",11,120);
        // 模块目录
        private String name;
        //模块标号
        private int index;
        //该模块下图片 小图缩放后的高度
        private int scaleHeight;

        // 构造方法
        private FilePath(String name, int index,int scaleHeight) {
            this.name = name;
            this.index = index;
            this.scaleHeight = scaleHeight;
        }
        // 获取模块目录名
        public static String getName(int index) {
        	
            for (FilePath c : FilePath.values()) {
            	
                if (c.getIndex() == index) {
                	
                    return c.name;
                }
            }
            return null;
        }
        
        // 获取小图缩放后的宽度
        public static int getScaleWidth(int index) {
        	
        	for (FilePath c : FilePath.values()) {
        		
        		if (c.getIndex() == index) {
        			
        			return c.scaleHeight;
        		}
        	}
        	
        	return 0;
        }

        
		// get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
		public int getScaleHeight() {
			return scaleHeight;
		}
		public void setScaleHeight(int scaleHeight) {
			this.scaleHeight = scaleHeight;
		}
		
        
    }
