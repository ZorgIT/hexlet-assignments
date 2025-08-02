package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;


    // BEGIN

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        var books = bookService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(books.size()))
                .body(books);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> show(@PathVariable("id") long id) {
        var book = bookService.findById(id);
        return book != null ?
                ResponseEntity.ok().body(book) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookCreateDTO book) {
        var bookCreated = bookService.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCreated);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") long id, @RequestBody @Valid BookUpdateDTO book) {
        var bookUpdated = bookService.update(book, id);
        return ResponseEntity.ok().body(bookUpdated);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BookDTO> delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }


    /*
GET /books – просмотр списка всех книг
GET /books/{id} – просмотр конкретной книги
POST /books – добавление новой книги. При указании id несуществующего автора должен вернуться ответ с кодом 400 Bad request
PUT /books/{id} – редактирование книги. При редактировании мы должны иметь возможность поменять название и автора. При указании id несуществующего автора также должен вернуться ответ с кодом 400 Bad request
DELETE /books/{id} – удаление книги
     */
    // END
}
