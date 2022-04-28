<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<h1><%= "Welcome to Simin's Library Application" %>
</h1>
<div id="mainForm">
        <fieldset>
            <legend>Options</legend>
            <button class="submitButton" onclick="location.href='addBook.jsp'"> Add a new Book </button><br/>
            <button class="submitButton" onclick="location.href='addAuthor.jsp'"> Add a new Author </button>
            <form method="get" enctype="application/x-www-form-urlencoded" id="mainForm_viewAuthors" action="LibraryData">
                <input type="hidden" id="viewAuthorsForm" name="viewOutputs" value="author">
                <input class="submitButton" type="submit" value="View All Authors">
            </form>
            <form method="get" enctype="application/x-www-form-urlencoded" id="mainForm_viewBooks" action="LibraryData">
                <input type="hidden" id="viewBooksForm" name="viewOutputs" value="book">
                <input class="submitButton" type="submit" value="View All Books">
            </form>
        </fieldset>
</div>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
</body>
</html>