package com.example.barbeariaviniespinosasamuelferraz.controller;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Barbeiro;
import com.example.barbeariaviniespinosasamuelferraz.entity.Especialidade;
import com.example.barbeariaviniespinosasamuelferraz.service.BarbeiroService;
import com.example.barbeariaviniespinosasamuelferraz.service.EspecialidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping("/especialidades")
    public ModelAndView getEspecialidades() {
        ModelAndView mv = new ModelAndView("especialidadesTemplate");

        mv.addObject("especialidade", new Especialidade());
        mv.addObject("especialidades", especialidadeService.getEspecialidades());

        return mv;
    }

    @PostMapping("/salvarEspecialidade")
    public String salvarEspecialidade(@ModelAttribute Especialidade especialidade) {

        especialidadeService.salvarEspecialidade(especialidade);

        return "redirect:/especialidades";
    }

    @GetMapping("/detalhesEspecialidade/{idEspecialidade}")
    public ModelAndView getEspecialidadeDetalhes(@PathVariable(name = "idEspecialidade") Integer idEspecialidade) {

        Especialidade especialidade = especialidadeService.getEspecialidadeById(idEspecialidade);
        ModelAndView mv = new ModelAndView("detalhesEspecialidade");

        mv.addObject("especialidade", especialidade);

        return mv;
    }

    @GetMapping("/editarEspecialidade")
    public ModelAndView editarEspecialidade(@ModelAttribute Especialidade especialidade,
            @RequestParam Integer idEspecialidade) {

        ModelAndView mv = new ModelAndView("especialidadeEdit");

        Especialidade especialidadeAux = especialidadeService.getEspecialidadeById(especialidade.getIdEspecialidade());

        mv.addObject("especialidade", especialidadeAux);

        List<Barbeiro> barbeirosNaoAssociados = barbeiroService.getBarbeiros();
        barbeirosNaoAssociados.removeAll(especialidadeAux.getBarbeiros());

        mv.addObject("barbeiros", barbeirosNaoAssociados);

        return mv;
    }

    @GetMapping("/removerEspecialidade")
    public String removerEspecialidade(@ModelAttribute Especialidade especialidade,
            @RequestParam Integer idEspecialidade) {

        especialidadeService.removerEspecialidade(especialidade);

        return "redirect:/especialidades";
    }

    @GetMapping("/removerBarbeiroEspecialidade/{idEspecialidade}/{idBarbeiro}")
    public String removerBarbeiroEspecialidade(@PathVariable(name = "idEspecialidade") Integer idEspecialidade,
            @PathVariable(name = "idBarbeiro") Integer idBarbeiro) {

        Especialidade especialidade = especialidadeService.getEspecialidadeById(idEspecialidade);

        especialidadeService.removerBarbeiroEspecialidade(especialidade, barbeiroService.getBarbeiroById(idBarbeiro));

        return "redirect:/editarEspecialidade?idEspecialidade=" + idEspecialidade.toString();
    }

    @PostMapping("/associarBarbeiroEspecialidade")
    public String associarBarbeiroEspecialidade(@ModelAttribute Barbeiro barbeiro,
            @RequestParam Integer idEspecialidade) {

        Especialidade especialidade = especialidadeService.getEspecialidadeById(idEspecialidade);

        barbeiro = barbeiroService.getBarbeiroById(barbeiro.getIdBarbeiro());

        especialidade.getBarbeiros().add(barbeiro);
        especialidadeService.salvarEspecialidade(especialidade);

        return "redirect:/detalhesEspecialidade/" + idEspecialidade.toString();
    }

}