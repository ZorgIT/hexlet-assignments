package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Category;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.ref.Reference;

// BEGIN
@Mapper(
        uses = {JsonNullableMapper.class, Reference.class},
        nullValuePropertyMappingStrategy =
                NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "categoryId", source = "category.id")
    public abstract ProductDTO map(Product product);

    @Mapping(target = "category", ignore = true)
    public abstract Product map(ProductCreateDTO productCreateDTO);

    @AfterMapping  // Метод выполнится после основного маппинга
    protected void setCategoryFromId(ProductCreateDTO productCreateDTO, @MappingTarget Product product) {
        if (productCreateDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productCreateDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category with id " + productCreateDTO.getCategoryId() + " not found"
                    ));
            product.setCategory(category);  // Устанавливаем найденную категорию
        }
    }

    @Mapping(target = "category", ignore = true)
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

    @AfterMapping
    protected void updateCategory(ProductUpdateDTO dto, @MappingTarget Product product) {
        if (dto.getCategoryId() != null && dto.getCategoryId().isPresent()) {
            Long categoryId = dto.getCategoryId().get();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category with id " + categoryId + " not found"
                    ));
            product.setCategory(category);
        }
    }

}
// END
