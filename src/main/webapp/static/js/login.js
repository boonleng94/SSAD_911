$(document).ready(function(){
	$('.log-btn').click(function(){
		$('.log-status').addClass('wrong-entry');
		$('.alertlogin').fadeIn(500);
		setTimeout( "$('.alertlogin').fadeOut(1500);",3000 );
	});
	$('.form-control').keypress(function(){
		$('.log-status').removeClass('wrong-entry');
	});
});