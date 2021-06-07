package com.crud.clientes.repository;

import com.crud.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

}
