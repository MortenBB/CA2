/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author Morten
 */
public class SchemaBuilder {

    public static void main(String[] args) {
        HashMap<String, Object> puproperties = new HashMap();
        puproperties.put("javax.persistence.sql-load-script-source", "Scripts/ClearDB.sql");
        Persistence.generateSchema("pu", puproperties);

        puproperties.remove("javax.persistence.sql-load-script-source");
        Persistence.generateSchema("pu", puproperties);
    }
}
