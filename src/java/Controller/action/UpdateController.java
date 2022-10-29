/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.action;

import context.action.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author ttaad
 */
public class UpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cmtId = Integer.parseInt(request.getParameter("cId"));
        int bookId = Integer.parseInt(request.getParameter("bId"));
        int userId = Integer.parseInt(request.getParameter("uId"));
        String newComment = request.getParameter("edit");
        String oldComment = request.getParameter("oldCmt");
        CommentDAO cmtDAO = new CommentDAO();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        cmtDAO.editComment(bookId, userId, oldComment, newComment, updateTime);
        response.sendRedirect("./BookDetail?id=" + bookId);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cmtId = Integer.parseInt(request.getParameter("cId"));
        int bookId = Integer.parseInt(request.getParameter("bId"));
        int userId = Integer.parseInt(request.getParameter("uId"));
        CommentDAO cmtDAO = new CommentDAO();
        cmtDAO.deleteComment(bookId, userId, cmtId);
        response.sendRedirect("./BookDetail?id=" + bookId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
