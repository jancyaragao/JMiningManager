package svn.testes;

import java.util.Collection;
import java.util.Date;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class RepositoryConnection {
	
	private SVNRepository repositorio;

	public RepositoryConnection(String url, String usuario, String senha) throws SVNException {
		
//		String url = "http://argouml.tigris.org/svn/argouml/trunk";
//		String usuario = "guest";
//		String senha = "";
		
		DAVRepositoryFactory.setup();
		repositorio = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		ISVNAuthenticationManager autenticador = SVNWCUtil.createDefaultAuthenticationManager(usuario, senha.toCharArray());
		repositorio.setAuthenticationManager(autenticador);
		
	}
	
	public Collection<?> buscarPorRevisao(long revisaoInicial, long revisaoFinal) throws SVNException {
		
		Collection<?> revisoes = repositorio.log(new String[] { "" }, null, revisaoInicial, revisaoFinal, true, true);
		return revisoes;
		
	}
	
	public Collection<?> buscarPorData(String dataInicial, String dataFinal) throws SVNException {
		
		Date inicio = DataUtil.converterStringParaDate(dataInicial);
		Date fim = DataUtil.converterStringParaDate(dataFinal);
		
		long revisaoInicial = repositorio.getDatedRevision(inicio);
		long revisaoFinal = repositorio.getDatedRevision(fim);
		
		Collection<?> revisoes = repositorio.log(new String[] { "" }, null, revisaoInicial, revisaoFinal, true, true);
		return revisoes;
		
	}
	
}
