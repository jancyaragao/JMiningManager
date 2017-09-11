package git.testes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;

public class Principal {
	
	public static void main(String[] args) throws NoHeadException, GitAPIException, RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		
		Scanner input = new Scanner(System.in);
		int opcao = 1;
		
		while(opcao != 0) {
			
			System.out.print("Escolha qual parâmetro você quer utilizar como filtro:"
					+ "\n\t1 - Listar arquivos por commit"
					+ "\n\t2 - Listar arquivos por data"
					+ "\n\t3 - Listar autores por commit"
					+ "\n\t4 - Listar autores por data"
					+ "\n\t5 - Listar por intervalo de commit"
					+ "\n\t6 - Listar por intervalo de data"
					+ "\n\t7 - Encerrar aplicação"
					+ "\n\nOpção desejada: ");
			opcao = input.nextInt();
			
			if(opcao == 1) {
				
				System.out.print("Informe o repositório a ser analisado: ");
				String path = input.nextLine();
				RepositoryConnection c = new RepositoryConnection(path);
				
				System.out.print("Informe o primeiro commit: ");
				String r1 = input.nextLine();
				System.out.print("Informe o segundo commit: ");
				String r2 = input.nextLine();
				Iterable<RevCommit> revisoes = c.buscarPorRevisao(r1, r2);

				Map<String, Integer> contagem = new HashMap<String, Integer>();
				
				for (RevCommit rev : revisoes) {
					String autor = rev.getAuthorIdent().getName();
					
					Integer i = contagem.get(autor);
					
					if (i == null) {
						contagem.put(autor, 1);
					} else {
						contagem.put(autor, i + 1);
					}
				}
				
				Integer numero_modificacoes = 0;
				String autor_mais_modificacoes = null;
				
				for (String autor : contagem.keySet()) {
					Integer i = contagem.get(autor);
					
					if (i > numero_modificacoes) {
						numero_modificacoes = i;
						autor_mais_modificacoes = autor;
					}
				}
				
				System.out.println("Autor mais produtivo no período é: " + autor_mais_modificacoes + 
						" com " + numero_modificacoes + " revisões.");
				
			} else if (opcao == 2) {
				
			} else if (opcao == 3) {
				
			} else if (opcao == 4) {
				
			} else if (opcao == 5) {
				
			} else if (opcao == 6) {
				
			} else if (opcao == 7) {
				System.out.println("Encerrando aplicação...");
			} else {
				System.out.println("Opção inválida. Tente novamente!");
			}
			
		}
		
		input.close();
		
	}

}
