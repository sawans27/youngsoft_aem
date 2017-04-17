package org.youngsoft.bench.core.models;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUse;

public class BannerImageHelper extends WCMUse{
	
	private String formatedBannerImages;

	public void setFormatedBannerImages(String formatedBannerImages) {
		this.formatedBannerImages = formatedBannerImages;
	}

	public String getFormatedBannerImages() {
		return formatedBannerImages;
	}

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		ValueMap props = getPageProperties();
		String[] imageLink = (String[]) props.get("bannerImages",String[].class);
		//formatedBannerImages = imageLink[1];
		formatBannerLinks(imageLink);
	}
	
	private void formatBannerLinks (String[] links){
		
		String updatedLink = "";
		for(String link: links){
			updatedLink = updatedLink+"\""+link+"\""+",";
		}
		
		this.setFormatedBannerImages(updatedLink);
	}
	
}
