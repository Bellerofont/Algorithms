def reverseInParentheses(inputString: String): String = {
    if (inputString.contains("(")) {
        val start = inputString.lastIndexOf("(")
        val end = inputString.substring(start).indexOf(")")
        val reverse = inputString.substring(start,start + end + 1).reverse.replaceAll("[()]","")
        reverseInParentheses(new StringBuilder(inputString).replace(start, start + end + 1, reverse).toString)
    }
    else inputString
}
