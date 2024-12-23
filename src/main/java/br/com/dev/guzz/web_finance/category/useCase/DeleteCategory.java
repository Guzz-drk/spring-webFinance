package br.com.dev.guzz.web_finance.category.useCase;

import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.repository.CategoryRepository;
import br.com.dev.guzz.web_finance.category.util.ValidateCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategory {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ValidateCategory validateCategory;

    public void invoke(Long id) throws Exception {
        Category category = validateCategory.invoke(id);
        repository.delete(category);
    }
}
