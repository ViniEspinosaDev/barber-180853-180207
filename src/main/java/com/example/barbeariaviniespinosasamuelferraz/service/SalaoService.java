package com.example.barbeariaviniespinosasamuelferraz.service;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Salao;
import com.example.barbeariaviniespinosasamuelferraz.repository.SalaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaoService {

    @Autowired
    private SalaoRepository salaoRepository;

    public List<Salao> getSaloes() {
        return salaoRepository.findAll();
    }

    public void salvarSalao(Salao salao) {
        salaoRepository.save(salao);
    }

    public Salao getSalaoById(int id) {
        return salaoRepository.findById(id).get();
    }

    public boolean removerSalao(Salao salao) {
        if (salao.getBarbeiros().isEmpty() && salao.getClientes().isEmpty() && salao.getAgendamentos().isEmpty()) {
            salaoRepository.delete(salao);
            return true;
        }
        return false;
    }
}