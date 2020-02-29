<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Group</h1>
<p><a href="<spring:url value="/students"/>">Students List</a></p>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Group Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody id="tBody">
    </tbody>
</table>

<h1>Create new group</h1>
<form id="formCreateGroup">
    <div>
        <label for="group">First name: </label>
        <input name="groupName" id="group" required/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>

<h1>Update group</h1>
<form id="formUpdateGroup">
    <div>Id changes:<span id="charges"></span></div>
    <div>
        <label for="groupName">First name: </label>
        <input name="groupName" id="groupName" required/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
<script>
    function loadTable() {
        console.log("a");
        let tBody = document.getElementById("tBody");
        tBody.innerHTML = "";
        fetch('<spring:url value="/api/groups"/>', {
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
                await fetch('<spring:url value="/api/groups/"/>' + id, {
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
            await fetch('<spring:url value="/api/groups"/>', {
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
            fetch("<spring:url value="/api/groups/"/>" + id, {
                method: "DELETE"
            }).then(() => {
                loadTable();
            })
        })
    }


    loadTable();
</script>
</body>
</html>
