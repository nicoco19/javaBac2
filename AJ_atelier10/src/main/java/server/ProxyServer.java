package server;

import java.util.Scanner;

import Blacklist.BlacklistService;
import Blacklist.BlacklistServiceImpl;
import domaine.Query;
import domaine.Query.QueryMethod;
import domaine.QueryFactory;

public class ProxyServer {
	
	QueryFactory queryFactory;

	public ProxyServer(QueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	public void startServer() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				BlacklistService blacklistService = new BlacklistServiceImpl();
				String url = scanner.nextLine();
				Query query = this.queryFactory.getQuery();
				query.setMethod(QueryMethod.GET);
				query.setUrl(url);
				boolean result = blacklistService.check(query);
				if (!result){
					System.out.println("le site est dans la liste noir");
				}else{
					System.out.println("le site n'est pas dans la liste noire");
					QueryHandler queryHandler = new QueryHandler(query);
					queryHandler.start();
				}

			}
		}
	}

}
