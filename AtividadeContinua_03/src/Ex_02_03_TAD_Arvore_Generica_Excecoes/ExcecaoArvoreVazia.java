package Ex_02_03_TAD_Arvore_Generica_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoArvoreVazia extends RuntimeException{
	
	public ExcecaoArvoreVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}
