package view;

import java.time.Instant;
import java.util.Date;
import model.Aluguel;
import model.Devolucao;
import servico.AluguelServico;
import servico.DevolucaoServico;
import util.Console;
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
                case DevolucaoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    return;
                default:
                    System.out.println("Opção inválida..");
            }
        }
    }
    
    private void devolverLivro() {
        long codigo = Console.scanLong("Digite o código do aluguel do livro que deseja devolver: ");
        servicoA.CodigoExiste(codigo);
        if (servicoA.CodigoExiste(codigo) == false) {
            System.out.println("Não existe livro alugado com este código em no nosso sistema!");
            return;
        }
        Aluguel alu = servicoA.buscarPorCodigo(codigo);
        @SuppressWarnings("UnusedAssignment")
        Date data = new Date();
        data = Date.from(Instant.now());
        int qntDias = servicoD.difDatas(alu, data);
        System.out.println(qntDias);
        if (qntDias > 7) {
            System.out.println("Você está com o livro atrasado, favor acertar pendência com a administração!");
            return;
        }
        servicoD.addDevolucao(new Devolucao());
        System.out.println("Livro devolvido com sucesso!");
    }

   

}
