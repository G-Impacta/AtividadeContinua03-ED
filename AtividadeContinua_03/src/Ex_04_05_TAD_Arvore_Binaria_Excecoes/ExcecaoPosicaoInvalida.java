package Ex_04_05_TAD_Arvore_Binaria_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoPosicaoInvalida extends RuntimeException{
	
	public ExcecaoPosicaoInvalida(String mensagemErro) {
		
		super(mensagemErro);
	}
}
