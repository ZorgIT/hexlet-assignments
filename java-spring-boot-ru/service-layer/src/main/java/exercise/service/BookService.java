package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        var result = books.stream()
                .map(bookMapper::map)
                .toList();
        return result;

    }

    public BookDTO findById(Long id) {
        var author = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var bookDTO = bookMapper.map(author);
        return bookDTO;
    }

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        if (bookCreateDTO.getAuthorId() != null && !authorRepository.existsById(bookCreateDTO.getAuthorId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Author not found with ID: " + bookCreateDTO.getAuthorId());
        }
        var book = bookMapper.map(bookCreateDTO);
        var bookDto = bookRepository.save(book);
        return bookMapper.map(bookDto);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        if (bookData.getAuthorId() != null && !authorRepository.existsById(bookData.getAuthorId().get())) {
            throw new ResourceNotFoundException("Author not found with ID: "
                    + bookData.getAuthorId());
        }
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
