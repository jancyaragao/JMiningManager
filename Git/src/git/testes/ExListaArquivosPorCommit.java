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
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.util.io.DisabledOutputStream;

// Exemplo ainda em vers�o parcial e n�o finalizada
public class ExListaArquivosPorCommit {

	public static void main(String[] args) throws IOException, NoWorkTreeException, GitAPIException {
		Git git = Git.open(new File("C:/Users/felipe/git/wicket"));
		Repository repositorio = git.getRepository();

		// 8b6fcd869ceb96f7b4ea003d3d5665a1626390ad > Adi��o de arquivo
		// 266c90037d689f47bf45722532536716dc9f5b06 > Remo��o de arquivo
		// 7e032d211feecf00b93f72fd0ee49c42abf08c61 > 2 pais (merge)
		ObjectId obj_initial = repositorio.resolve("7729e4d469ff728a66dfec3818e584a504b9753c");
		ObjectId obj_final = repositorio.resolve("1421ea2dc9207143cdadb735f3c79421674d924d");

		RevWalk rw = new RevWalk(repositorio);
		//RevCommit commit = rw.parseCommit(obj_initial);
		RevCommit rinitial = rw.parseCommit(obj_initial);
		RevCommit rfinal = rw.parseCommit(obj_final);
		//RevCommit parent = rw.parseCommit(commit.getParent(0).getId());

		DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
		df.setRepository(repositorio);
		df.setDiffComparator(RawTextComparator.DEFAULT);
		df.setDetectRenames(true);
		// df.setDiffAlgorithm(MyersDiff.INSTANCE);
		// df.getRenameDetector().setRenameLimit(1000);
		// df.getRenameDetector().setRenameScore(50);
		// System.out.println(df.getRenameDetector().getRenameLimit());
		// System.out.println(df.getRenameDetector().getRenameScore());
		// System.out.println(df.getRenameDetector().getBreakScore());

		//List<DiffEntry> diffs = df.scan(parent.getTree(), commit.getTree());
		List<DiffEntry> diffs = df.scan(rinitial.getTree(), rfinal.getTree());
		ArrayList<String> modificados = new ArrayList<>();
		ArrayList<String> adicionados = new ArrayList<>();
		ArrayList<String> removidos = new ArrayList<>();
		ArrayList<String> renomeados = new ArrayList<>();
		ArrayList<String> copiados = new ArrayList<>();
		
		for (DiffEntry diff : diffs) {
			System.out.println("Tipo da altera��o: " + diff.getChangeType().name());
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
