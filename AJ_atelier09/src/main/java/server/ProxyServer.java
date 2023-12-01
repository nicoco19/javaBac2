package server;

import domaine.Query;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;

import java.util.Scanner;

public class ProxyServer {
    Scanner scanner = new Scanner(System.in);

    QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void startServer(){

        while (true){

            System.out.print("url : ");
            String url = scanner.nextLine();
            Query query = queryFactory.getQuery();
            query.setUrl(url);
            query.setMethod(Query.QueryMethod.GET);
            QueryHandler queryHandler = new QueryHandler(query);
            queryHandler.start();

        }
    }
}
