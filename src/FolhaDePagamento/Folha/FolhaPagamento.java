package FolhaDePagamento.Folha;

import FolhaDePagamento.Funcionario;
import FolhaDePagamento.Impostos.CalcularImposto;
import FolhaDePagamento.Impostos.Impostos2025;

public class FolhaPagamento {

    public static ResultadoFolha gerarFolha(
            Funcionario f,
            int mesFolha,
            int anoFolha,
            boolean feriasNoMes,
            CalcularImposto imposto) {

        ResultadoFolha r = new ResultadoFolha();

        double salarioBase = f.getCargo().getSalarioBase();

        // VALOR DA HORA EXTRA + 50% (CLT)
        double valorHora = salarioBase / 220;
        double horasExtras = f.getHorasExtras() * valorHora * 1.5;

        // 13º SALÁRIO DIVIDIDO SOBRE 2 PARCELAS
        double decimoTerceiro = 0;

        if (mesFolha == 11) {
            decimoTerceiro = salarioBase / 2; // 1ª parcela
        } else if (mesFolha == 12) {
            decimoTerceiro = salarioBase / 2; // 2ª parcela
        }

        // FÉRIAS (somente se tirar férias)
        double ferias = 0;
        double adicionalFerias = 0;

        if (feriasNoMes) {
            ferias = salarioBase;
            adicionalFerias = ferias / 3;
        }

        // CALCULO DO SALARIO BRUTO TOTAL
        double bruto = salarioBase + horasExtras + decimoTerceiro + ferias + adicionalFerias;

        // Impostos do mês
        double inss = imposto.calcularINSS(bruto);
        double irrf = imposto.calcularIRRF(bruto);
        double fgts = imposto.calcularFGTS(bruto);

        double liquido = bruto - inss - irrf;

        // PREENCHIMENTO DO OBJETO RESULTADO
        r.nome = f.getNome();
        r.cargo = f.getCargo().getNome();
        r.mesFolha = mesFolha;
        r.anoFolha = anoFolha;

        r.salarioBase = salarioBase;
        r.horasExtras = horasExtras;
        r.decimoTerceiro = decimoTerceiro;
        r.ferias = ferias + adicionalFerias;
        r.inss = inss;
        r.irrf = irrf;
        r.fgts = fgts;
        r.bruto = bruto;
        r.liquido = liquido;

        return r;
    }
}