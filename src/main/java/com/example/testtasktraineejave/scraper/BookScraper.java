package com.example.testtasktraineejave.scraper;

import com.example.testtasktraineejave.entity.Book;
import com.example.testtasktraineejave.service.BookService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookScraper {

    private final BookService bookService;

    private static final String BASE_URL = "https://books.toscrape.com/catalogue/page-";

    public List<Book> scrapeBooks(String BASE_URL, int pageNumber) throws IOException {
        List<Book> books = new ArrayList<>();
        String paginatedUrl = BASE_URL + pageNumber + ".html";
        Document doc = Jsoup.connect(paginatedUrl).get();
        Elements bookElements = doc.select(".product_pod");

        for (Element element : bookElements) {
            Book book = new Book();
            book.setTitle(element.select("h3 > a").attr("title"));


            String priceText = element.select(".price_color").text().replace("£", "");
            book.setPrice(new BigDecimal(priceText));

            String availabilityText = element.select(".availability").text().trim();
            book.setAvailability(availabilityText.contains("In stock"));

            book.setRating(parseRating(element));

            books.add(book);
        }
        return books;
    }

    private Integer parseRating(Element element) {
        String ratingClass = element.select(".star-rating").attr("class");
        if (ratingClass.contains("One")) return 1;
        if (ratingClass.contains("Two")) return 2;
        if (ratingClass.contains("Three")) return 3;
        if (ratingClass.contains("Four")) return 4;
        if (ratingClass.contains("Five")) return 5;
        return 0;
    }

    public void scrapeBooksFromMultiplePages(int totalPages) throws IOException {
        for (int page = 1; page <= totalPages; page++) {
            List<Book> books = scrapeBooks(BASE_URL, page);
            bookService.saveBooks(books);
        }
    }
}
