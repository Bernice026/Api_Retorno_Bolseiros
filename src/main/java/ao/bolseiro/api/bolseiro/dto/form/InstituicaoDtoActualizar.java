package ao.bolseiro.api.bolseiro.dto.form;

import ao.bolseiro.api.bolseiro.model.Instituicao;
import ao.bolseiro.api.bolseiro.repository.InstituicaoRepository;
import lombok.Data;

@Data
public class InstituicaoDtoActualizar {

	private String nomeInstituicao;

	private String nifInstituicao;

	private String tipoInstituicao;

	private String sectorInstituicao;
	
	

	public Instituicao actualizar(Long id, InstituicaoRepository instituicaoRepo) {
		Instituicao instituicao = instituicaoRepo.getOne(id);
		instituicao.setNomeInstituicao(this.nomeInstituicao);
		instituicao.setNifInstituicao(this.nifInstituicao);
		instituicao.setTipoInstituicao(this.tipoInstituicao);
		instituicao.setSectorInstituicao(this.sectorInstituicao);
		return instituicao;
	}
	
	
	

}
