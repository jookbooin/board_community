<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>File Upload Form</title>
</head>
<body>

<div th:if="${filename}">
    <h2 th:text="${filename}"/>
</div>

<form method="POST" enctype="multipart/form-data" action="#"
      th:action="@{/files}"> File: <input type="file" name="file"/>
    <input type="submit" value="Upload"/>
</form>

</body>
</html>