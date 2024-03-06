package com.br.firesa.baleiaBotDmoAutomat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class initAppTests {

	public static String generateNickname() {
        int length = new Random().nextInt(5) + 8; // Gera um comprimento aleatório de 8 a 12 caracteres
        List<Character> characters = new ArrayList<>();

        // Adiciona caracteres únicos à lista
        for (char c = 'a'; c <= 'z'; c++) {
            characters.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            characters.add(c);
        }
        for (char c = '0'; c <= '9'; c++) {
            characters.add(c);
        }

        // Embaralha os caracteres
        Collections.shuffle(characters);

        // Seleciona os primeiros 'length' caracteres da lista embaralhada
        StringBuilder nickname = new StringBuilder();
        for (int i = 0; i < length; i++) {
            nickname.append(characters.get(i));
        }

        return nickname.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateNickname());
    }


}
