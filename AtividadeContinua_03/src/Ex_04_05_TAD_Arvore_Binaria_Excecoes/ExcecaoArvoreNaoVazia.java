package Ex_04_05_TAD_Arvore_Binaria_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoArvoreNaoVazia extends RuntimeException{
	
	public ExcecaoArvoreNaoVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}