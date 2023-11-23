package Blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class BlacklistServiceImpl implements BlacklistService {
    private static Set<String> blacklistedDomains;
   public BlacklistServiceImpl(){
        Properties props = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream("blacklistedDomains.properties");
            props.load(input);
            blacklistedDomains =  Set.of(props.getProperty("blacklistedDomains").split(";"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
    public boolean check(Query query) {
        return BlacklistServiceImpl.blacklistedDomains.stream().noneMatch(s -> query.getUrl().contains(s));
    }
}
