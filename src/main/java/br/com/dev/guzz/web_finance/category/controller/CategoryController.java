package br.com.dev.guzz.web_finance.category.controller;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.useCase.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CreateCategory createCategory;

    @Autowired
    private GetCategory getCategory;

    @Autowired
    private UpdateCategory updateCategory;

    @Autowired
    private DeleteCategory deleteCategory;

    @Autowired
    private InactivateCategory inactivateCategory;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO){
        try {
            CategoryDTO category = createCategory.invoke(categoryDTO);
            return ResponseEntity.status(201).body(category);
        } catch (Exception e){
            log.warn("=== Error while creating category. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id){
        try {
            Category category = getCategory.invoke(id);
            return ResponseEntity.status(200).body(category);
        } catch (Exception e){
            log.warn("=== Error while finding category. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO){
        try {
            CategoryDTO category = updateCategory.invoke(categoryDTO);
            return ResponseEntity.status(200).body(category);
        } catch (Exception e){
            log.warn("=== Error while updating category. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestHeader Long id){
        try {
            deleteCategory.invoke(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e){
            log.warn("=== Error while deleting category. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/inactivate")
    public ResponseEntity<?> inactivate(@RequestHeader Long id){
        try {
            inactivateCategory.invoke(id);
            return ResponseEntity.status(200).build();
        } catch(Exception e){
            log.warn("=== Error while inactivating category. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
