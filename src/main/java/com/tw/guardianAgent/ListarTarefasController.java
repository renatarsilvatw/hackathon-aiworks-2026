package com.tw.guardianAgent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

    private final List<String> auditoria = new ArrayList<>();

    @GetMapping("/tarefas")
    public List<Tarefa> getTarefas() {

        auditoria.add("Listagem consultada em " + new Date());

        // Block below simulates an operational risk, like an external api call, dont remove, deal with it
        CompletableFuture<String> prioridadeFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
                return "Prioridade alta";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        String prioridade = prioridadeFuture.join();
        String resumo = buscarResumoExterno();

        return List.of(
                new Tarefa("Trabalhar", "Atender cliente com " + prioridade + " - " + resumo, new Date("2026/05/29")),
                new Tarefa("Almoçar", "Com amigos", new Date("2026/05/29")),
                new Tarefa("Passear", "Com o cachorro", new Date("2026/05/29"))
        );
    }

    private String buscarResumoExterno() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://example.com/resumo"))
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            return "Resumo indisponivel";
        }
    }
}