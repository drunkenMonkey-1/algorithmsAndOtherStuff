package tests;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fhv.at.mwi.hash_table.CollisionStrategy;
import fhv.at.mwi.hash_table.MHashTable;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void testHash(){
        MHashTable<String, Integer> testTable = new MHashTable(CollisionStrategy.DOUBLE_HASHING);
        testTable.put("Moritz", 5);
        testTable.put("Pete", 6);
        testTable.put("Walther White", 7);
        testTable.put("Sanchez", 8);
        testTable.put("Sanchez2", 8);
        testTable.put("Sanchez3", 8);
        testTable.put("Sanchez4", 8);
        testTable.put("Sanchez5", 8);
        testTable.put("Wiz Khalifa", 8);
        testTable.put("Snoop Dogg", 8);
        testTable.put("Kendrick Lamar", 8);
        testTable.debugList();

        testTable.get("Moritz");

    }

}