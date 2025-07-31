package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.*;

import java.lang.ref.Reference;

// BEGIN
@Mapper(
        uses = {JsonNullableMapper.class, Reference.class},
        nullValuePropertyMappingStrategy =
                NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CategoryMapper {
//    @Mapping()
    public abstract Category map(CategoryDTO dto);

//    @Mapping()
    public abstract CategoryDTO map(Category model);

//    @Mapping()
    public abstract Category map(CategoryCreateDTO dto);

}
// END
