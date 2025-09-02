package com.tcc.SalusSync.util;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;

@UtilityClass
public class ValidadorCPF {


    private String cpf;

    public static boolean validarCpf(String cpf){

        // Remove pontos e traço
        String cpfNumerico = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (cpfNumerico.length() != 11) return false;

        // Verifica se todos os dígitos são iguais (ex: 11111111111)
        if (cpfNumerico.matches("(\\d)\\1{10}")) return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpfNumerico.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpfNumerico.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        // Verifica se os dígitos calculados batem com os do CPF informado
        return cpfNumerico.charAt(9) - '0' == primeiroDigito &&
                cpfNumerico.charAt(10) - '0' == segundoDigito;

    }






}
