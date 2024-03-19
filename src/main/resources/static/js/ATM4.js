bal2 =document.getElementById('balance').value
var disp ="Welcome, click on any to start"
userInput=document.getElementById('userInput').value
 
function turnOn(){
    userBalance.innerText = bal2
    userDetails.innerText = disp
}
function depositMoney(){
    userDetails.innerText ="kindly enter amount to deposit"
}
 
function makeTransaction(){
    if(userDetails.innerText =="kindly enter amount to deposit"){
        bal2= Number(userInput.value) + bal2
        userBalance.innerText="your balance is" + bal2
        alert("Transaction Successful")
        userDetails.innerText= disp
    }
    else if(userDetails.innerText=="kindly enter amount to withdraw"){
        bal2 =  bal2-Number(userInput.value)
        userBalance.innerText="Your current balance is" + bal2
        alert("Transaction is Successful")
        userDetails.innerText= disp
    }
    else if( userDetails.innerText="Enter Amount to Transfer"){
        bal2= bal2-Number(userInput.value)
        userBalance.innerText="Your current balance is" + bal2
        alert("Transaction is Successful")
    }
    else if( userDetails.innerText="Enter Amount "){
        bal2= bal2-Number(userInput.value)
        userBalance.innerText="Your current balance is" + bal2
        alert("Transaction is Successful")
    }
   

    else {
        alert("Select the right option")
    }
  
}

function withDrawMoney(){
    userDetails.innerText="kindly enter amount to withdraw"
   
}
function cancel(){
    userInput.value=""
}
function trans(){
    userDetails.innerText="Enter Amount to Transfer"
}
function air(){
    userDetails.innerText="Enter Amount  "
}