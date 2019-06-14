$( document ).ready(function() {
	$("#btnAjaxTest").click(function(){
		 $.ajax({
	          type: 'GET',
	          dataType: "jsonp",
	          contentType: "application/json; charset=utf-8",
	          url: 'http://localhost:8089/spring-test/spring-resource-2/test/test2',
	          headers: {
	            //WRITE IF THEIR HAVE SOME HEADER REQUEST OR DATA
	          },
	          crossDomain: true,
	          success: function (data, textStatus, xhr) {
	            console.log(data);
	          },
	          error: function (xhr, textStatus, errorThrown) {
	            console.log(errorThrown);
	          }
	        });
	});
});