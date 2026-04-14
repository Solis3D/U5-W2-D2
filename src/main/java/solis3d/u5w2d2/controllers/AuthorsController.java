package solis3d.u5w2d2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solis3d.u5w2d2.entities.Author;
import solis3d.u5w2d2.payloads.NewAuthorPayload;
import solis3d.u5w2d2.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody NewAuthorPayload body) {
        return this.authorService.saveAuthor(body);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable("authorId") long authorId) {
        return this.authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable long authorId, @RequestBody NewAuthorPayload body) {
        return this.authorService.findByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId]")
    public void deleteById(@PathVariable long authorId) {
        this.authorService.findByIdAndDelete(authorId);
    }
}
