const id_input = document.getElementById("user-register-id");
const pw_input = document.getElementById("user-register-pw");
const pw_check_input = document.getElementById("user-register-pw-check");
const email_input = document.getElementById("user-register-email");
const warningTags = [...document.getElementsByClassName("warning-tag")];
const submitBtn = document.getElementById("submit-button");

id_input.onkeydown = function (element) {
    id_input.onkeyup = function (event){
        const id_pattern = /^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){6,12}$/;
        if (!id_pattern.test(id_input.value)) {
            id_input.nextElementSibling.firstElementChild.textContent = "영문 또는 영문, 숫자 조합 6~12 자리로 입력해주세요.";
            id_input.nextElementSibling.firstElementChild.style.color="red";
            id_input.removeAttribute("checked");
        }
        if (id_input.value === "" || id_input.value === null){
            id_input.nextElementSibling.firstElementChild.textContent = "입력한 내용이 없어요.";
            id_input.nextElementSibling.firstElementChild.style.color = "red";
            id_input.removeAttribute("checked");
        }
        if(id_pattern.test(id_input.value)){
            id_input.nextElementSibling.firstElementChild.textContent = "영문 또는 영문, 숫자 조합 6~12 자리";
            id_input.nextElementSibling.firstElementChild.style.color= "#a3a3a3";
            id_input.toggleAttribute("checked");
        }
        join_form_check();
    }
}

pw_input.onkeydown = function (element) {
    pw_input.onkeyup = function (event){
        const pw_pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,15}$/
        if (!pw_pattern.test(pw_input.value)) {
            pw_input.nextElementSibling.firstElementChild.textContent = "영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15 자리로 입력해주세요.";
            pw_input.nextElementSibling.firstElementChild.style.color="red";
            pw_input.removeAttribute("checked");
        }
        if (pw_input.value === "" || pw_input.value === null){
            pw_input.nextElementSibling.firstElementChild.textContent = "입력한 내용이 없어요.";
            pw_input.nextElementSibling.firstElementChild.style.color = "red";
            pw_input.removeAttribute("checked");
        }
        if(pw_pattern.test(pw_input.value)){
            pw_input.nextElementSibling.firstElementChild.textContent = "영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15 자리";
            pw_input.nextElementSibling.firstElementChild.style.color= "#a3a3a3";
            pw_input.toggleAttribute("checked");
        }
        join_form_check();
    }
}

pw_check_input.onkeydown = function (element) {
    pw_check_input.onkeyup = function (event){
        if(pw_check_input.value !== pw_input.value){
            pw_check_input.nextElementSibling.removeAttribute("hidden");
            pw_check_input.nextElementSibling.firstElementChild.textContent = "일치하지 않습니다. 다시 입력해주세요."
            pw_check_input.nextElementSibling.firstElementChild.style.color = "red";
        }
        if(pw_check_input.value === "" || pw_check_input.value === null){
            pw_check_input.nextElementSibling.removeAttribute("hidden");
            pw_check_input.nextElementSibling.firstElementChild.textContent = "입력한 내용이 없어요."
            pw_check_input.nextElementSibling.firstElementChild.style.color = "red";
        }
        if(pw_check_input.value === pw_input.value){
            pw_check_input.nextElementSibling.toggleAttribute("hidden");
            pw_check_input.nextElementSibling.firstElementChild.style.color= "#a3a3a3";
        }
        join_form_check();
    }
}

email_input.onkeydown = function (element) {
    email_input.onkeyup = function (event){
        const email_pattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{3}$/i;
        if (!email_pattern.test(email_input.value)){
            email_input.nextElementSibling.removeAttribute("hidden");
            email_input.nextElementSibling.firstElementChild.textContent = "올바른 이메일 형식이 아닙니다."
            email_input.nextElementSibling.firstElementChild.style.color = "red";
        }
        if(email_input.value === "" || email_input.value === null){
            email_input.nextElementSibling.removeAttribute("hidden");
            email_input.nextElementSibling.firstElementChild.textContent = "입력한 내용이 없어요."
            email_input.nextElementSibling.firstElementChild.style.color = "red";
        }
        if(email_pattern.test(email_input.value)){
            email_input.nextElementSibling.toggleAttribute("hidden");
            email_input.nextElementSibling.firstElementChild.style.color= "#a3a3a3";
        }
        join_form_check();
    }
}

function join_form_check(){
    warningTags.forEach(function (element){
        if (element.firstElementChild.style.color !== "red"){
            submitBtn.style.backgroundColor ="white";
            submitBtn.style.color ="black";
        }
    })
}


