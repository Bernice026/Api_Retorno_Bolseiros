package ao.bolseiro.api.bolseiro.dto;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Instituicao;
import lombok.Data;

@Data
public class InstituicaoDto {
	
	private Long idInstituicao;
	private String nomeInstituicao;
	private String nifInstituicao;
	private String tipoInstituicao;
	private String sectorInstituicao;
	
	
	
	public InstituicaoDto(Instituicao instituicao) {
		
		this.idInstituicao = instituicao.getIdInstituicao();
		this.nomeInstituicao = instituicao.getNomeInstituicao();
		this.nifInstituicao = instituicao.getNifInstituicao();
		this.tipoInstituicao = instituicao.getTipoInstituicao();
		this.sectorInstituicao = instituicao.getSectorInstituicao();
	}



	public static Page<InstituicaoDto> converter(Page<Instituicao> instituicao) {
		return instituicao.map(InstituicaoDto::new);
	}

	
	
	

}
