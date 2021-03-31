package Ex_04_05_TAD_Arvore_Binaria_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoArvoreVazia extends RuntimeException{
	
	public ExcecaoArvoreVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}
