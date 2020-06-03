package com.example.barbeariaviniespinosasamuelferraz.service;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Agendamento;
import com.example.barbeariaviniespinosasamuelferraz.entity.Barbeiro;
import com.example.barbeariaviniespinosasamuelferraz.entity.Especialidade;
import com.example.barbeariaviniespinosasamuelferraz.repository.BarbeiroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    public List<Barbeiro> getBarbeiros() {
        return barbeiroRepository.findAll();
    }

    public void salvarBarbeiro(Barbeiro barbeiro) {
        barbeiroRepository.save(barbeiro);
    }

    public Barbeiro getBarbeiroById(int id) {
        return barbeiroRepository.findById(id).get();
    }

    public boolean removerBarbeiro(Barbeiro barbeiro) {
        barbeiro = barbeiroRepository.findById(barbeiro.getIdBarbeiro()).get();

        if (barbeiro.getAgendamentos().size() == 0) {
            barbeiroRepository.delete(barbeiro);
            return true;
        }
        return false;
    }

    public boolean removerEspecialidadeBarbeiro(Barbeiro barbeiro, Especialidade especialidade) {

        barbeiro = barbeiroRepository.findById(barbeiro.getIdBarbeiro()).get();
        List<Agendamento> agendamentosBarbeiro = barbeiro.getAgendamentos();

        for (Agendamento agendamento : agendamentosBarbeiro) {
            if (agendamento.getEspecialidades().equals(especialidade)) {
                return false;
            }
        }

        barbeiro.getEspecialidades().remove(especialidade);
        barbeiroRepository.save(barbeiro);

        return true;
    }

}