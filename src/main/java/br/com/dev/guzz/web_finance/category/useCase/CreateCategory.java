package br.com.dev.guzz.web_finance.category.useCase;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.repository.CategoryRepository;
import br.com.dev.guzz.web_finance.category.util.BuilderCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategory {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private BuilderCategory builderCategory;

    public CategoryDTO invoke(CategoryDTO categoryDTO){
        Category category = builderCategory.toEntity(categoryDTO);
        repository.save(category);

        return builderCategory.toDTO(category);
    }
}
