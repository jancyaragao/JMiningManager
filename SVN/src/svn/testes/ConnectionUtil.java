package svn.testes;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public abstract class ConnectionUtil {

	public static SVNRepository conexao() throws SVNException {
		
		String url = "http://argouml.tigris.org/svn/argouml/trunk";
		String usuario = "guest";
		String senha = "";
		
		DAVRepositoryFactory.setup();
		SVNRepository repositorio = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		ISVNAuthenticationManager autenticador = SVNWCUtil.createDefaultAuthenticationManager(usuario, senha.toCharArray());
		repositorio.setAuthenticationManager(autenticador);
		
		return repositorio;
		
	}
	
}
