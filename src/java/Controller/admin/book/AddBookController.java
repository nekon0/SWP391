/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin.book;

import Controller.product.BookDetail;
import Model.product.Author;
import Model.product.Book;
import Model.product.Category;
import context.product.AuthorDAO;
import context.product.BookDAO;
import context.product.CategoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtil;

/* @author ACER */
@WebServlet(name = "AddBookController", urlPatterns = {"/Admin/AddBook"})
public class AddBookController extends HttpServlet {

    MyUtil mu = new MyUtil();
    BookDAO bd = new BookDAO();
    CategoryDAO cd = new CategoryDAO();
    AuthorDAO ad = new AuthorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Category> cates = cd.getAllCategory();
            ArrayList<Author> authors = ad.getAllAuthor();
            request.setAttribute("authors", authors);
            request.setAttribute("categories", cates);
            request.setAttribute("service", "Add");
            request.setAttribute("newId", bd.countBookNumber() + 1);
            request.getRequestDispatcher("/manage/book/book-detail.jsp").forward(request, response);
        } catch (Exception e) {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Category> cates = cd.getAllCategory();
        request.setAttribute("categories", cates);
        ArrayList<Author> authors = ad.getAllAuthor();
        request.setAttribute("authors", authors);
        request.setAttribute("service", "Add");
        try {
            int id = mu.fieldInt(request.getParameter("id"), "Wrong format!");
            String title = mu.fieldString(request.getParameter("title"), "Required field!");
            String author = request.getParameter("authorId");
            String[] category = request.getParameterValues("categoryId");
            if (category == null) {
                throw new Exception("Genre required!");
            }
            float price = (float) mu.fieldDouble(request.getParameter("price"), "Wrong format!");
            boolean issale = (request.getParameter("issale") != null);
            String description = mu.fieldString(request.getParameter("description"), "Required field!");
            String img = mu.fieldString(request.getParameter("image"), "Required field!");

            Book book = new Book();
            book.setId(id);
            book.setTitle(title);

            if (author != null) {
                book.setAuthorId(Integer.parseInt(author));
            } else {
                String newAuthor = request.getParameter("author");
                Author au = new Author();
                au.setName(newAuthor);
                book.setAuthor(au);
            }

            book.setCategory(category);
            book.setPrice(price);
            book.setIssale(issale);
            book.setImage(img);
            book.setDescription(description);

            if (bd.addBook(book) == 0) {
                request.setAttribute("message", "Add Failed! Please try again!");
                request.setAttribute("book", book);
                request.setAttribute("newId", bd.countBookNumber() + 1);
                request.getRequestDispatcher("/manage/book/book-detail.jsp").forward(request, response);
            } else {
                response.sendRedirect("./Book");
            }
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("newId", bd.countBookNumber() + 1);
            request.getRequestDispatcher("/manage/book/book-detail.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
