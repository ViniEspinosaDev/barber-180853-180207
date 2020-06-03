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
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/barbeiros")
    public ModelAndView getBarbeiros() {
        ModelAndView mv = new ModelAndView("barbeirosTemplate");

        mv.addObject("barbeiro", new Barbeiro());
        mv.addObject("barbeiros", barbeiroService.getBarbeiros());

        return mv;
    }

    @PostMapping("/salvarBarbeiro")
    public String salvarBarbeiro(@ModelAttribute Barbeiro barbeiro) {

        barbeiroService.salvarBarbeiro(barbeiro);

        return "redirect:/barbeiros";
    }

    @GetMapping("/detalhesBarbeiro/{idBarbeiro}")
    public ModelAndView getBarbeiroDetalhes(@PathVariable(name = "idBarbeiro") Integer idBarbeiro) {

        Barbeiro barbeiro = barbeiroService.getBarbeiroById(idBarbeiro);
        ModelAndView mv = new ModelAndView("detalhesBarbeiro");

        mv.addObject("barbeiro", barbeiro);

        return mv;
    }

    @GetMapping("/editarBarbeiro")
    public ModelAndView editarBarbeiro(@ModelAttribute Barbeiro barbeiro, @RequestParam Integer idBarbeiro) {

        ModelAndView mv = new ModelAndView("barbeiroEdit");

        Barbeiro barbeiroAux = barbeiroService.getBarbeiroById(barbeiro.getIdBarbeiro());

        mv.addObject("barbeiro", barbeiroAux);

        List<Especialidade> especialidadeNaoAssociadas = especialidadeService.getEspecialidades();
        especialidadeNaoAssociadas.removeAll(barbeiroAux.getEspecialidades());

        mv.addObject("especialidades", especialidadeNaoAssociadas);

        return mv;
    }

    @GetMapping("/removerBarbeiro")
    public String removerBarbeiro(@ModelAttribute Barbeiro barbeiro, @RequestParam Integer idBarbeiro) {

        barbeiroService.removerBarbeiro(barbeiro);

        return "redirect:/barbeiros";
    }

    @GetMapping("/removerEspecialidadeBarbeiro/{idBarbeiro}/{idEspecialidade}")
    public String removerEspecialidadeBarbeiro(@PathVariable(name = "idBarbeiro") Integer idBarbeiro,
            @PathVariable(name = "idEspecialidade") Integer idEspecialidade) {

        Barbeiro barbeiro = barbeiroService.getBarbeiroById(idBarbeiro);

        barbeiroService.removerEspecialidadeBarbeiro(barbeiro,
                especialidadeService.getEspecialidadeById(idEspecialidade));

        return "redirect:/editarBarbeiro?idBarbeiro=" + idBarbeiro.toString();
    }

    @PostMapping("/associarEspecialidadeBarbeiro")
    public String associarEspecialidadeBarbeiro(@ModelAttribute Especialidade especialidade,
            @RequestParam Integer idBarbeiro) {

        Barbeiro barbeiro = barbeiroService.getBarbeiroById(idBarbeiro);

        especialidade = especialidadeService.getEspecialidadeById(especialidade.getIdEspecialidade());

        barbeiro.getEspecialidades().add(especialidade);
        barbeiroService.salvarBarbeiro(barbeiro);

        return "redirect:/detalhesBarbeiro/" + idBarbeiro.toString();
    }

}