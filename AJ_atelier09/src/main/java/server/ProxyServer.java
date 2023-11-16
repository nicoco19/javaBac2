package server;

import domaine.Query;
import domaine.QueryFactoryImpl;

import java.util.Scanner;

public class ProxyServer {
    Scanner scanner = new Scanner(System.in);

    QueryFactoryImpl queryFactory = new QueryFactoryImpl();

    public ProxyServer(QueryFactoryImpl queryFactory) {
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
