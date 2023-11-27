package br.com.jogadores;

import br.com.jogadores.entidade.Jogador;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


public class Main {

    public static void main(String args[]) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_jpa_jogador_h2");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        criarJogador("niko", "rifler", "g2", "global");
        criarJogador("fallen", "awper", "mibr", "global");
        criarJogador("fer", "entry", "mibr", "global");

        insereUmJogador("niko", "rifler", "g2", "global", entityManager);
        insereUmJogador("fallen", "awper", "mibr", "global", entityManager);
        insereUmJogador("fer", "entry", "mibr", "global", entityManager);

        List<Jogador> jogadores = listarJogadores(entityManager);
        imprimirJogadores(jogadores);

        entityManager.close();

    }


    private static List<Jogador> listarJogadores(EntityManager entityManager) {
        List<Jogador> jogadores = entityManager.createQuery("select p from Jogador p", Jogador.class).getResultList();

        return jogadores;
    }

    private static void imprimirJogadores(List<Jogador> jogadores) {
        System.out.println("\n\nJogadores: \n");
        for (Jogador jogador : jogadores) {
            System.out.println("Nome: " + jogador.getNome());
            System.out.println("Função: " + jogador.getFuncao());
            System.out.println("Time: " + jogador.getTime());
            System.out.println("Patente: " + jogador.getPatente());
            System.out.println("\n");
        }
    }


    private static Jogador criarJogador(String nome, String funcao, String time, String patente) {
        Jogador jogador = new Jogador();
        jogador.setNome(nome);
        jogador.setFuncao(funcao);
        jogador.setTime(time);
        jogador.setPatente(patente);
        return jogador;
    }

    private static void insereUmJogador(String nome, String funcao, String time, String patente, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(criarJogador(nome, funcao, time, patente));
        entityManager.getTransaction().commit();
    }

}