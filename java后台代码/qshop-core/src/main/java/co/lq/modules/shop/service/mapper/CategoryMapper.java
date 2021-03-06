package co.lq.modules.shop.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import co.lq.mapper.EntityMapper;
import co.lq.modules.shop.domain.Category;
import co.lq.modules.shop.service.dto.CategoryDTO;

/**
 * @author billy
 * @date 2019-10-03
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

}
