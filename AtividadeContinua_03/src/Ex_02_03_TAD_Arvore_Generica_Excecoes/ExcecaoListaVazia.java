package Ex_02_03_TAD_Arvore_Generica_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoListaVazia extends RuntimeException{
	
	public ExcecaoListaVazia(String mensagemErro) {
		
		super(mensagemErro);
	}
}
