package solis3d.u5w2d2.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import solis3d.u5w2d2.entities.BlogPost;
import solis3d.u5w2d2.payloads.NewBlogPostPayload;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogPostsService {
    private List<BlogPost> blogPostsDB = new ArrayList<>();

    public List<BlogPost> findAll() {
        return this.blogPostsDB;
    }

    public BlogPost saveBlogPost(NewBlogPostPayload body) {
        BlogPost newBlogpost = new BlogPost(body.getCategoria(), body.getTitolo(), body.getContenuto(), body.getTempoDiLettura());
        this.blogPostsDB.add(newBlogpost);
        log.info("Il blog con id " + newBlogpost.getId() + " salvato correttamente!");
        return newBlogpost;
    }

    public BlogPost findById(long blogPostId) {
        BlogPost trovato = null;

        for (BlogPost blogPost : this.blogPostsDB) {
            if (blogPost.getId() == blogPostId) {trovato = blogPost;}
        }

        if (trovato == null) {throw new EntityNotFoundException("BlogPost con id " + blogPostId + " non trovato!");}
        return trovato;
    }

    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload body) {
        BlogPost trovato = null;

        for (BlogPost blogPost : this.blogPostsDB) {
            if(blogPost.getId() == blogPostId){
                trovato = blogPost;
                trovato.setCategoria(body.getCategoria());
                trovato.setTitolo(body.getTitolo());
                trovato.setContenuto(body.getContenuto());
                trovato.setTempoDiLettura(body.getTempoDiLettura());
            }
        }
        if (trovato == null) {throw new EntityNotFoundException("BlogPost con id " + blogPostId + " non trovato!");}
        return trovato;
    }

    public void findByIdAndDelete(long blogPostId){
        BlogPost trovato = null;

        for (BlogPost blogPost : this.blogPostsDB) {
            if(blogPost.getId() == blogPostId){trovato = blogPost;}
        }

        if(trovato == null) throw new EntityNotFoundException("BlogPost con id " + blogPostId + " non trovato!");
        this.blogPostsDB.remove(trovato);
    }
}
