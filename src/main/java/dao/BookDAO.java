package dao;

import dto.BookDTO;
import entity.Book;
import mapper.BookMapper;
import org.springframework.stereotype.Component;
import repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDAO {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDAO(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return bookMapper.toDto(book);
        }
        return null;
    }

    public BookDTO createBook(BookDTO dto) {
        Book book = bookMapper.toEntity(dto);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    public BookDTO updateBook(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book = bookRepository.save(book);
            return bookMapper.toDto(book);
        }
        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
