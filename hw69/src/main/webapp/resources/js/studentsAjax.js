function loadTable() {
    let tBody = document.getElementById("tBody");
    tBody.innerHTML = "";
    fetch("/api/students", {
        method: "GET"
    }).then(response => response.json())
        .then(result => {
                for (let i = 0; i < result.valueOf().length; i++) {
                    let tr = document.createElement("tr");

                    for (let val in result[i]) {
                        console.log(i);
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

                    deleteGroup(removeButton);
                    update(updateButton);


                    updateButton.setAttribute("data-id", result[i].id);
                    removeButton.setAttribute("data-id", result[i].id);
                    td.appendChild(updateButton);
                    td.appendChild(removeButton);
                    tr.appendChild(td);
                    tBody.appendChild(tr);
                }

            }
        )
};

function update(btn) {
    btn.addEventListener("click", function (e) {
        document.getElementById("firstNameUp").value = e.target.parentNode.parentNode.children[1].innerHTML ;
        document.getElementById("lastNameUp").value = e.target.parentNode.parentNode.children[2].innerHTML ;
        document.getElementById("ageUp").value = e.target.parentNode.parentNode.children[3].innerHTML ;
        document.getElementById("groupUp").value = e.target.parentNode.parentNode.children[4].innerHTML ;
        let id = e.target.dataset.id;
        formUpdateGroup.onsubmit = async (e) => {
            e.preventDefault();
            let ob = {
                firstName: document.getElementById("firstNameUp").value,
                lastName: document.getElementById("lastNameUp").value,
                age: document.getElementById("ageUp").value,
                group: document.getElementById("groupUp").value
            };
            await fetch("/api/students/" + id, {
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
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            age: document.getElementById("age").value,
            group: document.getElementById("group").value,
        };
        await fetch("/api/students", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(ob)
        });
        loadTable();
    };
}

function deleteGroup(button) {
    button.addEventListener("click", function (e) {
        let id = Number(button.dataset.id);
        fetch("/api/students/" + id, {
            method: "DELETE"
        }).then(() => {
            e.target.parentNode.parentNode.remove();
        })
    })
}
add();
loadTable();