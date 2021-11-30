package es.manu.openwebinars;

import es.manu.openwebinars.modelo.Producto;
import es.manu.openwebinars.modelo.Puntuacion;
import es.manu.openwebinars.repositorios.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class OpenwebinarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenwebinarsApplication.class, args);
    }
    /**
     * Este método, que se ejecutará al iniciar el proyecto nos servirá de soporte
     * para que todos los productos de nuestro sistema tengan una puntuación
     * (más o menos) aleatoria, simulando el que haya sido puntuado por cientos
     * de usuarios durante varios meses.
     *
     * @param productoRepository Repositorio de productos
     * @return Una instancia de CommandLineRunner, que será ejecutada al iniciar el proyecto
     */

    @Bean
    public CommandLineRunner initData(ProductoRepository productoRepository) {

        return args -> {

            // Rescatamos todos los productos
            List<Producto> productos = productoRepository.findAll();

            Random r = new Random();

            // Para cada uno de ellos
            for (Producto p : productos) {
                // Vamos a añadirle un número aleatorio de puntuaciones, entre 1 y 10
                for (int i = 0; i < r.nextInt(10); i++)
                    // Lo valoramos con una puntuación aleatoria, entre 3 y 5.
                    p.addPuntuacion(new Puntuacion(3 + r.nextInt(2)));
            }

            // Actualizamos los productos, almacenando así su puntuación
            productoRepository.saveAll(productos);

        };

    }
}
