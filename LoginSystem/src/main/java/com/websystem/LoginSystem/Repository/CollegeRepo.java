package com.websystem.LoginSystem.Repository;

import com.websystem.LoginSystem.models.CollegeEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepo extends JpaRepository<CollegeEmployee,Long> {
//    CollegeEmployee find
}
