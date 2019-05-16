var NS = NS || {};
NS.Common = NS.Common || {};
NS.Common.PageUtil = (function($)  {
	function initSelectors(pageId, selectors) {
		for (var _attr in selectors) {
			var selector = selectors[_attr];
			selector.jqObj = $("#" + selector.id + pageId);
		}
	};
	
	
	return {
		initSelectors : initSelectors
	};
})(jQuery);