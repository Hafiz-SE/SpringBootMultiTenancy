package com.multitenancy.test.repositories;

import com.multitenancy.test.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    @Async
    @Query("select c from Customer c")
    CompletableFuture<List<Customer>> findAllAsync();

}
