package com.siminproject.assignment2_simin_servlet_jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet class that provides database connectivity features to the upcoming forms (requests / responses)
 * @Author Simin
 */
@WebServlet("/LibraryData")
public class LibraryData extends HttpServlet {
//    private static final long serialVersionUID = 1L;
    public static String srcForm;
    private static final String BOOKS_VIEW = "SELECT * FROM all_books";
    private static final String AUTHORS_VIEW = "SELECT * FROM all_authors";

    /**
     * Handles all get method features of the application's form
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        srcForm = request.getParameter("viewOutputs");

            try{
                // connection created
                Class.forName("org.mariadb.jdbc.Driver");
                Connection conn = DBConnection.initDatabase();
                assert conn != null;

                // distinguished forms
                if (srcForm.equals("author")) {
                PreparedStatement pstatement = conn.prepareStatement(AUTHORS_VIEW);
                ResultSet result = pstatement.executeQuery();

                String targetJSP = "ViewAuthors.jsp"; // destination jsp file
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);

                // top of the table created based on the DB view for authors
                String finalViewPart1 = "<div id='outputView'>" +
                        "<h1>The Authors Table</h1><br/>" +
                        "<br/><table id='tableFromDB' cellspacing='0' cellpadding='5' border='1'>" +
                        "<tr class='table-heading'>" +
                        "<td><b>Author's Name</b></td>"+
                        "<td><b>Book Title</b></td>"+
                        "</tr>";
                String finalViewPart2 = "";
                while (result.next()) {
                    finalViewPart2 += "<tr>" +
                            "<td>" + result.getString(1) + "</td>" +
                            "<td>" + result.getString(2) + "</td>" +
                            "</tr>";
                }
                finalViewPart2 = finalViewPart2.replace("null", "No Book");
                String finalViewPart3 = "</table><br/></div>";
                String finalView = finalViewPart1 + finalViewPart2 + finalViewPart3;

                // updates the target page's attribute with the query results (of type String)
                request.setAttribute("finalOutput_ViewAuthor", finalView);
                requestDispatcher.forward(request, response);

            }
                else if (srcForm.equals("book")) {
                    PreparedStatement pstatement = conn.prepareStatement(BOOKS_VIEW);
                    ResultSet result = pstatement.executeQuery();
                    String targetJSP = "ViewBooks.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);

                    String finalViewPart1 = "<div id='outputView'>" +
                            "<h1>The Books Table</h1><br/>" +
                            "<br/><table id='tableFromDB' cellspacing='0' cellpadding='5' border='1'>" +
                            "<tr class='table-heading'>" +
                            "<td><b>Book's Title</b></td>"+
                            "<td><b>Author's Name</b></td>"+
                            "</tr>";
                    String finalViewPart2 = "";
                    while (result.next()) {
                        finalViewPart2 += "<tr>" +
                                "<td>" + result.getString(1) + "</td>" +
                                "<td>" + result.getString(2) + "</td>" +
                                "</tr>";
                    }
                    String finalViewPart3 = "</table><br/></div>";
                    String finalView = finalViewPart1 + finalViewPart2 + finalViewPart3;
                    request.setAttribute("finalOutput_ViewBook", finalView);
                    requestDispatcher.forward(request, response);

                }
                conn.close();
        }
            catch(Exception e){
                e.printStackTrace();
            }
    }

    /**
     * Handles all post method features of the application's form
     * Posts all users input to the corresponding tables on the DB
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Here we distinguish which form is involved (Books or Authors form)
        srcForm = request.getParameter("submitForm");

        // Populates the corresponding tables based on the incoming input data by user
        String SQL_Stm_Authors = "INSERT INTO authors values (?, ? , ?)";
        String SQL_Stm_Titles = "INSERT INTO titles values (?, ?, ?, ?)";
        String SQL_Stm_AuthorISBN = "INSERT INTO authorisbn values (? , ?)";

        // ID generated for the new Author
        int author_ID = getAuthorIDFromDB();

        String book_isbn;
        String book_title;
        int book_edition_number;
        String book_copyright;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DBConnection.initDatabase();
            assert conn != null;

            String author_first_name = request.getParameter("author_first_name");
            String author_last_name = request.getParameter("author_last_name");
            PreparedStatement post_author = conn.prepareStatement(SQL_Stm_Authors);
            PreparedStatement post_book = conn.prepareStatement(SQL_Stm_Titles);
            PreparedStatement post_authorisb = conn.prepareStatement(SQL_Stm_AuthorISBN);

            // Sets each value of the SQL statement to be filled in the corresponding table in DB
            post_author.setInt(1, author_ID);
            post_author.setString(2, author_first_name);
            post_author.setString(3, author_last_name);

            if (srcForm.equals("book")) {
                book_isbn = request.getParameter("book_isbn");
                book_title = request.getParameter("book_title");
                book_edition_number = Integer.parseInt(request.getParameter("book_edition_number"));
                book_copyright = request.getParameter("book_copyright");

                post_book.setString(1, book_isbn);
                post_book.setString(2, book_title);
                post_book.setInt(3, book_edition_number);
                post_book.setString(4, book_copyright);

                post_authorisb.setInt(1, author_ID);
                post_authorisb.setString(2, book_isbn);
            }

            request.getRequestDispatcher("index.jsp").forward(request, response);

            post_author.executeUpdate();
            post_author.close();

            if (srcForm.equals("book")) {
                post_book.executeUpdate();
                post_authorisb.executeUpdate();
                post_book.close();
                post_authorisb.close();
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates DB ID for the New Author based on the count of the existing authors in the DB
     * @return new ID for the new Author
     */
    private int getAuthorIDFromDB () {
        int authorsQ = 0;

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DBConnection.initDatabase();
            assert conn != null;

            Statement statement = conn.createStatement();

            // SQL statement returns a number of all existing Authors in the author table of the DB
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM authors");
            while(resultSet.next()) {
                authorsQ = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return authorsQ + 1; // all existing authors quantity + 1
    }
}
