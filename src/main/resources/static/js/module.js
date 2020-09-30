
 function modalLoading( modal ,isComplete)
{
if(isComplete)
{
console.log('loading')
modal.find('.modal-loading').hide();
}else
{
modal.find('.modal-loading').show();
}

}

function setDataToForm(form,name, value)
{
	if(value===null){
		 form.elements.namedItem(name).value = "";
		 return;
	};
	form.elements.namedItem(name).value = value;
}

