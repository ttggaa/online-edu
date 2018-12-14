
(function ($) {

    $.JsonClass = function () {

        var txt = "";        
        var lp = "";
        this.push = function (key,value) {
        	txt += lp + "\"" + key + "\":\"" + value + "\"";
        	lp = ",";
        };

        this.get = function () {        	
	    	return "{" + txt + "}";
	
	    };
    };

})(jQuery);
