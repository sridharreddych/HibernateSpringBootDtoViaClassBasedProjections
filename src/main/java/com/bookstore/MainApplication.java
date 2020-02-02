package com.bookstore;
 
import com.bookstore.projection.AuthorNameAge;
import java.util.List;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            List<AuthorNameAge> authors = bookstoreService.fetchFirst2ByBirthplace();

            System.out.println("Number of authors:" + authors.size());

            for (AuthorNameAge author : authors) {
                System.out.println("Author name: " + author.getName() 
                        + " | Age: " + author.getAge());
            }
        };
    }
}

/*
 * 
 * DTO Via Spring Data Class-Based Projections

Description: Fetch only the needed data from the database via Spring Data Projections (DTO). In this case, via class-based projections.

Key points:

write an class (projection) containing a constructor, getters, setters, equals() and hashCode() only for the columns that should be fetched from the database
write the proper query returning a List<projection>
if it is applicable, limit the number of returned rows (e.g., via LIMIT)
in this example, we can use query builder mechanism built into Spring Data repository infrastructure
Note: Using projections is not limited to use query builder mechanism built into Spring Data repository infrastructure. We can fetch projections via JPQL or native queries as well. For example, in this application we use a JPQL.

Output example (select first 2 rows; select only "name" and "age"):
 */

