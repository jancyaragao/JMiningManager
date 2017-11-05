package jmm.common;

import java.util.Collection;

import org.tmatesoft.svn.core.SVNException;

// TODO: Definir m�todo para todas as demais opera��es
// TODO: Adicionar m�todos para listar arquivos por data (svn e git)
public interface JMMRepository {

	public Collection<?> searchByReviosion(long r_initial, long r_final) throws SVNException;

	public Collection<?> searchByReviosion(String r_initial, String r_final) throws NumberFormatException, SVNException;

	// TODO: Ver a possibilidade de implementar tamb�m a listagem por revis�o
	public String listAuthorByCommit(String d1, String d2) throws SVNException;

}
