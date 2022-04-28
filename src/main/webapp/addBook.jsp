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
        <title>Add Book</title>
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <div class="formContainer">
            <form method="post" enctype="application/x-www-form-urlencoded" id="mainForm_addBook" action="LibraryData">
                <fieldset id="addBook_fieldset">
                    <legend>Add a new Book (all fields are required)</legend>
                    <label for="bookTitle">Book Title:</label>
                    <input type="text" id="bookTitle" name="book_title" required><br><br>
                    <label for="bookISBN">Book ISBN:</label>
                    <input type="text" id="bookISBN" name="book_isbn" required><br><br>
                    <label for="bookEditionNumber">Book Edition Number:</label>
                    <input type="number" id="bookEditionNumber" name="book_edition_number" required><br><br>
                    <label for="bookCopyright">Copyright (year):</label>
                    <input type="text" id="bookCopyright" name="book_copyright" required><br><br>
                </fieldset>
                <br><br>
                <fieldset id="addAuthor_fieldset">
                    <legend>Add Book's Author (all fields are required)</legend>
                    <label for="fname">Author's First name:</label>
                    <input type="text" id="fname" name="author_first_name" required><br><br>
                    <label for="lname">Author's Last name:</label>
                    <input type="text" id="lname" name="author_last_name" required><br><br>
                </fieldset>
                <br><br>
                <input type="hidden" id="bookForm" name="submitForm" value="book">
                <input class="submitButton" type="submit" value="Submit this Book">
            </form>
            <div class="return_to_homepage">
                <button onclick="location.href='index.jsp'"> <<< Return to homepage</button>
            </div>
        </div>
    </body>
</html>
