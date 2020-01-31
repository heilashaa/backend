//package com.haapp.formicary.api.controller;
//
//import com.haapp.formicary.api.message.CampaignRequest;
//import com.haapp.formicary.api.message.CampaignResponse;
//import com.haapp.formicary.api.message.CampaignsResponse;
//import com.haapp.formicary.persistence.model.Campaign;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.Authorization;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.swagger2.mappers.ModelMapper;
//
//import javax.validation.Valid;
//import java.util.stream.Collectors;
//
//import static com.haapp.formicary.config.Constant.Service.BASIC_AUTH;
//import static org.springframework.http.HttpStatus.CREATED;
//import static org.springframework.http.HttpStatus.OK;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@Api(value = "REST API for Campaign. API allows operations with campaign: create, read, update, delete.",
//        tags = "campaign", authorizations = {@Authorization(BASIC_AUTH)})
//@RestController
//@RequestMapping(value = "/api/campaign", produces = APPLICATION_JSON_VALUE)
//@AllArgsConstructor
//public class CampaignController {
//
//    private CampaignService customerService;
//    private ModelMapper modelMapper;
//
//
//    @ApiOperation(value = "${customers.operation.create-customer}",
//            nickname = "createCustomer",
//            authorizations = {@Authorization(BASIC_AUTH)})
//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    @ResponseStatus(CREATED)
//    public CampaignResponse createCustomer(
//            @ApiParam(value = "${customers.param.customer}")
//            @RequestBody @Valid CampaignRequest request) {
//        var customer = modelMapper.map(request.getCampaign(), Campaign.class);
//        customer = customerService.create(customer);
//        var apiCustomer = modelMapper.map(customer, com.haapp.formicary.api.model.Campaign.class);
//        return new CampaignResponse(apiCustomer);
//    }
//
//    @ApiOperation(value = "${customers.operation.get-customer}",
//            nickname = "getCustomer",
//            authorizations = {@Authorization(BASIC_AUTH)})
//    @GetMapping("/{id}")
//    @ResponseStatus(OK)
//    public CampaignResponse getCustomer(
//            @ApiParam(value = "${customers.param.customer-id}")
//            @PathVariable Long id) {
//        var customer = customerService.findById(id);
//        var apiCustomer = modelMapper.map(customer, com.heilash.logistoffer.api.model.Customer.class);
//        return new CampaignResponse(apiCustomer);
//    }
//
//    @ApiOperation(value = "${customers.operation.delete-customer}",
//            nickname = "deleteCustomer",
//            authorizations = {@Authorization(BASIC_AUTH)})
//    @DeleteMapping("/{id}")
//    @ResponseStatus(OK)
//    public CampaignResponse deleteCustomer(
//            @ApiParam(value = "${customers.param.customer-id}")
//            @PathVariable Long id) {
//        var customer = customerService.deleteById(id);
//        var apiCustomer = modelMapper.map(customer, com.heilash.logistoffer.api.model.Customer.class);
//        return new CampaignResponse(apiCustomer);
//    }
//
//    @ApiOperation(value = "${customers.operation.getAll-customer}",
//            nickname = "getAllCustomer",
//            authorizations = {@Authorization(BASIC_AUTH)})
//    @GetMapping("/")
//    @ResponseStatus(OK)
//    public CampaignsResponse getCustomers() {
//        var customers = customerService.getAllCustomers();
//        var apiCustomers = customers.stream()
//                .map(customer -> modelMapper.map(customer, com.heilash.logistoffer.api.model.Customer.class))
//                .collect(Collectors.toList());
//        return new CampaignsResponse(apiCustomers);
//    }
//
//    @ApiOperation(value = "${customers.operation.update-customer}",
//            nickname = "updateCustomer",
//            authorizations = {@Authorization(BASIC_AUTH)})
//    @PutMapping("/{id}")
//    @ResponseStatus(OK)
//    public CampaignResponse updateCustomer(
//            @ApiParam(value = "${customers.param.customer-id}")
//            @PathVariable Long id,
//            @RequestBody @Valid CampaignRequest request) {
//        var customer = modelMapper.map(request.getCampaign(), Campaign.class);
//        Campaign domainCustomer = customerService.update(customer, id);
//        var apiCustomer = modelMapper.map(domainCustomer, com.haapp.formicary.api.model.Customer.class);
//        return new CampaignResponse(apiCustomer);
//    }
//}
