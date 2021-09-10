package com.sudhi.gameranking.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersDao extends JpaRepository<Players, String>{

}
