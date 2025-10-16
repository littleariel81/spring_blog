package uon.spring_blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uon.spring_blog.domain.Article;
import uon.spring_blog.domain.User;
import uon.spring_blog.dto.AddArticleRequest;
import uon.spring_blog.dto.UpdateArticleRequest;
import uon.spring_blog.repository.BlogRepository;
import uon.spring_blog.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public Article save(AddArticleRequest request, String userName) {
        String author = userRepository.findByEmail(userName)
                .map(User::getNickname)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user "+userName));

        return blogRepository.save(request.toEntity(author));
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("not found : " + id)
                );
    }

    public void delete(Long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        authorizeArticleAuthor(article.getAuthor());
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("not found : " + id)
                );
        authorizeArticleAuthor(article.getAuthor());
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    private void authorizeArticleAuthor(String author) {
        String saveName = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .map(User::getNickname)
                .orElseThrow(()->new IllegalArgumentException("not found :" + author));
        if (!author.equals(saveName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
