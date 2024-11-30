package dev.java10x.CadastroDeNinjas.Ninjas;

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
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;


    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Adicionar Ninja", description = "Esta rota cria e adiciona um novo ninja no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível crirar o Ninja!")
            })
    public ResponseEntity<String> criarNinja(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "O Usuário informa os parâmetros no corpo da requisição, em formato JSON.")
            @RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + ninjaDTO.getNome() + " - (ID): " + ninjaDTO.getId());
    }




    // Mostrar todos os ninjas (CREATE)
    @GetMapping("/listar")
    @Operation(summary = "Listar Ninjas", description = "Lista todos os ninjas cadastrados na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas listados com sucesso!"),
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        return ResponseEntity.ok(ninjaService.listarNinjas());
    }




    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar o Ninjas Individualmente", description = "Esta rota lista o ninja através do id informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado!")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "O usuário informa id no caminho da requisição")
            @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if(ninja != null)
            return ResponseEntity.ok(ninja);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com id " + id + " não encontrado ou não existe!");
    }




    //  Alterar dados dos ninjas (UPDATE)
    @PatchMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar Ninja", description = "Esta rota atualiza/altera os dados do ninja desejado através do id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado!")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "O usuário informa o id no caminho da requisição")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "O usuário manda os dados do ninja a ser atualizado no corpo da requisição.")
            @RequestBody NinjaDTO ninja) {

        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(id, ninja);

        if(ninjaDTO != null) {
            return ResponseEntity.ok(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com id " + id + " não encontrado ou não existe!");
    }




    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar Ninja", description = "Esta rota deleta o Ninja por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado ou não existe!")
    })
    public ResponseEntity<String> deletarNinja(
            @Parameter(description = "O usuário informa o id no caminho da requisição.")
            @PathVariable Long id) {
        if(ninjaService.listarNinjasPorId(id) != null) {
            ninjaService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com o ID: " + id + " deletado com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com id " + id + " não encontrado ou não existe!");
    }

}
