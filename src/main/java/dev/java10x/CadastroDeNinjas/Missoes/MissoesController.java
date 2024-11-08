package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listarMissoes() {
        return "Todas as missões";
    }

    @PostMapping("/criar")
    public String criarMissao() {
        return "Missão criada";
    }

    @GetMapping("/missao_id")
    public String mostrarMissaoPorId() {
        return "Missão por id";
    }

    @PutMapping("/atualizar")
    public String atualizarMissao() {
        return "Missão atualizada";
    }

    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missão deletada";
    }


}
