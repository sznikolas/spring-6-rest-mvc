package nikolas.springframework.spring6restmvc.services;

import lombok.RequiredArgsConstructor;
import nikolas.springframework.spring6restmvc.mappers.CustomerMapper;
import nikolas.springframework.spring6restmvc.model.CustomerDTO;
import nikolas.springframework.spring6restmvc.repositories.BeerRepository;
import nikolas.springframework.spring6restmvc.repositories.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final BeerRepository beerRepository;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper
                .customerToCustomerDto(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDto(customerRepository
                .save(customerMapper.customerDtoToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {

        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepository.save(foundCustomer))));
            customerRepository.save(foundCustomer);
        }, () -> {
            atomicReference.set(Optional.empty());
        });
        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID customerId) {
        if (beerRepository.existsById(customerId)) {
            beerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
            if (StringUtils.hasText(customer.getCustomerName())) {
                foundCustomer.setCustomerName(customer.getCustomerName());
            }
            atomicReference.set(Optional.of(customerMapper
                    .customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }
}
