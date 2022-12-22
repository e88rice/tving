const moreBtn = document.getElementById("more-btn");
const closeBtn = document.getElementById("close-btn");
const introduceMore = document.getElementsByClassName("product-introduce-more").item(0);
const watchBtn = document.getElementById("program-watch-btn");
const moreWatchBtn = [...document.getElementsByClassName("more-watch-btn")];
const productRealIntroduce = document.getElementById("product-real-introduce");

function program_watch_event(programName, order){
    location.href = `/main/program/watch/${programName}/${order}`;
}

moreWatchBtn.forEach(btns => {
    btns.onclick=()=>{
        let name = btns.firstElementChild.getAttribute("name");
        let order = btns.getAttribute("name");
        program_watch_event(name, order)
    }

})

moreBtn.onclick = () => {
    introduceMore.toggleAttribute("hidden");
    productRealIntroduce.style.webkitLineClamp = '30';
    moreBtn.t
}

closeBtn.onclick = () => {
    introduceMore.toggleAttribute("hidden");
    productRealIntroduce.style.webkitLineClamp = '4';
    moreBtn.toggleAttribute("hidden");
}