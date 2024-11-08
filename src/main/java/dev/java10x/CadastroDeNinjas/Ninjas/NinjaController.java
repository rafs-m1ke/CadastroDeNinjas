package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota.";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja Criado!";
    }

    // Mostrar todos os ninjas (CREATE)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/todos_id")
    public String mostrarTodosOsNinjasPorId() {
        return "Mostrar todos os ninjas por id";
    }

    //  Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar_id")
    public String alterarNinjaPorId() {
        return "Alterar ninja por id";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar_id")
    public String deletarNinjaPorId() {
        return "Ninja deletado por id";
    }

}
