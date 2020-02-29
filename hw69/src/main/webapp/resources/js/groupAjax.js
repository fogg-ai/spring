function loadTable() {
    console.log("a");
    let tBody = document.getElementById("tBody");
    tBody.innerHTML = "";
    fetch("/api/groups", {
        method: "GET"
    }).then(response => response.json())
        .then(result => {
            for (let i = 0; i < result.valueOf().length; i++) {
                let tr = document.createElement("tr");

                for (let val in result[i]) {
                    let td = document.createElement("td");
                    td.appendChild(document.createTextNode(result[i][val]));
                    tr.appendChild(td);
                }

                let td = document.createElement("td");
                let updateButton = document.createElement("button");
                let removeButton = document.createElement("button");

                let upDateText = document.createTextNode("Update");
                let removeText = document.createTextNode("Remove");

                updateButton.appendChild(upDateText);
                removeButton.appendChild(removeText);

                deleteN(removeButton);
                update(updateButton);
                add();

                updateButton.setAttribute("data-id", result[i].id);
                removeButton.setAttribute("data-id", result[i].id);
                td.appendChild(updateButton);
                td.appendChild(removeButton);
                tr.appendChild(td);
                tBody.appendChild(tr);
            }})
};

function update(btn) {
    btn.addEventListener("click", function (e) {
        document.getElementById("groupName").value = e.target.parentNode.parentNode.children[1].innerHTML ;
        let id = e.target.dataset.id;
        formUpdateGroup.onsubmit = async (e) => {
            e.preventDefault();
            let ob = {
                groupName: document.getElementById("groupName").value
            };
            await fetch('/api/groups/' + id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(ob)
            });
            console.log(JSON.stringify(ob));
            loadTable();
        }
    });
}

function add() {
    formCreateGroup.onsubmit = async (e) => {
        e.preventDefault();
        let ob = {
            groupName: document.getElementById("group").value
        };
        await fetch('/api/groups', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(ob)
        });
        loadTable();
    };
}

function deleteN(button) {
    button.addEventListener("click", function () {
        let id = Number(button.dataset.id);
        fetch("/api/groups/" + id, {
            method: "DELETE"
        }).then(() => {
            loadTable();
        })
    })
}


loadTable();