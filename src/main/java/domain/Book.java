package domain;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private Integer id;
    private Integer rate;
    private List<Event> events;

    private EventStore eventStore = new EventStore();

    public Book() {
        this.rate = 0;
        this.events = new ArrayList<>();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void rate(Rating rating) {
        if (rating.hasCorrectValue()) {
            applyNewEvent(new BookRated(id, rating.getRate(), rating.getDescription(), rating.getUserId()));
        }
    }

    private void applyNewEvent(BookRated event) {
        applyEvent(event);
        eventStore.save(event);
    }

    private void applyEvent(BookRated event) {
        events.add(event);
        updateRatingWith(event.getRate());
    }

    public Boolean hasId(Integer bookId) {
        return id.compareTo(bookId) == 0;
    }

    private void updateRatingWith(Integer rate) {
        this.rate += rate;
    }
}
