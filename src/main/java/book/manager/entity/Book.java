package book.manager.entity;

import lombok.Data;

@Data
public class Book {
    private int bid;
    private String title;
    private String description;
    private float price;
    private String author;
}
