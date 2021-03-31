package Ex_02_03_TAD_Arvore_Generica_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoIndiceForaLimites extends RuntimeException{
	
	public ExcecaoIndiceForaLimites(String mensagemErro) {
		
		super(mensagemErro);
	}
}
