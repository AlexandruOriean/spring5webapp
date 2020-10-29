package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println("Started in Bootstrap");

        Publisher humanitas = new Publisher();
        humanitas.setName("Humanitas");
        humanitas.setCity("Bucharest");
        humanitas.setAddress("Piata Romana");

        publisherRepository.save(humanitas);

        Author mEliade = new Author("Mircea", "Eliade");
        Book coincidentiaOppositorum = new Book("Coincidentia oppositorum", "123456789");
        mEliade.getBooks().add(coincidentiaOppositorum);
        coincidentiaOppositorum.getAuthors().add(mEliade);
        coincidentiaOppositorum.setPublisher(humanitas);
        humanitas.getBooks().add(coincidentiaOppositorum);

        authorRepository.save(mEliade);
        bookRepository.save(coincidentiaOppositorum);
        publisherRepository.save(humanitas);

        Author eLovinescu = new Author("Eugen", "Lovinescu");
        Book lulu = new Book("Lulu", "34567800");
        eLovinescu.getBooks().add(lulu);
        lulu.getAuthors().add(eLovinescu);
        lulu.setPublisher(humanitas);
        humanitas.getBooks().add(lulu);

        authorRepository.save(eLovinescu);
        bookRepository.save(lulu);
        publisherRepository.save(humanitas);

    }

}
