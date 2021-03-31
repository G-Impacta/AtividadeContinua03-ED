package Ex_04_05_TAD_Arvore_Binaria_Excecoes;

@SuppressWarnings("serial")
public class PilhaExcecao_PilhaVazia extends RuntimeException {
	
	public PilhaExcecao_PilhaVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}
