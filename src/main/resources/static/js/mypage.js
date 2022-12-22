
const historyContentsContainer = document.getElementById("view-history-TV")

function get_all_watch_program_list(){
    const request = new XMLHttpRequest()
    request.open('GET', '/user/mypage/watch');
    request.send();
    request.onload = () => {
        if(request.status === 200){
            console.log("리폰: "+JSON.parse(request.response))
            historyContentsContainer.innerHTML = '';
            const responseObj = JSON.parse(request.response);
            responseObj.forEach( data => {
                create_view_history(data)
            })
        }
    }
}

function create_view_history(data){
    historyContentsContainer.insertAdjacentHTML('beforeend', "      <div class=\"view-history-content\">\n" +
        "        <img src=\"/image/"+data.programName+"/"+data.watchOrder+".webp"+"\" alt=\"\">\n" +
        "        <div class=\"history-TV-title\">"+ data.title +' '+ data.watchOrder + '화' + "</div>\n" +
        "        <div class=\"history-TV-date\">"+ data.watchDate+"</div>\n" +
        "      </div>")
}

window.onload = () => {
    get_all_watch_program_list();
}
