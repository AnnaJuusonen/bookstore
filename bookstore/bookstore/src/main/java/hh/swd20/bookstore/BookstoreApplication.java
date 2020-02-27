package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save few books");
			crepository.save(new Category("Fantasiakirjat"));
			crepository.save(new Category("Tietokirjat"));

			repository.save(new Book("Harry Potter ja viisasten kivi", "JK Rowling", 2001, "1224434", 30.00,
					crepository.findByName("Fantasiakirjat").get(0)));
			repository.save(new Book("Harry Potter ja salainen kammio", "JK Rowling", 2002, "134234234", 35.90,
					crepository.findByName("Fantasiakirjat").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
