const moreBtn = document.getElementById("more-btn");
const closeBtn = document.getElementById("close-btn");
const introduceMore = document.getElementsByClassName("product-introduce-more").item(0);

moreBtn.onclick = () => {
    introduceMore.toggleAttribute("hidden");
    moreBtn.toggleAttribute("hidden");
}

closeBtn.onclick = () => {
    introduceMore.toggleAttribute("hidden");
    moreBtn.toggleAttribute("hidden");
}