package fatec.edu.br.demo.repository;

import fatec.edu.br.demo.model.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
