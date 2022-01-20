package com.azzouz.bsapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.azzouz.bsapi.entities.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}