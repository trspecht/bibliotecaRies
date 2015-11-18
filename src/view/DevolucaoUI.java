package view;

import dao.ClienteDao;
import dao.ClienteDaoBd;
import java.time.Instant;
import java.util.Date;
import java.util.InputMismatchException;
import model.Aluguel;
import model.Devolucao;
import servico.AluguelServico;
import servico.DevolucaoServico;
import util.Console;
import util.DateUtil;
import view.menu.DevolucaoMenu;

public class DevolucaoUI {

    private DevolucaoServico servicoD;
    private AluguelServico servicoA;

    public DevolucaoUI() {
        servicoA = new AluguelServico();
        servicoD = new DevolucaoServico();
    }

    public void executar() {
        String op = "";
        while (!op.equals("0")) {
            op = Console.scanString(DevolucaoMenu.getOpcoes() + "\nDigite sua opção:");
            switch (op) {
                case DevolucaoMenu.OP_DEVOLVERLIVRO:
                    devolverLivro();
                    break;
                case DevolucaoMenu.OP_LISTARDEVOLUCAO:
                    mostrarDevolucao();
                    break;
                case DevolucaoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    return;
                default:
                    System.out.println("Opção inválida..");
            }
        }
    }

    private void devolverLivro() {
        boolean atrasado = false;
        try {
            int idAluguel = Console.scanInt("Digite o código do aluguel do livro que deseja devolver: ");
            if (servicoA.CodigoExiste(idAluguel) == false) {
                System.out.println("Não existe livro alugado com este código em no nosso sistema!");
                return;
            }
            Aluguel alu = servicoA.buscarPorCodigo(idAluguel);
            Date data = new Date();
            data = Date.from(Instant.now());
            int qntDias = servicoD.difDatas(alu, data);
            if (qntDias > 7) {
                double multa = 1.00 * qntDias;
                System.out.println("Você está com o livro atrasado, favor acertar a multa de atraso no valor de " + multa + " com a administração!");
                atrasado = true;
            }
            servicoD.addDevolucao(new Devolucao(alu, data), atrasado);
            System.out.println("Livro devolvido com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
        }
    }

    public void mostrarDevolucao() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-20s", "|Código da devolução") + "\t"
                + String.format("%-20s", "|Nome do cliente")
                + String.format("%-20s", "  |Titulo do livro alugado")
                + String.format("%-20s", "    |Data da devolução"));
        for (Devolucao devolucao : servicoD.listarDevolucao()) {
            System.out.println(String.format("%-10s", devolucao.getIdDevolucao()) + "\t"
                    + String.format("%-20s", "        |" + devolucao.getAluguel().getC().getNome()) + "\t"
                    + String.format("%-20s", "      |" + devolucao.getAluguel().getLivrosAlugados().getTitulo() + "\t"
                            + String.format("%-20s", "                  |" + DateUtil.dateToString(devolucao.getDataDevolucao()))));
        }
    }

}
