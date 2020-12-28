
 function modalLoading( modal ,isComplete)
{
if(isComplete)
{
console.log('loading');
modal.find('.modal-loading').hide();
}else
{
modal.find('.modal-loading').show();
}

}

function setDataToForm(form,name, value)
{
    console.log('form ');console.log(form);
    console.log('name '+name);
    console.log('value '+value);
	if(value===null||value==null){
		 form.elements.namedItem(name).value = "";
		 return;
	};
	form.elements.namedItem(name).value = value;
}

