package com.crud.clientes.dto;

import com.crud.clientes.model.Cliente;
import org.springframework.data.domain.Page;

public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String idade;
    private String endereco;
    private String cep;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.idade = cliente.getIdade();
        this.endereco = cliente.getEndereco();
        this.cep = cliente.getCep();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public static Page<ClienteDTO> converter(Page<Cliente> clientes) {
        return clientes.map(ClienteDTO::new);
    }
}
