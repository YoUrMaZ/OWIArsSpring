package es.manu.openwebinars.modelo;

import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String nombre;

	@Lob
	private String descripcion;

	@Min(0)
	private float pvp;

	private float descuento;

	@URL
	private String imagen;

	@NotNull
	@ManyToOne
	private Categoria categoria;

	@OneToMany(mappedBy="producto", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private Set<Puntuacion> puntuaciones = new HashSet<Puntuacion>();

	public Producto() {
	}

	public Producto(String nombre, String descripcion, float pvp, float descuento, String imagen, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pvp = pvp;
		this.descuento = descuento;
		this.imagen = imagen;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	/**
	 * Métodos helper
	 */
	public void addPuntuacion(Puntuacion puntuacion) {
		this.puntuaciones.add(puntuacion);
		puntuacion.setProducto(this);
	}


	public double getPuntuacionMedia() {
		if (this.puntuaciones.isEmpty())
			return 0;
		else
			return this.puntuaciones.stream()
					.mapToInt(Puntuacion::getPuntuacion)
					.average()
					.getAsDouble();
	}

	public double getNumeroTotalPuntuaciones() {
		return this.puntuaciones.size();
	}


}
