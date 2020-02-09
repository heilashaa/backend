//package com.haapp.formicary.domain.service;
//
//import com.haapp.formicary.mapping.CampaignMapper;
//import com.haapp.formicary.persistence.repository.CampaignRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import springfox.documentation.swagger2.mappers.ModelMapper;
//
//import java.util.stream.Collectors;
//
//import static java.util.Objects.nonNull;
//
//@Service
//@AllArgsConstructor
//@Transactional
//public class CampaignService {
//
//private CustomerRepository customerRepository;
//private ModelMapper modelMapper;
//
//public Customer create(Customer customer) {
//        if (nonNull(customer)) {
//        var persistenceCustomer = customerRepository.save(modelMapper.map(customer, com.heilash.logistoffer.persistence.model.Customer.class));
//        return modelMapper.map(persistenceCustomer, Customer.class);
//        }
//        return null;
//        }
//
//public Customer findById(Long id) {
//        var optionalCustomer = customerRepository.findById(id);
//        return optionalCustomer.map(customer -> modelMapper.map(customer, Customer.class))
//        .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND));
//        }
//
//public Customer deleteById(Long id) {
//        var optionalCustomer = customerRepository.findById(id);
//        if (optionalCustomer.isPresent()) {
//        customerRepository.delete(optionalCustomer.get());
//        return optionalCustomer.map(customer -> modelMapper.map(customer, Customer.class)).orElse(null);
//        }
//        return null;
//        }
//
//public List<Customer> getAllCustomers() {
//        var customers = customerRepository.findAll();
//        return customers.stream()
//        .map(customer -> modelMapper.map(customer, Customer.class))
//        .collect(Collectors.toList());
//        }
//
//public Customer update(Customer customer, Long id) {
//        var persistenceCustomer = customerRepository.getOne(id);
//        if (nonNull(customerRepository)) {
//        modelMapper.map(customer, persistenceCustomer);
//        persistenceCustomer = customerRepository.save(persistenceCustomer);
//        return modelMapper.map(persistenceCustomer, Customer.class);
//        }
//        return null;
//        }
//}
//
