"use strict";
use(function() {
    var bannerImages = pageProperties.get("bannerImages");

	return {
		icon:  granite.resource.properties["tag"],
        bannerImages : bannerImages
	};
});