package jmm.common;

import java.util.Collection;

import org.tmatesoft.svn.core.SVNException;

// TODO: Definir método para todas as demais operações
// TODO: Adicionar métodos para listar arquivos por data (svn e git)
public interface JMMRepository {

	public Collection<?> searchByReviosion(long r_initial, long r_final) throws SVNException;

	public Collection<?> searchByReviosion(String r_initial, String r_final) throws NumberFormatException, SVNException;

	// TODO: Ver a possibilidade de implementar também a listagem por revisão
	public String listAuthorByCommit(String d1, String d2) throws SVNException;

}
