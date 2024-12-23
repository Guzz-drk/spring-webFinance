package br.com.dev.guzz.web_finance.category.util;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class BuilderCategory {

    public Category toEntity(CategoryDTO categoryDTO){
        return Category.builder()
                .name(categoryDTO.getName())
                .active(true)
                .build();
    }

    public CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .active(category.isActive())
                .build();
    }
}
