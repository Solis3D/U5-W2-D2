package solis3d.u5w2d2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solis3d.u5w2d2.entities.Author;
import solis3d.u5w2d2.exceptions.ValidationException;
import solis3d.u5w2d2.payloads.AuthorDTO;
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
    public Author createAuthor(@RequestBody @Validated AuthorDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errors = validationResult.getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();

            throw new ValidationException(errors);
        }

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

    @PatchMapping("/{authorId}/avatar")
    public Author uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable long authorId) {
        return this.authorService.uploadAvatar(file, authorId);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long authorId) {
        this.authorService.findByIdAndDelete(authorId);
    }
}
