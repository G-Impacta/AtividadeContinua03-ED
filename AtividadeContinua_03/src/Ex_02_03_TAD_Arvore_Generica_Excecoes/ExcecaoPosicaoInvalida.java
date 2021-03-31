package Ex_02_03_TAD_Arvore_Generica_Excecoes;

@SuppressWarnings("serial")
public class ExcecaoPosicaoInvalida extends RuntimeException{
	
	public ExcecaoPosicaoInvalida(String mensagemErro) {
		
		super(mensagemErro);
	}
}
