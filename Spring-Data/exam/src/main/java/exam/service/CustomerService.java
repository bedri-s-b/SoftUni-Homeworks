package exam.service;

import java.io.IOException;

public interface CustomerService {

    boolean areImported();

    String readCustomersFileContent() throws IOException;

    String importCustomers() throws IOException;

    boolean existEmail(String email);
}
