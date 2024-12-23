package br.com.dev.guzz.web_finance.category.useCase;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.repository.CategoryRepository;
import br.com.dev.guzz.web_finance.category.util.BuilderCategory;
import br.com.dev.guzz.web_finance.category.util.ValidateCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategory {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ValidateCategory validateCategory;

    @Autowired
    private BuilderCategory builderCategory;

    public CategoryDTO invoke(CategoryDTO categoryDTO) throws Exception {
        Category category = validateCategory.invoke(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        repository.saveAndFlush(category);
        return builderCategory.toDTO(category);
    }
}
