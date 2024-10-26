package com.fipe.leo.demo.principal;

import com.fipe.leo.demo.model.Dados;
import com.fipe.leo.demo.model.Modelos;
import com.fipe.leo.demo.service.ConsumoApi;
import com.fipe.leo.demo.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private final String URL_Base = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    public void exibeMenu(){
        System.out.println("****OPÇÕES****");
        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");
        System.out.println("Digite sua escolha: ");
        System.out.println("**************");
        System.out.println();
        String opcao = sc.nextLine();
        String endereco = "";
        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_Base+"carros/marcas";
        }
        if(opcao.toLowerCase().contains("mot")){
            endereco = URL_Base+"motos/marcas";
        }
        if(opcao.toLowerCase().contains("camin")){
            endereco = URL_Base+"caminhoes/marcas";
        }
        var json = consumoApi.obterDados(endereco);
        System.out.println(json);
        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                        .sorted(Comparator.comparing(Dados::nome)).forEach(System.out::println);
        System.out.println("****Escolha uma marca através do código****");
        var codigo  = sc.nextLine();
        endereco+="/"+codigo+"/modelos";
        json = consumoApi.obterDados(endereco);
        var modeloLista = conversor.obterDados(json, Modelos.class);
        System.out.println(modeloLista);
        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::nome));

    }
}
