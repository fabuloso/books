package actions;

import domain.Book;
import domain.BookRepository;
import infrastructure.InMemoryBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BooksActionHandler {

    static final Logger LOG = LoggerFactory.getLogger(BooksActionHandler.class);

    BookRepository repository;

    public BooksActionHandler() {
        this(new InMemoryBookRepository());
    }

    public BooksActionHandler(BookRepository repository) {
        this.repository = repository;
    }

    public void handle(AddBook action) {
        LOG.info("Handle command: {}", action);
        repository.save(action.getBook());
    }

    public void handle(RateBook action) {
        LOG.info("Handle command: {}", action);
        Book book = repository.find(action.getBookId());
        book.rate(action.getRating());
    }
}