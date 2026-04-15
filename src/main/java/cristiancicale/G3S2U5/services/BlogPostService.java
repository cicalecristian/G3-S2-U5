package cristiancicale.G3S2U5.services;

import cristiancicale.G3S2U5.entities.BlogPost;
import cristiancicale.G3S2U5.exceptions.NotFoundException;
import cristiancicale.G3S2U5.payloads.NewBlogPostPayload;
import cristiancicale.G3S2U5.repositories.BlogPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost save(NewBlogPostPayload body) {

        BlogPost newBlogPost = new BlogPost(body.getTitolo(), body.getCategoria(), body.getContenuto(), body.getTempoDiLettura());
        BlogPost savedBlogPost = this.blogPostRepository.save(newBlogPost);

        log.info("Il post con id " + savedBlogPost.getId() + "è stato salvato correttamente");

        return savedBlogPost;
    }

    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 10) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostRepository.findAll(pageable);
    }

    public BlogPost findById(long blogPostId) {
        return this.blogPostRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload body) {
        BlogPost found = this.findById(blogPostId);

        found.setTitolo(body.getTitolo());
        found.setCategoria(body.getCategoria());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());

        BlogPost updateBlogPost = this.blogPostRepository.save(found);

        log.info("Il post " + updateBlogPost.getId() + "è stato salvato correttamente");

        return updateBlogPost;
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = this.findById(blogPostId);
        this.blogPostRepository.delete(found);
    }
}
