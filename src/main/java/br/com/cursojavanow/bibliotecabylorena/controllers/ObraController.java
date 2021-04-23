/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursojavanow.bibliotecabylorena.controllers;

import br.com.cursojavanow.bibliotecabylorena.entities.Obra;
import br.com.cursojavanow.bibliotecabylorena.repositories.ObraRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zigui
 */

/*
[POST] /obras : A rota deverá receber titulo, editora, foto, e autores dentro do 
corpo da requisição. Ao cadastrar um novo projeto, ele deverá ser armazenado 
dentro de um objeto no seguinte formato: { id: 1, titulo: 'Harry Potter', editora: 
'Rocco',foto: 'https://i.imgur.com/UH3IPXw.jpg', autores: ["JK Rowling", "..."]};

[GET] /obras/ : A rota deverá listar todas as obras cadastradas

[PUT] /obras/🆔 : A rota deverá atualizar as informações de titulo, editora, 
foto e autores da obra com o id presente nos parâmetros da rota

[DELETE] /obras/🆔 : A rota deverá deletar a obra com o id presente nos 
parâmetros da rota
 */
@RestController
@RequestMapping("/obras")
@CrossOrigin(origins = "*")
public class ObraController {

    @Autowired
    private ObraRepository obraRepository;

    @PostMapping
    public Obra inserir(@RequestBody Obra obra) {
        Obra obraGravada = obraRepository.save(obra);
        return obraGravada;
    }

    @GetMapping
    public ResponseEntity<List<Obra>> todas() {
        List<Obra> obras = obraRepository.findAll();
        if (obras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(obras, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> atualizar(@PathVariable Long id,
            @RequestBody Obra obra) {
        Optional<Obra> ob = obraRepository.findById(id);

        if (ob.isPresent()) {
            ob.get().setTitulo(obra.getTitulo());
            ob.get().setEditora(obra.getEditora());
            ob.get().setFoto(obra.getFoto());
            ob.get().setAutores(obra.getAutores());

            Obra obraAlterada = obraRepository.save(ob.get());
            return ResponseEntity.ok(obraAlterada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Obra> remover(@PathVariable Long id) {
        try {
            obraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
