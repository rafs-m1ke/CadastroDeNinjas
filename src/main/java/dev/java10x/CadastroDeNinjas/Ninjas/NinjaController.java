package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;


    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + ninjaDTO.getNome() + " - (ID): " + ninjaDTO.getId());
    }

    // Mostrar todos os ninjas (CREATE)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if(ninja != null)
            return ResponseEntity.ok(ninja);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com id " + id + " não encontrado ou não existe!");
    }

    //  Alterar dados dos ninjas (UPDATE)
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninja) {

        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(id, ninja);

        if(ninjaDTO != null) {
            return ResponseEntity.ok(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com id " + id + " não encontrado ou não existe!");
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinja(@PathVariable Long id) {
        if(ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com o ID: " + id + " deletado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com id " + id + " não encontrado ou não existe!");
    }

}
