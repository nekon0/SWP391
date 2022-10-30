/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.payment;

import Model.auth.User;
import Model.payment.Transaction;
import Model.product.Book;
import Model.product.Product;
import context.payment.PaymentAccountDAO;
import context.payment.TransactionDAO;
import context.product.BookOwnDAO;
import context.product.ProductDAO;
import context.product.ProductOwnDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Silver_000
 */
@WebServlet(name="PurchaseChapterController", urlPatterns={"/User/PurchaseChapter"})
public class PurchaseChapterController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PaymentAccountDAO payAccDAO = new PaymentAccountDAO();
        TransactionDAO transDAO = new TransactionDAO();

        float amount = Float.parseFloat(request.getParameter("amount"));

        User user = (User) request.getSession().getAttribute("user");
        
        if (amount > user.getPaymentAccount().getBalance()) {
            response.sendRedirect(request.getContextPath() + "/BookDetail?id=" + request.getParameter("bookId"));
        } else {
            float walletBalance = user.getPaymentAccount().getBalance() - amount;
            user.getPaymentAccount().setBalance(walletBalance);

            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setAmount(amount);
            transaction.setBalanceAfter(walletBalance);
            transaction.setTransactionTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            transaction.setType(3);
            transaction.setStatus(3);

            ProductDAO productDAO = new ProductDAO();
            Product product = new Product(request.getParameter("productId"));
            product = productDAO.get(product);

            transaction.setProduct(product);
            transaction.setDescription("Buy " + product.getBook().getTitle() + ".");
            transDAO.insert(transaction);

            payAccDAO.update(user.getPaymentAccount());
            User author = product.getBook().getAuthor().getUser();
            if (author != null) {
                Transaction auTransaction = new Transaction();
                auTransaction.setUser(author);
                auTransaction.setAmount(author.getPaymentAccount().getBalance() + amount);
                auTransaction.setBalanceAfter(walletBalance);
                auTransaction.setTransactionTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                auTransaction.setType(4);
                auTransaction.setStatus(3);
                auTransaction.setProduct(product);
                auTransaction.setDescription("Sell " + product.toString() + ".");
                transDAO.insert(auTransaction);

                author.getPaymentAccount().setBalance(author.getPaymentAccount().getBalance() + amount);
                payAccDAO.update(author.getPaymentAccount());
            }

            BookOwnDAO bookOwnDAO = new BookOwnDAO();
            bookOwnDAO.insert(product.getBook(), user);
            ProductOwnDAO productOwnDAO = new ProductOwnDAO();
            productOwnDAO.insert(product, user);

            response.sendRedirect(request.getContextPath() + "/BookDetail?id=" + product.getBook().getId());
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
