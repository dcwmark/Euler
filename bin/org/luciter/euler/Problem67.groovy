package org.luciter.euler

import org.apache.commons.lang.StringUtils

/**
 * Using an efficient algorithm to find the maximal sum in the triangle.
 * 
 * Strategic solution based on
 * http://blog.functionalfun.net/2008/08/project-euler-problems-18-and-67.html
 * 
 * @author dmark
**/

class Problem67 {

    static main(args) {
        if (null == args || args.length < 1) {
            throw new IllegalArgumentException(showUsage())
        }

        println("In file name::" + args[0])

        def problem67 = new Problem67()

        def startAt = System.currentTimeMillis()

        private File iFile = new File(args[0])
        if (iFile.exists()) {
            if (iFile.size() < 1) {
                throw new IllegalStateException("File is empty; aborting ...")
            } else {
                iFile.eachLine {
                    problem67.loadTriangle(it)
                }
            }
        } else {
            throw new FileNotFoundException(
                iFile.getName() + " specified not found.")
        }

        problem67.process()

        def endAt =  System.currentTimeMillis()
        def duration = endAt - startAt
        println("*===========================================================*")
        println(" Max Path    :: " + problem67.finalRowMax)
        println(" Max Path Sum:: " + problem67.finalRowMax.max())
        println(" Problem67 Started at:: " + startAt)
        println(" Problem67 Ended   at:: " + endAt)
        println(" Duration            :: " + duration / 1000 + ' sec. ')
        println("*===========================================================*")
    }

    static def showUsage = {
        return new StringBuilder(100)
               .append("Usage: org.luciter.euler.Prolem67 ")
               .append("<File Name>")
               .toString()
    }

    def triangle = new ArrayList()

    /**
     * Load the triangle as read from input file.
    **/
    def loadTriangle = {
        def strElements = it.split(" ")
        /*
         * Make sure that input file is in correct format.
         * Input line entries need to be incrementing one by one.
         */
        if (triangle.size() + 1 != strElements.size()) {
            throw new IllegalArgumentException(
                new StringBuilder(100)
                .append("Prior entry length::")
                .append(triangle.size())
                .append(" Current input line length::")
                .append(strElements.size())
                .append(".  They should be incrementing one by one.")
                .toString()
            )
        }

        triangle.add( numberFix(strElements) )
    }

    /**
     * Converts input String values to Long values,
    **/
    def numberFix = { strElements ->
        /* Converts String to Long */
        def lngElements = new ArrayList<Long>(strElements.size())
        strElements.each {
            if (!it.isNumber()) {
                throw new NumberFormatException(
                    new StringBuilder(50)
                    .append("An input value '").append(it)
                    .append("' is *NOT* numeric.  Task aborted!")
                    .toString()
                )
            }
            lngElements.add(new Long(it))
        }
        return lngElements
    }

    /** Sum of all paths **/
    def finalRowMax = []

    /**
     * Main process path.
    **/
    def process = {

        triangle.eachWithIndex { thisRow, indxRow ->
            def thisRowMax = calcRowMax(thisRow, indxRow)
            finalRowMax = thisRowMax.clone()
        }

    }

    /**
     * Calculate all possible max values for each row of the triangle.
    **/
    def calcRowMax = { thisRow, indxRow ->
        def thisRowMax = []
        thisRow.eachWithIndex {thisCol, indxCol ->
            if (indxRow < 1) {
                /* Top of the triangle */
                thisRowMax[indxCol] = thisCol
            } else {
                if (indxCol < 1) {
                    /* Leftmost column */
                    thisRowMax[indxCol] = finalRowMax[indxCol].value + thisCol
                } else if (thisRow.size() == (indxCol + 1)) {
                    /* Rightmost column */
                    thisRowMax[indxCol] =
                        finalRowMax[indxCol - 1].value + thisCol
                } else {
                    /* 
                     * When the cell is in the middle, check the upper row's
                     * left and right path for the greater of the two.
                     */
                    def leftPath = finalRowMax[indxCol].value + thisCol
                    def ritePath = finalRowMax[indxCol - 1].value + thisCol
                    thisRowMax[indxCol] =
                        leftPath > ritePath ? leftPath : ritePath
                }
            }
        }
        thisRowMax
    }
}
