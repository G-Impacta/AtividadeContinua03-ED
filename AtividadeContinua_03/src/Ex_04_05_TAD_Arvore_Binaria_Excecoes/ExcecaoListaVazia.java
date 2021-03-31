package Ex_04_05_TAD_Arvore_Binaria_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoListaVazia extends RuntimeException{
	
	public ExcecaoListaVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}
