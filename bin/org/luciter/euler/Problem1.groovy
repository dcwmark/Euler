package org.luciter.euler

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 * Find the sum of all the multiples of 3 or 5 below 1000.
**/
class Problem1 {

    static main(args) {
        if (args.length > 1) {
            throw new IllegalArgumentException(showUsage())
        }

        def upperRange = args.length == 0 ? 99999999 : Long.parseLong(args[0])
        private def problem1 = new Problem1()
        
        private def startAt = System.currentTimeMillis()

        private def ans = problem1.calculate(3, upperRange) \
                        + problem1.calculate(5, upperRange) \
                        - problem1.calculate(15, upperRange)

        private def endAt = System.currentTimeMillis()

        println("* ========================================================= *")
        println(" Upper Range                    :: " + upperRange)
        println(" Problem1 calculate solution    :: " + ans)
        println(" Problem1 Started at            :: " + startAt)
        println(" Problem1 Ended   at            :: " + endAt)
        println(" Duration                       :: "
               + (endAt - startAt) / 1000 + ' sec.')
        println("* ========================================================= *")

        startAt = System.currentTimeMillis()

        ans = problem1.bruteForce(upperRange)

        endAt = System.currentTimeMillis()

        println("* ========================================================= *")
        println(" Upper Range                    :: " + upperRange)
        println(" Problem1 brute force solution  :: " + ans)
        println(" Problem1 Started at            :: " + startAt)
        println(" Problem1 Ended   at            :: " + endAt)
        println(" Duration                       :: "
               + (endAt - startAt) / 1000 + ' sec.')
        println("* ========================================================= *")
    }

    static showUsage = {
        return new StringBuilder(100)
               .append("Usage: org.luciter.euler.Problem1")
               .append(" -or - org.luciter.euler.Problem1 <number:upper range")
               .toString()
    }

    def calculate = {factor, range ->
        private Long hiRange = (range / factor) * factor
        private Long factorial = hiRange / factor
        private Long ans = factor * (factorial * (factorial + 1)) / 2
    }

    def bruteForce = { upper ->
        private def Long ans = 8
        (6..upper).each { it ->
            ans += (it % 3 == 0 || it % 5 == 0) ? it : 0
        }
        ans
    }
}
