package nikolas.springframework.spring6restmvc.mappers;

import nikolas.springframework.spring6restmvc.entities.Customer;
import nikolas.springframework.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
