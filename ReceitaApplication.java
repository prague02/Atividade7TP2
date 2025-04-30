package fatec.edu.br.demo;

import fatec.edu.br.demo.service.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ReceitaApplication implements CommandLineRunner {

	private final RecipeService recipeService;

	public ReceitaApplication(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReceitaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n--- Menu ---");
			System.out.println("1. Buscar e salvar receita");
			System.out.println("2. Ver todas as receitas salvas");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();

			if (opcao == 1) {
				recipeService.buscarESalvarReceita();
			} else if (opcao == 2) {
				recipeService.exibirReceitasSalvas();
			} else if (opcao == 0) {
				System.out.println("Saindo...");
				break;
			} else {
				System.out.println("Opção inválida, tente novamente.");
			}
		}
	}
}
