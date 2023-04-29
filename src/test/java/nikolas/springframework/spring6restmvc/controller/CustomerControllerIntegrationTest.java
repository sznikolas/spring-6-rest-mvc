package nikolas.springframework.spring6restmvc.controller;

import jakarta.transaction.Transactional;
import nikolas.springframework.spring6restmvc.entities.Customer;
import nikolas.springframework.spring6restmvc.mappers.CustomerMapper;
import nikolas.springframework.spring6restmvc.model.CustomerDTO;
import nikolas.springframework.spring6restmvc.repositories.BeerRepository;
import nikolas.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    private BeerRepository beerRepository;

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.deleteById(UUID.randomUUID()));
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteByIdFound() {
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity responseEntity = customerController.deleteById(customer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(beerRepository.findById(customer.getId())).isEmpty();


//        Customer foundCustomer = customerRepository.findById(customer.getId()).get();
//        assertThat(foundCustomer).isNull();
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.updateById(UUID.randomUUID(), CustomerDTO.builder().build()));
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateById(customer.getId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);
    }

    @Rollback
    @Transactional
    @Test
    void saveNewCustomerTest() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("New Customer")
                .build();

        ResponseEntity responseEntity = customerController.handlePost(customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID saveUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(saveUUID).get();
        assertThat(customer).isNotNull();

    }

    @Rollback
    @Transactional
    @Test
    void testListALlEmptyList() {

        customerRepository.deleteAll();
        List<CustomerDTO> customerDTOList = customerController.listCustomers();

        assertThat(customerDTOList.size()).isEqualTo(0);
    }

    @Test
    void testListAll() {
        List<CustomerDTO> customerDTOList = customerController.listCustomers();
        assertThat(customerDTOList.size()).isEqualTo(3);
    }

    @Test
    void testGetByIdNotFound() {

        assertThrows(NotFoundException.class, () ->{
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void getById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO.getId()).isNotNull();
    }
}