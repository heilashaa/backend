package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.ArticleDto;
import com.haapp.formicary.domain.model.Article;
import com.haapp.formicary.persistence.model.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CampaignMapper.class})
public interface ArticleMapper {

        ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

        ArticleDto articleDomainToArticleDto(Article articleDomain);

        Article articleDtoToArticleDomain(ArticleDto articleDto);

        ArticleEntity articleDomainToArticlePersistence(Article articleDomain);

        Article articlePersistenceToArticleDomain(ArticleEntity articlePersistence);

//    DivisionDTO divisionToDivisionDTO(Division entity);
//    Division divisionDTOtoDivision(DivisionDTO dto);

//    Mappings({
//        @Mapping(target="employeeId", source = "entity.id"), @Mapping(target="employeeName", source = "entity.name"),
//        @Mapping(target="employeeStartDt", source = "entity.startDt", dateFormat = "dd-MM-yyyy HH:mm:ss")})
//    EmployeeDTO employeeToEmployeeDTO(Employee entity);
//    @Mappings({@Mapping(target="id", source="dto.employeeId"), @Mapping(target="name", source="dto.employeeName"),
//            @Mapping(target="startDt", source="dto.employeeStartDt", dateFormat="dd-MM-yyyy HH:mm:ss")})
//    Employee employeeDTOtoEmployee(EmployeeDTO dto);

//    EmployeeDTO temp = EmployeeMapper.INSTANCE.employeeToEmployeeDTO(entity);

//    @Mapper(componentModel = "spring", uses = {BookMapper.class})
//    public interface LibraryMapper extends IEntityMapper<LibraryDTO, Library> {

//    @Mapper
//    public interface ProductMapper {
//        ProductDTO toProductDTO(Product product);
//        List<ProductDTO> toProductDTOs(List<Product> products);
//        Product toProduct(ProductDTO productDTO);
}
