/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.product.Author;
import Model.product.Book;
import context.product.AuthorDAO;
import context.product.BookDAO;
import context.product.CategoryDAO;
import java.util.ArrayList;


/* @author ACER */
public class Test {

    public static void main(String[] args) {
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> books = bd.getAllBook();

        String all = "Art\n"
                + "Biography\n"
                + "Business\n"
                + "Chick Lit\n"
                + "Children's\n"
                + "Christian\n"
                + "Classics\n"
                + "Comics\n"
                + "Contemporary\n"
                + "Cookbooks\n"
                + "Crime\n"
                + "Fantasy\n"
                + "Fiction\n"
                + "Gay and Lesbian\n"
                + "Graphic Novels\n"
                + "Historical Fiction\n"
                + "History\n"
                + "Horror\n"
                + "Humor and Comedy\n"
                + "Manga\n"
                + "Memoir\n"
                + "Music\n"
                + "Mystery\n"
                + "Nonfiction\n"
                + "Paranormal\n"
                + "Philosophy\n"
                + "Poetry\n"
                + "Psychology\n"
                + "Religion\n"
                + "Romance\n"
                + "Science\n"
                + "Science Fiction\n"
                + "Self Help\n"
                + "Suspense\n"
                + "Spirituality\n"
                + "Sports\n"
                + "Thriller\n"
                + "Travel\n"
                + "Young Adult";
        String[] array = all.split("\n");
//        for(int i=0;i<array.length;i++){
//            cd.addCategory(array[i]);
//        }
        Author au = new Author();
        au.setName(all);
        AuthorDAO ad = new AuthorDAO();
//        ArrayList<User> userList = ud.getAllUsers();
//        userList.forEach((t) -> {
//            Author author = new Author();
//            author.setUserId(t.getId());
//            author.setAuthorName(t.getName());
//            dao.add(author);
//        });
    }
}
