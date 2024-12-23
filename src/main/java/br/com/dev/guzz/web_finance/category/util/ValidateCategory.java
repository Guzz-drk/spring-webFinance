package br.com.dev.guzz.web_finance.category.util;

import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateCategory {

    @Autowired
    private CategoryRepository repository;

    public Category invoke(Long id) throws Exception {
        Optional<Category> optionalCategory = getCategoryById(id);

        if(optionalCategory.isPresent())
            return optionalCategory.get();

        throw new Exception("Category Not Found");
    }

    public Optional<Category> getCategoryById(Long id){
        return repository.findByIdAndActive(id, true);
    }
}
