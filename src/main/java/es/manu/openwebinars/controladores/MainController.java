package es.manu.openwebinars.controladores;

import es.manu.openwebinars.modelo.Producto;
import es.manu.openwebinars.servicios.CategoriaService;
import es.manu.openwebinars.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

	private static final int NUM_PRODUCTOS_ALEATORIOS = 8;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	public String index(@RequestParam(name="idCategoria", required=false) Long idCategoria, Model model) {

		model.addAttribute("categorias", categoriaService.findAll());

		List<Producto> productos;

		if (idCategoria == null) {
			productos = productoService.obtenerProductosAleatorios(NUM_PRODUCTOS_ALEATORIOS);
		} else {
			productos = productoService.findAllByCategoria(idCategoria);
		}

		model.addAttribute("productos", productos);

		return "index";
	}

	@GetMapping("/product/{id}")
	public String showDetails(@PathVariable("id") Long id, Model model) {
		Producto p = productoService.findById(id);
		if (p != null) {
			model.addAttribute("producto", p);
			return "detail";
		}

		return "redirect:/";

	}




}
