package Ex_02_03_TAD_Arvore_Generica_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoArvoreNaoVazia extends RuntimeException{
	
	public ExcecaoArvoreNaoVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}