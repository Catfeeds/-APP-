package com.hcb.xigou.pojo;

public class GoodsWithBLOBs extends Goods {
    private String poster;

    private String photos;

    private String models;
    
    private String skus;

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		String str1 = poster;
		if ("0".equals(str1.substring(0, 1))) {
			this.poster = str1.substring(1, str1.length()) == null ? null : str1.substring(1, str1.length()).trim();
		} else {
			String str2 ="\"" +str1.substring(2, str1.length() - 1);
			this.poster = str2;
		}

	}

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
    	String str1 = photos;
    	if("0".equals(str1.substring(0,1))){
    		 this.photos = str1.substring(2,str1.length()) == null ? null : str1.substring(1,str1.length()).trim();
    	}else{
    		String str2 ="\"" + str1.substring(2,str1.length()-2);
            this.photos = str2;
    	}
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models == null ? null : models.trim();
    }
    
    public String getSkus() {
		return skus;
	}

	public void setSkus(String skus) {
		this.skus = skus == null ? null : skus.trim();
	}
}