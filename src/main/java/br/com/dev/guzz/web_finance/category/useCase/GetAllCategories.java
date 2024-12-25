package br.com.dev.guzz.web_finance.category.useCase;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.repository.CategoryRepository;
import br.com.dev.guzz.web_finance.category.util.BuilderCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCategories {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private BuilderCategory builderCategory;

    public List<CategoryDTO> invoke(){
        List<Category> categories = repository.findAllByActive(true);

        return categories.stream()
                .map(category -> this.builderCategory.toDTO(category))
                .toList();
    }
}
