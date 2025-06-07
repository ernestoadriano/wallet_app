package com.org.wallet_app.repository;

import com.org.wallet_app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByNumberIdentityCard(String numberIdentityCard);
}
