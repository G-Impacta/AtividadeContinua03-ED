package Ex_04_05_TAD_Arvore_Binaria_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoIndiceForaLimites extends RuntimeException{
	
	public ExcecaoIndiceForaLimites(String mensagemErro) {
		
		super(mensagemErro);
	}
}
