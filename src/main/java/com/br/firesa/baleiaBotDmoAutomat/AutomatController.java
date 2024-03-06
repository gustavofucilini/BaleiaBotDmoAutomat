package com.br.firesa.baleiaBotDmoAutomat;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class AutomatController {
	// Imagens Do Bot
	private static Pattern imagemOkButton = new Pattern("src/main/resources/imagens/OkButton.png");
	private static Pattern imagemEnterID = new Pattern("src/main/resources/imagens/EnterID.png");
	private static Pattern imagemEnterPW = new Pattern("src/main/resources/imagens/EnterPW.png");
	private static Pattern imagemLoginButton = new Pattern("src/main/resources/imagens/LoginButton.png");
	private static Pattern imagemLater = new Pattern("src/main/resources/imagens/Later.png");
	private static Pattern imagemServerSelect = new Pattern("src/main/resources/imagens/ServerSelect.png");
	private static Pattern imagemTamerName = new Pattern("src/main/resources/imagens/TamerName.png");
	private static Pattern imagemCreate = new Pattern("src/main/resources/imagens/Create.png");
	private static Pattern imagemDigimonName = new Pattern("src/main/resources/imagens/DigimonName.png");
	private static Pattern imagemFirstXButton = new Pattern("src/main/resources/imagens/FirstXButton.png");
	private static Pattern imagemFirstSkipButton = new Pattern("src/main/resources/imagens/FirstSkipButton.png");
	private static Pattern imagemYesButton = new Pattern("src/main/resources/imagens/YesButton.png");
	private static Pattern imagemSecondSkipButton = new Pattern("src/main/resources/imagens/SecondSkipButton.png");
	private static Pattern imagemEventButton = new Pattern("src/main/resources/imagens/EventButton.png");
	private static Pattern imagemReceiveButton = new Pattern("src/main/resources/imagens/ReceiveButton.png");
	private static Pattern imagemRewardMailBoxButton = new Pattern("src/main/resources/imagens/RewardMailBoxButton.png");
	private static Pattern imagemCheckButtonMail = new Pattern("src/main/resources/imagens/CheckButtonMail.png");
	private static Pattern imagemSecondReceiveButton = new Pattern("src/main/resources/imagens/SecondReceiveButton.png");
	private static Pattern imagemBagButton = new Pattern("src/main/resources/imagens/BagButton.png");
	private static Pattern imagemLegBoxButton = new Pattern("src/main/resources/imagens/LegBoxButton.png");
	private static Pattern imagemGoldPresentButton = new Pattern("src/main/resources/imagens/GoldPresentButton.png");
	private static Pattern imagemGoldButton = new Pattern("src/main/resources/imagens/GoldButton.png");
	private static Pattern imagemTeraImage = new Pattern("src/main/resources/imagens/TeraImage.png");
	private static Pattern imagemLogoutButton = new Pattern("src/main/resources/imagens/LogoutButton.png");
	private static Screen s = new Screen();
	// End

	public static void main(String[] args) throws Exception {
		// focar na janela GDMO.exe
		App app = new App("GDMO.exe");
		app.focus();

		// Verifica Botão OK
		/*new Thread(() -> {
			while (true) {
				try {
					if (s.exists(imagemOkButton) != null) {
						s.click(imagemOkButton);
					}
					Thread.sleep(100); // Adiciona um atraso de 200 milissegundos
				} catch (InterruptedException | FindFailed e) {
					System.out.println("Erro ao clicar no botão: " + e.getMessage());
				}
			}
		}).start();*/
		// End
		
		//arquivo CSV
		String arquivoCSV = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "logins.csv";
		// ler o arquivo CSV
		List<String[]> linhas = lerCSV(arquivoCSV);
		
		for (int i = 0; i < linhas.size(); i++) {
		//for (String[] linha : linhas) {
			String[] linha = linhas.get(i);
			
			if (linha[0].equals("UserID") || linha[6].equals("X")) {
				continue;
			}
			checkAndClickButton();

			// clicar no primeiro local baseado na imagem
			s.wait(imagemEnterID, 60);
			s.click(imagemEnterID);
			s.type("\t");

			checkAndClickButton();
			
			// digitar o texto da coluna 'UserID'
			s.type(linha[0]);

			// clicar no segundo local baseado na imagem
			s.wait(imagemEnterPW, 60);
			// s.doubleClick(imagem2);
			s.type("\t");

			// digitar o texto da coluna 'Password'
			s.type(linha[2]);

			// clicar no terceiro local baseado na imagem
			s.wait(imagemLoginButton, 60);
			s.click(imagemLoginButton);
			
			checkAndClickButton();

			s.wait(imagemLater, 60);
			s.click(imagemLater);
			
			s.wait(imagemServerSelect, 90);
			s.doubleClick(imagemServerSelect);
			
			checkAndClickButton();

			s.wait(imagemTamerName, 60);
			// s.doubleClick(imagem2);
			s.type("\t");

			s.type(generateNickname());

			s.wait(imagemCreate, 60);
			s.click(imagemCreate);
			
			checkAndClickButton();

			s.wait(imagemDigimonName, 60);
			// s.doubleClick(imagem2);
			s.type("\t");

			s.type(generateNickname());

			s.wait(imagemCreate, 60);
			s.click(imagemCreate);
			
			checkAndClickButton();

			s.wait(imagemFirstXButton, 120);
			s.click(imagemFirstXButton);
			s.wait(imagemFirstXButton, 120);
			s.click(imagemFirstXButton);
			for (int j = 0; j < 5; j++) {
				s.type(Key.ENTER);
				Thread.sleep(500);
			}

			s.wait(imagemFirstSkipButton, 60);
			s.click(imagemFirstSkipButton);
			s.wait(imagemYesButton, 60);
			s.click(imagemYesButton);
			for (int j = 0; j < 5; j++) {
				s.type(Key.ENTER);
				Thread.sleep(500);
			}
			
			checkAndClickButton();

			s.wait(imagemSecondSkipButton, 60);
			s.click(imagemSecondSkipButton);
			s.wait(imagemYesButton, 60);
			s.click(imagemYesButton);
			//s.wait(imagemYesButton, 60);
			//s.click(imagemYesButton);
			
			//checkAndClickButton();

			//s.wait(imagemEventButton, 60);
			s.type("e");
			//s.click(imagemEventButton);

			s.wait(imagemReceiveButton, 60);
			s.click(imagemReceiveButton);

			s.wait(imagemRewardMailBoxButton, 60);
			s.click(imagemRewardMailBoxButton);

			s.wait(imagemCheckButtonMail, 60);
			s.click(imagemCheckButtonMail);
			//s.wait(imagemCheckButtonMail, 60);
			//s.click(imagemCheckButtonMail);
			

			s.wait(imagemSecondReceiveButton, 60);
			s.click(imagemSecondReceiveButton);

			
			for (int j = 0; j < 10; j++) {
				s.type(Key.ESC);
				Thread.sleep(100);
			}
			//s.wait(imagemFirstXButton, 120);
			//s.click(imagemFirstXButton);
			//s.wait(imagemFirstXButton, 120);
			//s.click(imagemFirstXButton);

			//s.wait(imagemBagButton, 60);
			s.type("i");
			//s.click(imagemBagButton);

			s.wait(imagemLegBoxButton, 60);
			s.rightClick(imagemLegBoxButton);
			
			
			Mouse.move(new Location(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2));
			imagemGoldPresentButton.similar(0.9F);
			//Clica no presente
			while (true) {
				try {
					s.wait(imagemGoldPresentButton, 5);
					s.rightClick(imagemGoldPresentButton);
					Mouse.move(new Location(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
	                        Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2));
					Thread.sleep(200);
				} catch (FindFailed e) {
					break; // Se a imagem não for encontrada, sai do loop
				}
			}
			
			Mouse.move(new Location(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2));
			//Clica no Gold
			while (true) {
				try {
					s.wait(imagemGoldButton, 5);
					s.rightClick(imagemGoldButton);
					Mouse.move(new Location(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
	                        Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2));
					Thread.sleep(200);
				} catch (FindFailed e) {
					break; // Se a imagem não for encontrada, sai do loop
				}
			}
			
			//Checa TERA
			imagemTeraImage.similar(0.9F);
			if (s.exists(imagemTeraImage, 15) != null) {
                // Adiciona "X" na sétima coluna
                linha[7] = "X";
                System.out.println("Encontrado Tera");
                // Atualiza o arquivo CSV
                //atualizarCSV(linhas, arquivoCSV);
            }
			
			for (int j = 0; j < 2; j++) {
				s.type(Key.ESC);
				Thread.sleep(500);
			}
			
			s.wait(imagemLogoutButton, 60);
			s.click(imagemLogoutButton);
			
			s.wait(imagemLater, 60);
			s.click(imagemLater);
			
			for (int j = 0; j < 10; j++) {
				s.type(Key.ESC);
				Thread.sleep(100);
			}
			
			linha[6] = "X";
			
            // Atualiza o arquivo CSV
			atualizarLinhaCSV(linha, arquivoCSV, i);
            
            linhas = lerCSV(arquivoCSV);
		}

	}

	private synchronized static void checkAndClickButton() {
		while (true) {
			try {
				s.wait(imagemOkButton, 5);
				s.rightClick(imagemOkButton);
				s.wait(100); // Adiciona um atraso de 200 milissegundos
			} catch (FindFailed | InterruptedException e) {
				break; // Se a imagem não for encontrada, sai do loop
			}
		}
	}

	private static List<String[]> lerCSV(String arquivo) throws Exception {
		Reader in = new FileReader(arquivo);
		CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').parse(in);
		List<String[]> linhas = new ArrayList<>();

		for (CSVRecord record : parser) {
			String[] linha = new String[record.size()];
			for (int i = 0; i < record.size(); i++) {
				linha[i] = record.get(i);
			}
			linhas.add(linha);
		}
		System.out.println("Lido CSV");
		return linhas;
	}
	
	private static void atualizarLinhaCSV(String[] linha, String arquivoCSV, int indice) {
	    try {
	        List<String> linhas = Files.readAllLines(Paths.get(arquivoCSV), StandardCharsets.UTF_8);
	        linhas.set(indice, String.join(";", linha));
	        Files.write(Paths.get(arquivoCSV), linhas, StandardCharsets.UTF_8);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static String generateNickname() {
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

}
