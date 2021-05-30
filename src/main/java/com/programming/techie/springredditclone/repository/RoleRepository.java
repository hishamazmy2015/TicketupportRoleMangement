package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository   extends PagingAndSortingRepository<Role, Long> {
}
