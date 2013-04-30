package org.luciter.euler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Problem67 Tester.
 * 
 * @author dmark
**/
class Problem67Test extends GroovyTestCase {

    /**
     * @throws java.lang.Exception
    **/
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
    **/
    @After
    public void tearDown() throws Exception {
    }

    def tenRowGenerator = { seed ->
        def rows = []
        for(indxRow in 0..9) {
            def col = new String()
            for(indxCol in 0..indxRow) {
                col += "${seed} "
            }
            rows[indxRow] = col
        }
        rows
    }

    void testProcess() {
        def generatedRows
        def problem67

        /* ---------------------------------------------------------------- */
        /* With all things being equal, max should be the same as row count */
        /* ---------------------------------------------------------------- */
        problem67 = new Problem67()
        generatedRows = tenRowGenerator(1)
        generatedRows.each {
            problem67.loadTriangle(it)
        }
        problem67.process()
        
        assertEquals generatedRows.size, problem67.finalRowMax.max()
        /* ---------------------------------------------------------------- */

        /* ----------------------------------------------------------- */
        /* With all things being equal, max should double of row count */
        /* ----------------------------------------------------------- */
        problem67 = new Problem67()
        generatedRows = tenRowGenerator(2)
        generatedRows.each {
            problem67.loadTriangle(it)
        }
        problem67.process()
        
        assertEquals generatedRows.size * 2, problem67.finalRowMax.max()
        /* ----------------------------------------------------------- */

        /* ----------------------------------------------------------- */
        /* With all things being equal, max should thrice of row count */
        /* ----------------------------------------------------------- */
        problem67 = new Problem67()
        generatedRows = tenRowGenerator(3)
        generatedRows.each {
            problem67.loadTriangle(it)
        }
        problem67.process()
        
        assertEquals generatedRows.size * 3, problem67.finalRowMax.max()
        /* ----------------------------------------------------------- */
    }

    void testPredeterminedMax() {
        def problem67
        def rows

        problem67 = new Problem67()
        rows = []
        rows.add "2"
        rows.add "1 2"
        rows.add "1 1 2"
        rows.add "1 1 1 2"
        rows.add "1 1 1 1 2"
        rows.add "1 1 1 1 1 2"
        rows.add "1 1 1 1 1 1 2"
        rows.add "1 1 1 1 1 1 1 2"
        rows.add "1 1 1 1 1 1 1 1 2"
        rows.add "1 1 1 1 1 1 1 1 1 2"
        rows.each {
            problem67.loadTriangle(it)
        }
        problem67.process()
        
        assertEquals 20, problem67.finalRowMax.max()

        problem67 = new Problem67()
        rows = []
        rows.add "3"
        rows.add "1 3"
        rows.add "1 1 3"
        rows.add "1 1 1 3"
        rows.add "1 1 1 1 3"
        rows.add "1 1 1 1 1 3"
        rows.add "1 1 1 1 1 1 3"
        rows.add "1 1 1 1 1 1 1 3"
        rows.add "1 1 1 1 1 1 1 1 3"
        rows.add "1 1 1 1 1 1 1 1 1 3"
        rows.each {
            problem67.loadTriangle(it)
        }
        problem67.process()
        
        assertEquals 30, problem67.finalRowMax.max()

        problem67 = new Problem67()
        rows = []
        rows.add "3"
        rows.add "1 3"
        rows.add "1 3 1"
        rows.add "1 3 1 1"
        rows.add "1 3 1 1 1"
        rows.add "1 1 3 1 1 1"
        rows.add "1 1 1 3 1 1 1"
        rows.add "1 1 1 1 3 1 1 1"
        rows.add "1 1 1 1 1 3 1 1 1"
        rows.add "1 1 1 1 1 1 3 1 1 1"
        rows.each {
            problem67.loadTriangle(it)
        }
        problem67.process()
        
        assertEquals 30, problem67.finalRowMax.max()
    }

    void testGenLargeSample() {
        def rows = []
        for(indxRow in 0..999) {
            def col = new String()
            for(indxCol in 0..indxRow) {
                col += "1 "
            }
            rows[indxRow] = col
        }
        def problem67 = new Problem67()
        rows.each{
            problem67.loadTriangle(it)
        }
        problem67.process()
    }
}
