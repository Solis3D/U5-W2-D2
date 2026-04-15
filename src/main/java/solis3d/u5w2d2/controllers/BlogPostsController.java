package solis3d.u5w2d2.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solis3d.u5w2d2.entities.BlogPost;
import solis3d.u5w2d2.payloads.NewBlogPostPayload;
import solis3d.u5w2d2.services.BlogPostsService;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
    private final BlogPostsService blogPostsService;

    public BlogPostsController(BlogPostsService blogPostsService) {
        this.blogPostsService = blogPostsService;
    }

    @GetMapping
    public Page<BlogPost> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "titolo") String sortBy
    ) {
        return this.blogPostsService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody NewBlogPostPayload body) {
        return this.blogPostsService.saveBlogPost(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable long blogPostId) {
        return this.blogPostsService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPost findByIdAndUpdate(@PathVariable long blogPostId, @RequestBody NewBlogPostPayload body) {
        return this.blogPostsService.findByIdAndUpdate(blogPostId, body);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long blogPostId) {
        this.blogPostsService.findByIdAndDelete(blogPostId);
    }

}
