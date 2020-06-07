package com.example.barbeariaviniespinosasamuelferraz.service;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Agendamento;
import com.example.barbeariaviniespinosasamuelferraz.entity.Barbeiro;
import com.example.barbeariaviniespinosasamuelferraz.entity.Especialidade;
import com.example.barbeariaviniespinosasamuelferraz.repository.EspecialidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> getEspecialidades() {
        return especialidadeRepository.findAll();
    }

    public void salvarEspecialidade(Especialidade especialidade) {
        especialidadeRepository.save(especialidade);
    }

    public Especialidade getEspecialidadeById(int id) {
        return especialidadeRepository.findById(id).get();
    }

    public boolean removerEspecialidade(Especialidade especialidade) {

        especialidade = especialidadeRepository.findById(especialidade.getIdEspecialidade()).get();

        if (especialidade.getAgendamentos().size() == 0 && especialidade.getBarbeiros().size() == 0) {
            especialidadeRepository.delete(especialidade);
            return true;
        }

        /*
         * Esse método só permite remover a especialidade caso ela não esteja vinculada
         * com nenhum agendamento e com nenhum barbeiro. Porém, ao tentar remover não
         * irá dar nenhum erro na tela, apenas não irá remover, pois, ainda não aprendi
         * a soltar pop-up em html xD
         */

        return false;
    }

    public boolean removerBarbeiroEspecialidade(Especialidade especialidade, Barbeiro barbeiro) {

        especialidade = especialidadeRepository.findById(especialidade.getIdEspecialidade()).get();
        List<Agendamento> agendamentosEspecialidade = especialidade.getAgendamentos();

        for (Agendamento agendamento : agendamentosEspecialidade) {
            if (agendamento.getBarbeiro().equals(barbeiro)) {
                return false;
            }
        }

        especialidade.getBarbeiros().remove(barbeiro);
        especialidadeRepository.save(especialidade);

        return true;
    }

}