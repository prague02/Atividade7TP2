package fatec.edu.br.demo.service;

import fatec.edu.br.demo.dto.RecipeDTO;
import fatec.edu.br.demo.model.Recipe;
import fatec.edu.br.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@Service
public class RecipeService {

    private final RecipeRepository repository;
    private final RestTemplate restTemplate;

    @Autowired
    public RecipeService(RecipeRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    // Método para buscar e salvar a receita
    public void buscarESalvarReceita() {
        Scanner scanner = new Scanner(System.in);

        // Recebe o ID da receita
        System.out.print("Digite o ID da receita: ");
        int id = scanner.nextInt();

        String url = "https://dummyjson.com/recipes/" + id;

        // Consome a API e converte para o DTO
        RecipeDTO dto = restTemplate.getForObject(url, RecipeDTO.class);

        if (dto != null) {
            System.out.println("Receita: " + dto.getName());
            System.out.println("Ingredientes: " + dto.getIngredients());
            System.out.println("Tempo de preparo: " + dto.getPrepTimeMinutes() + " minutos");
            System.out.println("Tempo de cozimento: " + dto.getCookTimeMinutes() + " minutos");
            System.out.println("Porções: " + dto.getServings());
            System.out.println("Dificuldade: " + dto.getDifficulty());

            // Pergunta se deseja salvar a receita no banco de dados
            System.out.print("Deseja salvar no banco de dados? (s/n): ");
            String opcao = scanner.next();

            if (opcao.equalsIgnoreCase("s")) {
                // Criação da entidade Recipe para salvar no banco
                Recipe receita = new Recipe();
                receita.setId(dto.getId());
                receita.setName(dto.getName());
                receita.setCookTimeMinutes(dto.getCookTimeMinutes());
                receita.setPrepTimeMinutes(dto.getPrepTimeMinutes());
                receita.setServings(dto.getServings());
                receita.setDifficulty(dto.getDifficulty());
                receita.setIngredients(dto.getIngredients());

                // Salva a receita no banco de dados
                repository.save(receita);
                System.out.println("Receita salva com sucesso!");
            } else {
                System.out.println("Receita não foi salva.");
            }
        } else {
            System.out.println("Receita não encontrada.");
        }
    }

    // Método para exibir todas as receitas salvas no banco de dados
    public void exibirReceitasSalvas() {
        List<Recipe> receitas = repository.findAll();

        if (!receitas.isEmpty()) {
            System.out.println("Receitas salvas:");
            for (Recipe receita : receitas) {
                System.out.println("ID: " + receita.getId() + ", Nome: " + receita.getName());
            }
        } else {
            System.out.println("Não há receitas salvas.");
        }
    }
}
