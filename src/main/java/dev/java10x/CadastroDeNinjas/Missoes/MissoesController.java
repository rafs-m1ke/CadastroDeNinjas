package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @Autowired
    private MissoesService missoesService;

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        return ResponseEntity.ok(missoesService.listarMissoes());
    }

    @PostMapping("/criar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO missoes = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> mostrarMissaoPorId(@PathVariable Long id) {

        MissoesDTO missoes = missoesService.listarMissoesPorId(id);

        if (missoes != null)
            return ResponseEntity.ok().body(missoes);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com id " + id + " não encontrado ou não existe!");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO) {

        MissoesDTO missoes = missoesService.atualizarMissao(id, missoesDTO);

        if (missoes != null)
            return ResponseEntity.ok().body(missoes);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com id " + id + " não encontrado ou não existe!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {

        if(missoesService.listarMissoesPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Ninja deletado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com id " + id + " não encontrado ou não existe!");


    }


}
