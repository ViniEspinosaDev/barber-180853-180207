package com.example.barbeariaviniespinosasamuelferraz.service;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Agendamento;
import com.example.barbeariaviniespinosasamuelferraz.entity.Barbeiro;
import com.example.barbeariaviniespinosasamuelferraz.entity.Cliente;
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
        salao = salaoRepository.findById(salao.getIdSalao()).get();

        if (salao.getClientes().size() == 0 && salao.getAgendamentos().size() == 0
                && salao.getBarbeiros().size() == 0) {
            salaoRepository.delete(salao);
            return true;
        }
        return false;
    }

    public boolean removerClienteSalao(Salao salao, Cliente cliente) {

        salao = salaoRepository.findById(salao.getIdSalao()).get();
        List<Agendamento> agendamentosSalao = salao.getAgendamentos();

        for (Agendamento agendamento : agendamentosSalao) {
            if (agendamento.getCliente().equals(cliente)) {
                return false;
            }
        }

        salao.getClientes().remove(cliente);
        salaoRepository.save(salao);

        return true;
    }

    public boolean removerBarbeiroSalao(Salao salao, Barbeiro barbeiro) {

        salao = salaoRepository.findById(salao.getIdSalao()).get();
        List<Agendamento> agendamentosSalao = salao.getAgendamentos();

        for (Agendamento agendamento : agendamentosSalao) {
            if (agendamento.getBarbeiro().equals(barbeiro)) {
                return false;
            }
        }

        salao.getBarbeiros().remove(barbeiro);
        salaoRepository.save(salao);

        return true;
    }
}