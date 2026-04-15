package cristiancicale.G3S2U5.controllers;

import cristiancicale.G3S2U5.services.BlogPostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }
}