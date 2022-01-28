package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.HelpSupport;
@Repository
public interface HelpSupportRepository extends JpaRepository<HelpSupport, Long> {

}
