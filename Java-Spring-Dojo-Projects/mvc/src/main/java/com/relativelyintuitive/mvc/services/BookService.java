package com.relativelyintuitive.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.relativelyintuitive.mvc.models.Book;
import com.relativelyintuitive.mvc.repositories.BookRepository;

@Service
public class BookService {
    // adding the book repository as a dependency
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }

    // retrieves a book if found by id
    public Book findBook(Long id) {
    	Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    // update a book
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
    	Optional<Book> optionalBook = bookRepository.findById(id);
    	if(optionalBook.isPresent()) {
    		Book toPut = bookRepository.findById(id).get();
    		toPut.setId(id);
    		toPut.setTitle(title);
    		toPut.setDescription(desc);
    		toPut.setLanguage(lang);
    		toPut.setNumberOfPages(numOfPages);
    		bookRepository.save(toPut);
    		return toPut;
    	} else {
    		return null;
    	}
    }
    
    public Book updateBook(Book book) {
    	return bookRepository.save(book);
    }
    
    // delete a book
    public void deleteBook(Long id) {
    	Optional<Book> optionalBook = bookRepository.findById(id);
    	if(optionalBook.isPresent())
    		bookRepository.deleteById(id);
    }
    
}