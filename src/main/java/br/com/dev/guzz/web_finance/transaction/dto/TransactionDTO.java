package br.com.dev.guzz.web_finance.transaction.dto;

import br.com.dev.guzz.web_finance.category.dto.CategoryDTO;
import br.com.dev.guzz.web_finance.category.entity.Category;
import br.com.dev.guzz.web_finance.transaction.enums.TransactionType;
import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionDTO {

    private Long id;

    private String description;

    private TransactionType type;

    private CategoryDTO category;

    private UserDTO user;

    private BigDecimal price;

    private boolean active;
}
