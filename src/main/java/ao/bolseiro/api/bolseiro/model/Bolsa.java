package ao.bolseiro.api.bolseiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;


@Data
@Entity
@Table(name = "TB_BOLSA")
public class Bolsa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBolsa;
	private String nomeBolsa;
	private String categoriaBolsa;
	private String tipoBolsa;
	private String duracaoBolsa;
	private String descricaoBolsa;

	

	public Bolsa() {
	}
	
	

	public Bolsa(String nomeBolsa, String categoriaBolsa, String tipoBolsa, String duracaoBolsa,
			String descricaoBolsa) {
		this.nomeBolsa = nomeBolsa;
		this.categoriaBolsa = categoriaBolsa;
		this.tipoBolsa = tipoBolsa;
		this.duracaoBolsa = duracaoBolsa;
		this.descricaoBolsa = descricaoBolsa;
	}

}
