package org.luciter.euler

import org.codehaus.groovy.tools.shell.util.ANSI;

/**
 * By considering the terms in the Fibonacci sequence whose values do not
 * exceed four million.
 * By starting with 1 and 2, the first 10 terms will be:
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ... 
 * Find the sum of the even-valued terms.
 * 
 * @author dmark
**/
class Problem2 {

    static main(args) {
        if (args.length > 1) {
            throw new IllegalArgumentException(showUsage())
        }

        private def Long upperRange =
            args.length == 0 ? 999999999 : Long.parseLong(args[0])

        private def problem2 = new Problem2()

        private def startAt = System.currentTimeMillis()

        private def ans = problem2.algorithm(upperRange)

        private def endAt = System.currentTimeMillis()

        println("* ========================================================= *")
        println(" Upper Range                    :: " + upperRange)
        println(" Problem2 algorithmic solution  :: " + ans)
        println(" Problem2 Started at            :: " + startAt)
        println(" Problem2 Ended   at            :: " + endAt)
        println(" Duration                       :: "
               + (endAt - startAt) / 1000 + ' sec.')
        println("* ========================================================= *")

        startAt = System.currentTimeMillis()

        ans = problem2.solve(upperRange)

        endAt = System.currentTimeMillis()

        println("* ========================================================= *")
        println(" Upper Range                    :: " + upperRange)
        println(" Problem2 brute force solution  :: " + ans)
        println(" Problem2 Started at            :: " + startAt)
        println(" Problem2 Ended   at            :: " + endAt)
        println(" Duration                       :: "
               + (endAt - startAt) / 1000 + ' sec.')
        println("* ========================================================= *")
    }

    static showUsage = {
        return new StringBuilder(100)
               .append("Usage: org.luciter.euler.Problem2")
               .append(" -or - org.luciter.euler.Problem2 <number:upper range")
               .toString()
    }

    def algorithm = { upper ->
        private Long ans = 2;
        private fibo = [1, 2, 3];
        while(fibo[fibo.size() - 1] < upper) {
            fibo[0] = fibo[1];
            fibo[1] = fibo[2];
            fibo[2] = fibo[0] + fibo[1];
            
            ans += fibo[2] % 2 == 0 ? fibo[2] : 0;
        }
        ans;
    }

    def solve = { upper ->
        private Long prevNumb = 0
        private fibo = [1, 2]
        private Long ans = 2
        while(fibo[fibo.size() - 1] < upper) {
            fibo[fibo.size()] =
                fibo[fibo.size() - 1] + fibo[fibo.size() - 2];

            ans += fibo[fibo.size() - 1] % 2 == 0 ?
                            fibo[fibo.size() - 1] : 0;
        } 
        ans
    }
}
