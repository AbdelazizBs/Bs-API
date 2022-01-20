package com.azzouz.bsapi.controllers;

import com.azzouz.bsapi.entities.Article;
import com.azzouz.bsapi.repositories.ArticleRepository;
import com.azzouz.bsapi.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/articles"})
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ProviderRepository providerRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ProviderRepository
            providerRepository) {
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
    }

    /*@Autowired
     private ArticleRepository articleRepository;
     @Autowired
     private ProviderRepository providerRepository;*/
    @GetMapping("/list")
    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    @PostMapping("/add/{providerId}")
    Article createArticle(@PathVariable(value = "providerId") Long providerId,
                          @Valid @RequestBody Article article) {
        return providerRepository.findById(providerId).map(provider -> {
            article.setProvider(provider);
            return articleRepository.save(article);
        }).orElseThrow(() -> new IllegalArgumentException("ProviderId " + providerId + "not found"));
    }

    @PutMapping("/update/{providerId}/{articleId}")
    public Article updateArticle(@PathVariable(value = "providerId") Long providerId,
                                 @PathVariable(value = "articleId") Long articleId,
                                 @Valid @RequestBody Article articleRequest) {
        if (!providerRepository.existsById(providerId)) {
            throw new IllegalArgumentException("ProviderId " + providerId + " notfound");
        }
        return articleRepository.findById(articleId).map(article -> {
            article.setPrice(articleRequest.getPrice());
            article.setLabel(articleRequest.getLabel());
            article.setPicture(articleRequest.getPicture());
            return articleRepository.save(article);
        }).orElseThrow(() -> new IllegalArgumentException("ArticleId " + articleId + "notfound"));
    }

    @DeleteMapping("/delete/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "articleId") Long
                                                   articleId) {
        return articleRepository.findById(articleId).map(article -> {
            articleRepository.delete(article);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Article not found with id " + articleId));
    }
}
