package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Listar Missões", description = "Lista todas as missões cadastradas na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões listadas com sucesso!"),
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        return ResponseEntity.ok(missoesService.listarMissoes());
    }



    @PostMapping("/criar")
    @Operation(summary = "Adicionar Missão", description = "Esta rota cria e adiciona uma nova missão no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível crirar a Missão!")
    })
    public ResponseEntity<MissoesDTO> criarMissao(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "O Usuário informa os parâmetros no corpo da requisição, em formato JSON.")
            @RequestBody MissoesDTO missoesDTO) {
        MissoesDTO missoes = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(missoes);
    }



    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar Missão Individualmente", description = "Esta rota lista a missão através do id informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada!")
    })
    public ResponseEntity<?> mostrarMissaoPorId(
            @Parameter(description = "O usuário informa id no caminho da requisição")
            @PathVariable Long id) {

        MissoesDTO missoes = missoesService.listarMissoesPorId(id);

        if (missoes != null)
            return ResponseEntity.ok().body(missoes);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com id " + id + " não encontrado ou não existe!");
    }




    @PatchMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar Missão", description = "Esta rota atualiza/altera os dados da missão desejada através do id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada!")
    })
    public ResponseEntity<?> atualizarMissao(
            @Parameter(description = "O usuário informa o id no caminho da requisição")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "O usuário informa os dados da missão a ser atualizada no corpo da requisição.")
            @RequestBody MissoesDTO missoesDTO) {

        MissoesDTO missoes = missoesService.atualizarMissao(id, missoesDTO);

        if (missoes != null)
            return ResponseEntity.ok().body(missoes);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com id " + id + " não encontrado ou não existe!");
    }




    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar Missão", description = "Esta rota deleta a Missão por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada ou não existe!")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "O usuário informa o id no caminho da requisição.")
            @PathVariable Long id) {

        if(missoesService.listarMissoesPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Ninja deletado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com id " + id + " não encontrado ou não existe!");


    }


}
