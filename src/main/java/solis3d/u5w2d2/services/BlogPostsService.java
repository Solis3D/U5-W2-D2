package solis3d.u5w2d2.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import solis3d.u5w2d2.entities.Author;
import solis3d.u5w2d2.entities.BlogPost;
import solis3d.u5w2d2.exceptions.NotFoundException;
import solis3d.u5w2d2.payloads.NewBlogPostPayload;
import solis3d.u5w2d2.repositories.AuthorsRepository;
import solis3d.u5w2d2.repositories.BlogPostsRepository;
import org.springframework.data.domain.Pageable;

@Service
@Slf4j
public class BlogPostsService {

    private final BlogPostsRepository blogPostsRepository;
    private final AuthorsRepository authorsRepository;

    public BlogPostsService(BlogPostsRepository blogPostsRepository,AuthorsRepository authorsRepository) {
        this.blogPostsRepository = blogPostsRepository;
        this.authorsRepository = authorsRepository;
    }

    public BlogPost saveBlogPost(NewBlogPostPayload body) {

        Author author = this.authorsRepository.findById(body.getAuthorId()).orElseThrow(() -> new NotFoundException(body.getAuthorId()));

        BlogPost blogPost = new BlogPost(body.getCategoria(), body.getTitolo(), body.getContenuto(), body.getTempoDiLettura(),  author);

        BlogPost savedBlogPost = blogPostsRepository.save(blogPost);

        log.info("Il blog con id " + savedBlogPost.getId() + " salvato correttamente!");
        return savedBlogPost;
    }

    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 1) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostsRepository.findAll(pageable);
    }

    public BlogPost findById(long blogPostId) {
      return  blogPostsRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload body) {
        BlogPost trovato = this.findById(blogPostId);

        Author author = this.authorsRepository.findById(body.getAuthorId()).orElseThrow(() -> new NotFoundException(body.getAuthorId()));

        trovato.setCategoria(body.getCategoria());
        trovato.setTitolo(body.getTitolo());
        trovato.setContenuto(body.getContenuto());
        trovato.setTempoDiLettura(body.getTempoDiLettura());
        trovato.setAuthor(author);

        BlogPost updatedBlogPost = blogPostsRepository.save(trovato);
        log.info("Blog post con id " + updatedBlogPost.getId() + " aggiornato correttamente!");

        return updatedBlogPost;
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = this.findById(blogPostId);
        this.blogPostsRepository.delete(found);
    }
}
