const emailSelect = document.getElementById("email-select");
const emailInput1 = document.getElementById("email-input-1");
const emailInput2 = document.getElementById("email-input-2");
const infoEmailHidden = document.getElementById("info-email-hidden");

const phoneSelect = document.getElementById("phoneNum-select");
const phoneNumInput1 = document.getElementById("phoneNum-input-1");
const phoneNumInput2 = document.getElementById("phoneNum-input-2");
const infoPhoneHidden = document.getElementById("info-phoneNum-hidden");


const infoSubmit = document.getElementById("info-submit");


infoSubmit.onclick = () => {
    if(emailSelect.value.toString() === "직접입력"){
      infoEmailHidden.value = emailInput1.value +'@'+ emailInput2.value;
    }
    if(emailSelect.value.toString() !== "직접입력"){
        infoEmailHidden.value = emailInput1.value +'@'+ emailSelect.value;
    }
    infoPhoneHidden.value = phoneSelect.value+"-"+phoneNumInput1.value+"-"+phoneNumInput2.value;
    console.log(infoPhoneHidden.value+"이메일="+infoEmailHidden.value)

    const infoForm = document.forms.namedItem("info-form");
    infoForm.action = '/user/info';
    infoForm.method = 'post';
    infoForm.submit();
}

emailSelect.onchange = () => {
    if(emailSelect.value.toString() === "직접입력"){
        emailInput2.value = "";
    }
    else{
        emailInput2.value = emailSelect.value;
    }
}

