package com.crud.clientes.controller;

import com.crud.clientes.dto.ClienteDTO;
import com.crud.clientes.form.AtualizarClienteForm;
import com.crud.clientes.form.ClienteForm;
import com.crud.clientes.model.Cliente;
import com.crud.clientes.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping
    @Cacheable(value = "listaDeClientes")
    public Page<ClienteDTO> listarTodosClientes(@PageableDefault(sort = "id", page = 0, size = 10) Pageable paginacao) {
        Page<Cliente> clientes = clientesRepository.findAll(paginacao);
        return ClienteDTO.converter(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarClienteEspecifico(@PathVariable Long id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(new ClienteDTO(cliente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
        Cliente cliente = form.cadastrarCliente(clientesRepository);
        clientesRepository.save(cliente);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody @Valid AtualizarClienteForm form) {
        Optional<Cliente> optional = clientesRepository.findById(id);
        if (optional.isPresent()) {
            Cliente cliente = form.atualizarCliente(id, clientesRepository);
            return ResponseEntity.ok(new ClienteDTO(cliente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeClientes", allEntries = true)
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {
        Optional<Cliente> optional = clientesRepository.findById(id);
        if (optional.isPresent()) {
            clientesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}