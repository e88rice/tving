const userIcon = document.getElementById("user-mypage-icon");
const userMypageContainer = document.getElementById("mypage-container");

userIcon.onmouseover = () => {
    userMypageContainer.removeAttribute("hidden");
}
userMypageContainer.onmouseleave = () => {
    userMypageContainer.toggleAttribute( "hidden");
}
