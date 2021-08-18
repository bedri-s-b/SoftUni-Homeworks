package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.json.CustomersSeedDto;
import exam.model.entity.Customer;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_FILE_PATH = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, TownService townService, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMER_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(readCustomersFileContent(), CustomersSeedDto[].class))
                .filter(customersSeedDto -> {
                    boolean valid = validationUtil.isValid(customersSeedDto) &&
                            townService.existByName(customersSeedDto.getTown().getName()) &&
                            !existEmail(customersSeedDto.getEmail());

                    sb.append(valid ? "Successfully imported Customer Mil " + customersSeedDto.getFirstName() + " " + customersSeedDto.getLastName() + " - marmit2@digg.com"
                            : "Invalid Customer").append(System.lineSeparator());

                    return valid;
                }).map(customersSeedDto -> {
                    Customer customer = modelMapper.map(customersSeedDto, Customer.class);
                    customer.setTown(townService.getTown(customersSeedDto.getTown().getName()));
                    return customer;
                }).forEach(customerRepository::save);


        return sb.toString();
    }

    @Override
    public boolean existEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
