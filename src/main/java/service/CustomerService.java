package service;

import model.Customer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService {
    private static final CustomerService SINGLETON = new CustomerService();
    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    private CustomerService() {

    }

    public static CustomerService getSingleton() {
        return SINGLETON;
    }

    public boolean duplicateMail(String email) {
        return customers.containsKey(email);
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String email) {
        return customers.get(email);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
