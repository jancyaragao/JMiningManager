package git.testes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.util.io.DisabledOutputStream;

public class ExListaArquivosPorData {

	public static void main(String[] args) throws IOException, NoWorkTreeException, GitAPIException {
		RepositoryConnection c = new RepositoryConnection("C:/Users/jancy/git/wicket");
		
		Iterable<RevCommit> revisoes = c.buscarPorData("01/03/2017", "20/03/2017");
		
		DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
		df.setRepository(c.getRepository());
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);
		
		List<DiffEntry> diffs = df.scan(parent.getTree(), commit.getTree());
		ArrayList<String> modificados = new ArrayList<>();
		ArrayList<String> adicionados = new ArrayList<>();
		ArrayList<String> removidos = new ArrayList<>();
		ArrayList<String> renomeados = new ArrayList<>();
		ArrayList<String> copiados = new ArrayList<>();
		
		for (DiffEntry diff : diffs) {
			System.out.println("Tipo da alteração: " + diff.getChangeType().name());
			System.out.println("Caminho novo     : " + diff.getNewPath());
			System.out.println("Caminho antigo   : " + diff.getOldPath());
			System.out.println("-------------------------------------");
			
			if (diff.getChangeType().name().equals("MODIFY") && !modificados.contains(diff.getNewPath())) {
				modificados.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("ADD") && !adicionados.contains(diff.getNewPath())) {
				adicionados.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("DELETE") && !removidos.contains(diff.getOldPath())) {
				removidos.add(diff.getOldPath());
			} else if (diff.getChangeType().name().equals("RENAME") && !renomeados.contains(diff.getNewPath())) {
				renomeados.add(diff.getNewPath());
			} else if (diff.getChangeType().name().equals("COPY") && !copiados.contains(diff.getNewPath())) {
				copiados.add(diff.getNewPath());
			} 
		}
		
		if (modificados.size() > 1) {
			System.out.println(modificados.size() + " arquivos foram modificados: " + modificados);
		} else {
			System.out.println(modificados.size() + " arquivo foi modificado: " + modificados);
		}
		
		if (adicionados.size() > 1) {
			System.out.println(adicionados.size() + " arquivos foram adicionados: " + adicionados);
		} else {
			System.out.println(adicionados.size() + " arquivo foi adicionado: " + adicionados);
		}
		
		if (removidos.size() > 1) {
			System.out.println(removidos.size() + " arquivos foram removidos: " + removidos);
		} else {
			System.out.println(removidos.size() + " arquivo foi removido: " + removidos);
		}
		
		if (renomeados.size() > 1) {
			System.out.println(renomeados.size() + " arquivos foram renomeados: " + renomeados);
		} else {
			System.out.println(renomeados.size() + " arquivo foi renomeado: " + renomeados);
		}
		
		if (removidos.size() > 1) {
			System.out.println(copiados.size() + " arquivos foram copiados: " + copiados);
		} else {
			System.out.println(copiados.size() + " arquivo foi copiado: " + copiados);
		}
		
		System.out.println("\nTotal: " + diffs.size() + " arquivos");
		
		df.close();
		rw.close();
	}

}
