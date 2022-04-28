<%--
  Created by IntelliJ IDEA.
  User: simin
  Date: 2/20/2022
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Author</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <div class="formContainer">
            <form method="post" enctype="application/x-www-form-urlencoded" id="mainForm_addAuthor" action="LibraryData">
                <fieldset>
                    <legend>Add a new Author (all fields are required)</legend>
                    <label for="fname">Author's First name:</label>
                    <input type="text" id="fname" name="author_first_name" required><br><br>
                    <label for="lname">Author's Last name:</label>
                    <input type="text" id="lname" name="author_last_name" required><br><br>
                    <input type="hidden" id="authorForm" name="submitForm" value="author">
                    <input class="submitButton" type="submit" value="Submit Author's Info">
                </fieldset>
            </form>
            <div class="return_to_homepage">
                <button onclick="location.href='index.jsp'"> <<< Return to homepage</button>
            </div>
        </div>

    </body>
</html>
