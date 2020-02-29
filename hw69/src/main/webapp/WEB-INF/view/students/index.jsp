<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Students</h1>
<p><a href="<spring:url value="/group"/>">Group List</a></p>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Group Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody id="tBody">
    </tbody>
</table>

<h1>Create new student</h1>
<form id="formCreateGroup">
    <div>
        <label for="firstName">First name: </label>
        <input name="firstName" id="firstName" required/>
    </div>
    <div>
        <label for="lastName">Last name: </label>
        <input name="lastName" id="lastName" required/>
    </div>
    <div>
        <label for="age">Age: </label>
        <input name="age" id="age" type="number" min="8" max="60" required/>
    </div>

    <div>
        <label for="group">Group: </label>
        <input name="group" id="group" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
<h1>Update student</h1>
<form id="formUpdateGroup">
    <div>
        <label for="firstNameUp">First name: </label>
        <input name="firstName" id="firstNameUp" required/>
    </div>
    <div>
        <label for="lastNameUp">Last name: </label>
        <input name="lastName" id="lastNameUp" required/>
    </div>
    <div>
        <label for="ageUp">Age: </label>
        <input name="age" id="ageUp" type="number" min="8" max="60" required/>
    </div>

    <div>
        <label for="groupUp">Group: </label>
        <input name="group" id="groupUp" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
<script >
    function loadTable() {
        let tBody = document.getElementById("tBody");
        tBody.innerHTML = "";
        fetch('<spring:url value="/api/students"/>', {
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
                        add();

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
                await fetch('<spring:url value="/api/students/"/>' + id, {
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
            await fetch('<spring:url value="/api/students"/>', {
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
            fetch("<spring:url value="/api/students/"/>" + id, {
                method: "DELETE"
            }).then(() => {
                e.target.parentNode.parentNode.remove();
            })
        })
    }
    add();
    loadTable();
</script>

</body>
</html>
