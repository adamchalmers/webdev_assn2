$(document).ready(function()
{
	
$(".tab").click(function()
{
var X=$(this).attr('id');
 
if(X=='signup')
{
$("#login").removeClass('select');
$("#signup").addClass('select');
$("#loginbox").slideUp();
$("#signupbox").slideDown();
}
else
{
$("#signup").removeClass('select');
$("#login").addClass('select');
$("#signupbox").slideUp();
$("#loginbox").slideDown();
}
 
});

});