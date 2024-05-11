package ao.bolseiro.api.bolseiro.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_INSTITUICAO")
public class Instituicao {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInstituicao;
	private String nomeInstituicao;
	private String nifInstituicao;
	private String tipoInstituicao;
	private String sectorInstituicao;

	

	public Instituicao() {
	}
	
	public Instituicao(Long idInstituicao) {
		
		this.idInstituicao = idInstituicao;
	}
	
	public Instituicao(String nomeInstituicao, String nifInstituicao, String tipoInstituicao,
			String sectorInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
		this.nifInstituicao = nifInstituicao;
		this.tipoInstituicao = tipoInstituicao;
		this.sectorInstituicao = sectorInstituicao;
	}

}
