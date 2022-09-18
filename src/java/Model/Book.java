/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.CategoryDAO;


public class Book {
    private int id;
    private String title, author;
    private int categoryid;
    private int favourite;
    private float price;
    private boolean issale;
    private String image;
    private String description;
    private int views;


    public Book(int id, String title, String author, int categoryid, int favourite, float price, boolean issale, String image, String description, int views) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryid = categoryid;
        this.favourite = favourite;
        this.price = price;
        this.issale = issale;
        this.image = image;
        this.description = description;
        this.views = views;
    }

    public Book(int id, String title, String author, int type, int favourite, float price, boolean issale, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryid=type;
        this.favourite = favourite;
        this.price = price;
        this.issale = issale;
        this.image = image;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean issale() {
        return issale;
    }

    public void setIssale(boolean issale) {
        this.issale = issale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getCategory(){
        CategoryDAO dao =new CategoryDAO();
        return (dao.getCategory(this.categoryid)==null?"Uncategorized":dao.getCategory(this.categoryid));
    }
}
