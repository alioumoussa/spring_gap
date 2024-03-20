/**
 * Created by chuandong on 15/11/27.
 */

function load() {

	var btns = document.querySelectorAll('#calculator span'); 
	var inputScreen = document.querySelector('#screen');
	var inputForm=document.getElementById('input').value;
	var btnValue; 
 	for (var i = 0; i < btns.length; i++) {

		btns[i].addEventListener('click', function() {

			btnValue = this.innerHTML; 
			
			switch (btnValue) {
				case 'Enter':
					console.log("login")
					
					break;
					case 'submit':
					break;
				case 'Sup':
					inputScreen.innerHTML = '';
			 
					break;

				default:
					inputScreen.innerHTML += "*";
					
					inputForm += btnValue;
				 alert(btnValue+inputForm)
					break;
			}
		});
	}
}
