package com.avalant.quiz.repository;

import com.avalant.quiz.entity.Address;
import com.avalant.quiz.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, String> {
    Address findByApplication(Application application);
}
