package com.oscar.ecommerce.repositories;

import com.oscar.ecommerce.models.SturshkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SturshkUserRepository extends JpaRepository<SturshkUser, String> {
}
