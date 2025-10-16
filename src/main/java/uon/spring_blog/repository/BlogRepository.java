package uon.spring_blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uon.spring_blog.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
