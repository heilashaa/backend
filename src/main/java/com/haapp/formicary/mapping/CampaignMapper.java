package com.haapp.formicary.mapping;

import com.haapp.formicary.api.model.CampaignDto;
import com.haapp.formicary.domain.model.Campaign;
import com.haapp.formicary.persistence.model.CampaignEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {CategoryMapper.class})
public interface CampaignMapper {

    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    CampaignDto campaignDomainToCampaignDto(Campaign campaignDomain);

    Campaign campaignDtoToCampaignDomain(CampaignDto campaignDto);

    CampaignEntity campaignDomainToCampaignPersistence(Campaign campaignDomain);

    Campaign campaignPersistenceToCampaignDomain(CampaignEntity campaignPersistence);

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
