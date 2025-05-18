/*package model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.Produto;
import model.entities.Pedido;
//import services.GoogleMapsService;

public class Utilidades {

    public static void exibirMenuADM() {
        System.out.println("+---------------------------------+");
        System.out.println("|   Selecione a opção desejada    |");
        System.out.println("|---------------------------------|");
        System.out.println("| 0- Cadastrar Usuário            |");
        System.out.println("| 1- Cadastrar Produto            |");
        System.out.println("| 2- Retirar Produto              |");
        System.out.println("| 3- Listar Produtos              |");
        System.out.println("| 4- Criar Pedido                 |");
        System.out.println("| 5- Listar Pedidos               |");
        System.out.println("| 6- Calcular Distância           |");
        System.out.println("| 7- Volume Galpao                |");
        System.out.println("| 8- Atualizar Status             |");
        System.out.println("| 9- Sair                         |");
        System.out.println("+---------------------------------+");
    }

    public static void exibirMenuUSER() {
        System.out.println("+---------------------------------+");
        System.out.println("|   Selecione a opção desejada    |");
        System.out.println("|---------------------------------|");
        System.out.println("| 1- Cadastrar Produto            |");
        System.out.println("| 2- Retirar Produto              |");
        System.out.println("| 3- Listar Produtos              |");
        System.out.println("| 4- Criar Pedido                 |");
        System.out.println("| 5- Listar Pedidos               |");
        System.out.println("| 6- Calcular Distância           |");
        System.out.println("| 7- Volume Galpao                |");
        System.out.println("| 8- Sair                         |");
        System.out.println("+---------------------------------+");
    }

    public static void cadastrarProduto(Scanner sc, List<Produto> listaItens) {
        System.out.println("-----------Cadastrar Produto-----------");

        System.out.print("Digite o id do produto: ");
        int id = sc.nextInt();

        System.out.print("Digite o lote do produto: ");
        int lote = sc.nextInt();

        System.out.print("Digite a quantidade do produto: ");
        int quantidade = sc.nextInt();

        System.out.print("Digite o tamanho do produto: ");
        String tamanho = sc.nextLine();

        sc.nextLine();

        System.out.print("Digite a descrição do produto: ");
        String descricao = sc.nextLine();

        System.out.print("Digite a categoria do produto: ");
        String categoria = sc.nextLine();

        System.out.print("Digite o prazo do produto: ");
        String prazo = sc.nextLine();

        Produto produto = new Produto(id, lote, quantidade, tamanho, descricao, categoria, prazo);
        listaItens.add(produto);

        System.out.println("Produto cadastrado com sucesso!\n");
    }

    public static void retirarProduto(Scanner sc, List<Produto> listaItens) {
        System.out.print("Digite o ID do produto que deseja retirar: ");
        int idRemover = sc.nextInt();

        boolean removido = listaItens.removeIf(produtoAtual -> produtoAtual.getId() == idRemover);

        System.out.println(removido ? "Produto removido com sucesso!\n" : "Produto não encontrado.\n");
    }

    public static void listarProdutos(List<Produto> listaItens) {
        System.out.println("Listar Produtos:");

        if (listaItens.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.\n");
        } else {
            for (Produto i : listaItens) {
                System.out.println("--------------------------------------------------");
                System.out.println(i);
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public static void criarPedido(Scanner sc, List<Produto> listaItens, List<Pedido> listaPedidos) {

        System.out.println("----------- Criar Pedido -----------");

        System.out.print("Digite o ID do pedido: ");
        int idPedido = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o endereço de entrega: ");
        String endereco = sc.nextLine();

        System.out.print("Digite a data de entrega: ");
        String deadline = sc.nextLine();

        System.out.println("Digite os IDs dos produtos do pedido (separados por espaço): ");
        String[] idsProdutos = sc.nextLine().split(" ");

        List<Integer> itensPedido = new ArrayList<>();
        for (String idStr : idsProdutos) {
            itensPedido.add(Integer.parseInt(idStr));
        }

        Pedido pedido = new Pedido(idPedido, endereco, deadline, itensPedido);
        listaPedidos.add(pedido);

        System.out.println("Pedido criado com sucesso!\n");
    }

    public static void listarPedidos(List<Produto> listaItens, List<Pedido> listaPedidos) {
        System.out.println("Listar Pedidos:");
        System.out.println("--------------------------------------------------");

        if (listaPedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.\n");
        } else {
            for (Pedido p : listaPedidos) {
                System.out.println("ID do Pedido: " + p.getId());
                System.out.println("Endereço: " + p.getEndereco());
                System.out.println("Data de entrega: " + p.getDeadline());
                System.out.println("Itens do Pedido:");

                for (int idProduto : p.getItens()) {
                    Produto produto = listaItens.stream()
                            .filter(i -> i.getId() == idProduto)
                            .findFirst()
                            .orElse(null);

                    if (produto != null) {
                        System.out.println("  - " + produto);
                    } else {
                        System.out.println("  - Produto com ID " + idProduto + " não encontrado.");
                    }
                }
                System.out.println("--------------------------------------------------");
                System.out.println();
            }
        }
    }

    public static void atualizarStatus(Scanner sc, List<Pedido> listaPedidos) {
        System.out.print("Digite o ID do pedido que deseja atualizar: ");
        int idPedido = sc.nextInt();
        sc.nextLine();

        Pedido pedido = listaPedidos.stream()
                .filter(p -> p.getId() == idPedido)
                .findFirst()
                .orElse(null);

        if (pedido != null) {
            System.out.println("Status atual do pedido: " + pedido.getStatus());
            System.out.print("Digite o novo status (PENDENTE, EM_ANDAMENTO, ENTREGUE): ");
            String novoStatusStr = sc.nextLine().toUpperCase();

            try {
                Pedido.Status novoStatus = Pedido.Status.valueOf(novoStatusStr);
                pedido.setStatus(novoStatus);
                System.out.println("Status atualizado com sucesso!\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Tente novamente.\n");
            }
        } else {
            System.out.println("Pedido não encontrado.\n");
        }
    }

    public static void calcularDistancia(Scanner sc) {
        System.out.print("Digite o endereço de destino: ");
        String destino = sc.nextLine();

        String resultado = GoogleMapsService.calcularDistancia(destino);

        System.out.println("\n-------- Resultado da Distância ------------------");
        System.out.println("|   " + resultado + "   |");
        System.out.println("--------------------------------------------------\n");
    }

    /*public static void volumeGalpao(Scanner sc) {
        System.out.print("Digite a largura do galpão: ");
        int x = sc.nextInt();

        System.out.print("Digite o comprimento do galpão: ");
        int y = sc.nextInt();

        System.out.print("Digite a altura do galpão: ");
        int z = sc.nextInt();

}

*/

