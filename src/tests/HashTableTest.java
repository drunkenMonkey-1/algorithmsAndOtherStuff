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
        testTable.put("Moritz", 6);
        testTable.put("Moritz", 7);
        testTable.put("Moritz", 8);
        testTable.debugList();
    }

}