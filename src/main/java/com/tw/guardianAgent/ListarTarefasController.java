package com.tw.guardianAgent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class Tarefa{
    UUID id;
    String titulo;
    String descricao;
    Date dataEntrega;

    public Tarefa(String titulo, String descricao, Date dataEntrega) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }
}

@RestController
public class ListarTarefasController {

    @GetMapping("/tarefas")
    public List<Tarefa> getTarefas(){
      return List.of(
              new Tarefa("Trabalhar", "Atender cliente", new Date("2026/05/29")),
              new Tarefa("Almoçar", "Com amigos", new Date("2026/05/29")),
              new Tarefa("Passear", "Com o cachorro", new Date("2026/05/29"))
      );


    }

}
