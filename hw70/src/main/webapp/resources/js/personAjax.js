function loadData() {
    fetch("/api",{
        method: "GET"
    }).then(response=>response.json()).then(result=>{
        for (let i = 0; i < result.valueOf().length; i++) {
            console.log(result[i].avatar);
            let tr = document.createElement("tr");
            let img = document.createElement("img");
            let j = 0;
            for (let val in result[i]) {
                if(j >= 1) {
                    let td = document.createElement("td");

                    if(result[i][val].lastIndexOf(".jpg") > -1){
                        img.src = result[i].avatar;
                    }else {
                        td.appendChild(document.createTextNode(result[i][val]));
                    }
                    tr.appendChild(img);
                    tr.appendChild(td);
                }
                ++j;
            }
            let tBody = document.querySelector("tbody");
            tBody.appendChild(tr);
        }
    });
}
loadData();