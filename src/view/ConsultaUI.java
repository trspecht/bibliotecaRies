/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import servico.AluguelServico;
import servico.ClienteServico;
import servico.LivroServico;
import util.Console;
import view.menu.ConsultaMenu;

/**
 *
 * @author Diego
 */
public class ConsultaUI {

    private final AluguelServico servicoA = new AluguelServico();
    private final ClienteServico servicoC = new ClienteServico();
    private final LivroServico servicoL = new LivroServico();
    private final LivroUI livroConsulta = new LivroUI();

    public ConsultaUI() {
    }

    public void executar() {
        String op = "";
        while (!op.equals("0")) {
            op = Console.scanString(ConsultaMenu.getOpcoes() + "\nDigite sua opção:");
            switch (op) {
                case ConsultaMenu.OP_LISTARLIVROS:
                    livroConsulta.mostrarLivro();
                    break;
                case ConsultaMenu.OP_LISTARLIVROSDISPONIVEIS:
                    servicoL.VisualizarLivroDisponivel();
                    break;
                case ConsultaMenu.OP_LISTARLIVROSMAISRETIRADOS:
                    servicoL.livrosMaisRetirados();
                    break;
                case ConsultaMenu.OP_LISTARCLIENTESQUEMAISRETIRAMLIVROS:
                    servicoC.clientesQueMaisAlugam();
                    break;
                case ConsultaMenu.OP_LISTARCLIENTESQUEMAISATRASARAM:
                    servicoC.clientesQueMaisAtrasam();
                    break;
                case ConsultaMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        }
    }

}
