package controller;

import dao.BookDAO;
import dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookDAO.getBookById(id);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO dto) {
        return bookDAO.createBook(dto);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        return bookDAO.updateBook(id, dto);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long id) {
        bookDAO.deleteBook(id);
    }
}
