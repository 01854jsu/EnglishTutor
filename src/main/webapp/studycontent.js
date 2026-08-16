$(document).ready(function() {
	$('#ChangeContent').click(function() {
		$.ajax({
			url : 'StudyContentServlet',
			success : function(responseText) {
				$('#ajaxStudyContentServletResponse').text(responseText);
			}
		});
	});
});