package com.example.barbeariaviniespinosasamuelferraz.service;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Cliente;
import com.example.barbeariaviniespinosasamuelferraz.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public void salvarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).get();
    }

    public boolean removerCliente(Cliente cliente) {
        // Talvez fazer o tratamento aqui futuramente
        if (cliente.getSaloes().isEmpty() && cliente.getAgendamentos().isEmpty()) {
            clienteRepository.delete(cliente);
            return true;
        }
        return false;
    }
}