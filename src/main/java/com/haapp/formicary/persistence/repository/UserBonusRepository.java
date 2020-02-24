package com.haapp.formicary.persistence.repository;

import com.haapp.formicary.persistence.model.UserBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBonusRepository extends JpaRepository<UserBonus, Long> {

    List<UserBonus> findAllByUserId(Long userId);

}
