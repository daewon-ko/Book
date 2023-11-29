package me.daewonko.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.daewonko.springbootdeveloper.domain.Article;
import me.daewonko.springbootdeveloper.dto.AddArticleRequest;
import me.daewonko.springbootdeveloper.dto.ArticleResponse;
import me.daewonko.springbootdeveloper.dto.UpdateArticleRequest;
import me.daewonko.springbootdeveloper.service.BlogService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // HTTP Response Body에 객체 데이터를 JSON형식으로 반환하는 컨트롤러
@RequiredArgsConstructor  // final이 붙거나 @NotNUll이 붙은 필드의 생성자를 추가
public class BlogApiController {
    private final BlogService blogService;

    // HTTP 메서드가 POST일때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    @ResponseStatus(value = HttpStatus.NOT_MODIFIED)
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findALl()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delteArticle(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updatedArticle);
    }
}
