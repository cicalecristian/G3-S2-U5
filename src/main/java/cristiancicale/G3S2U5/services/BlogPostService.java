package cristiancicale.G3S2U5.services;

import cristiancicale.G3S2U5.repositories.BlogPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }
}
