package br.com.dev.guzz.web_finance.transaction.util;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.category.util.BuilderCategory;
import br.com.dev.guzz.web_finance.category.util.ValidateCategory;
import br.com.dev.guzz.web_finance.transaction.dto.TransactionDTO;
import br.com.dev.guzz.web_finance.transaction.entity.Transaction;
import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.util.BuilderUser;
import br.com.dev.guzz.web_finance.user.util.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuilderTransaction {

    @Autowired
    private ValidateCategory validateCategory;

    @Autowired
    private ValidateUser validateUser;

    @Autowired
    private BuilderUser builderUser;

    @Autowired
    private BuilderCategory builderCategory;

    public Transaction toEntity(TransactionDTO transactionDTO) throws Exception {
        return Transaction.builder()
                .description(transactionDTO.getDescription())
                .type(transactionDTO.getType())
                .category(findCategory(transactionDTO.getCategory()))
                .user(findUser(transactionDTO.getUser()))
                .price(transactionDTO.getPrice())
                .active(true)
                .build();
    }

    public TransactionDTO toDTO(Transaction transaction){
        return TransactionDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .type(transaction.getType())
                .category(buildCategory(transaction.getCategory()))
                .user(buildUser(transaction.getUser()))
                .active(transaction.isActive())
                .price(transaction.getPrice())
                .build();
    }

    private Category findCategory(CategoryDTO categoryDTO) throws Exception {
        if(categoryDTO == null || categoryDTO.getId() == null)
            throw new Exception("Category cannot be null");

        return validateCategory.invoke(categoryDTO.getId());
    }

    private User findUser(UserDTO userDTO) throws Exception {
        if(userDTO == null || userDTO.getId() == null)
            throw new Exception("User cannot be null");

        return validateUser.invoke(userDTO.getId());
    }

    private CategoryDTO buildCategory(Category category){
        return builderCategory.toDTO(category);
    }

    private UserDTO buildUser(User user){
        return builderUser.toDTO(user);
    }
}
