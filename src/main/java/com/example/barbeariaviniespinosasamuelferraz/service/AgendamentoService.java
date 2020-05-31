package com.example.barbeariaviniespinosasamuelferraz.service;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Agendamento;
import com.example.barbeariaviniespinosasamuelferraz.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> getAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public void salvarAgendamento(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public Agendamento getAgendamentoById(int id) {
        return agendamentoRepository.findById(id).get();
    }

    public void removerAgendamento(Agendamento agendamento) {
        agendamentoRepository.delete(agendamento);
    }
}