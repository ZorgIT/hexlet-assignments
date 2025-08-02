package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> index() {
        var autorhs = authorService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(autorhs.size()))
                .body(autorhs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> show(@PathVariable long id) {
        var author = authorService.findById(id);
        return author != null ?
                ResponseEntity.ok().body(author) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCreateDTO authorCreateDTO) {
        var author = authorService.create(authorCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> update(@RequestBody @Valid AuthorUpdateDTO authorUpdateDTO,
                                            @PathVariable long id) {
        var productDto = authorService.update(authorUpdateDTO, id);
        return ResponseEntity.ok().body(productDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // END
}
