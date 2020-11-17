public class Palindrome {
    public static void main(final String[] args) {
        final char[][] words = {
                {'a', 'n', 'i', 'm', 'a', 'l'},
                {'r', 'a', 'd', 'a', 'r'},
                {'r', 'e', 's', 'u', 'm', 'a'},
                {'r', 'e', 's', 's', 'a', 's', 's', 'e', 'r'},
                {'r', 'e', 'l', 'a', 'c', 'e', 'r'},
                {'k', 'a', 'y', 'a', 'k'},
                {'v', 'i', 'v', 'e', ' ', 'J', 'a', 'v', 'a', ' ', '!'}
        };

        Palindrome.printWords(Palindrome.reverseWords(words));
    }

    private static void printWord(final char[] word) {
        for (final char element: word) {
            System.out.print(element);
        }

        System.out.println();
    }

    private static void printWords(final char[][] words) {
        for (final char[] word: words) {
            Palindrome.printWord(word);
        }
    }

    private static boolean isPalindrome(final char[] word) {
        for (int i = 0; i < word.length / 2; i++) {
            if (word[i] != word[word.length - i - 1]) {
                return false;
            }
        }

        return true;
    }

    private static void printPalindromes(final char[][] words) {
        for (final char[] word: words) {
            if (Palindrome.isPalindrome(word)) {
                Palindrome.printWord(word);
            }
        }
    }

    private static char[] reverseWord(final char[] word) {
        char tmp;

        for (int i = 0; i < word.length / 2; i++) {
            tmp = word[i];
            word[i] = word[word.length - i - 1];
            word[word.length - i - 1] = tmp;
        }

        return word;
    }

    private static char[][] reverseWords(final char[][] words) {
        for (final char[] word: words) {
            Palindrome.reverseWord(word);
        }

        return words;
    }
}
