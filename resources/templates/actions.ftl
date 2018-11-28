<html>
<head>
    <title>Actions</title>
</head>
<body>
    <h1>Door Actions</h1>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>User Id</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <#list actions as action>
                <tr>
                    <td>${action.id}</td>
                    <td>${action.userId}</td>
                    <td>${action.date}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>