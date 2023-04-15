package ci.inphb.appspringmongo;

import ci.inphb.appspringmongo.Domain.Category;
import ci.inphb.appspringmongo.Domain.Product;
import ci.inphb.appspringmongo.Repositories.CategoryRepository;
import ci.inphb.appspringmongo.Repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class AppSpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSpringMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository){
		return args -> {
			//...
			categoryRepository.deleteAll();
			Stream.of("C1 Ordinateurs", "C2 Imprimantes", "C3 Telephones").forEach(c->{
				categoryRepository.save(new Category(c.split(" ")[0], c.split(" ")[1],new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);

			productRepository.deleteAll();
			Category c1=categoryRepository.findById("C1").get();
			Stream.of("P1","P2","P3","P4").forEach(pro->{
				Product p=productRepository.save(new Product(null, pro,Math.random()*10000,c1));
				c1.getProducts().add(p);
				categoryRepository.save(c1);
			});

			Category c2=categoryRepository.findById("C2").get();
			Stream.of("P5","P6","P7").forEach(pro->{
				Product p=productRepository.save(new Product(null, pro,Math.random()*10000,c2));
				c1.getProducts().add(p);
				categoryRepository.save(c2);
			});

			Category c3=categoryRepository.findById("C3").get();
			Stream.of("P8","P9").forEach(pro->{
				Product p=productRepository.save(new Product(null, pro,Math.random()*10000,c3));
				c1.getProducts().add(p);
				categoryRepository.save(c3);
			});
			productRepository.findAll().forEach(System.out::println);
		};

	}

}
