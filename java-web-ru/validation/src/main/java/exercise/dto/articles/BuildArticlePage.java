package exercise.dto.articles;

import exercise.model.Page;
import io.javalin.validation.ValidationError;
import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
public class BuildArticlePage extends Page {
    private String articleTitle; // Заголовок статьи
    private String content; // Содержимое статьи
    private Map<String, List<ValidationError<Object>>> errors; // Ошибки валидации

    public BuildArticlePage(String articleTitle, String pageTitle, String content,
                            Map<String, List<ValidationError<Object>>> errors) {
        super(pageTitle); // Заголовок страницы
        this.articleTitle = articleTitle; // Заголовок статьи
        this.content = content;
        this.errors = errors;
    }

    public BuildArticlePage(String pageTitle) {
        super(pageTitle); // Заголовок страницы
    }
}
// END
