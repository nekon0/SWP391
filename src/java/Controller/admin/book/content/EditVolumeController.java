/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin.book.content;

import Model.Volume;
import context.BookDAO;
import context.ChapterDAO;
import context.VolumeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author ACER */
@WebServlet(name = "EditVolumeController", urlPatterns = {"/Admin/Book/EditVolume"})
public class EditVolumeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int volumeId = Integer.parseInt(request.getParameter("id"));
            BookDAO bd = new BookDAO();
            VolumeDAO vd = new VolumeDAO();
            ChapterDAO cd = new ChapterDAO();

            Volume vol = vd.getVolume(volumeId);
            request.setAttribute("vol", vol);
            request.setAttribute("book", bd.getBookById(vol.getBookId()));
            request.setAttribute("volumes", vd.getAllVolume(vol.getBookId()));
            request.setAttribute("chapters", cd.getAllChapter(vol.getBookId()));
            request.setAttribute("service", "Edit");

            request.getRequestDispatcher("/manage/book/toc/volume-detail.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddVolumeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String name = request.getParameter("volumeName");
        String summary = request.getParameter("summary");

        Volume volume = new Volume();
        volume.setId(id);
        volume.setVolumeName(name);
        volume.setSummary(summary);

        VolumeDAO vd = new VolumeDAO();

        if (vd.editVolume(volume) == 0) {
            ChapterDAO cd = new ChapterDAO();
            BookDAO bd = new BookDAO();
            request.setAttribute("book", bd.getBookById(bookId));
            request.setAttribute("volumes", vd.getAllVolume(bookId));
            request.setAttribute("chapters", cd.getAllChapter(bookId));
            request.setAttribute("message", "Edit Failed! Please try again!");
            request.setAttribute("vol", volume);
            request.setAttribute("service", "Edit");
            request.getRequestDispatcher("/manage/book/toc/volume-detail.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Saved changes!");
            //request.getRequestDispatcher("/manage/book/toc/view-toc.jsp").forward(request, response);
            response.sendRedirect("./TOC?id=" + bookId + "&vid=" + id);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
