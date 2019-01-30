
public class Palindrome {

	public static void main(String[] args) {
		char [][] words = {
			    { 'a', 'n', 'i', 'm', 'a', 'l' },
			    { 'r', 'a', 'd', 'a', 'r' },
			    { 'r', 'e', 's', 'u', 'm', 'a' },
			    { 'r', 'e', 's', 's', 'a', 's', 's', 'e', 'r' },
			    { 'r', 'e', 'l', 'a', 'c', 'e', 'r' },
			    { 'k', 'a', 'y', 'a', 'k' },
			    { 'v', 'i', 'v', 'e', ' ', 'J', 'a', 'v', 'a', ' ', '!' }
		};
		
		printWords(reverseWords(words));
	}
	
	private static void printWord(char[] word) {
		for (int i = 0; i < word.length; i++) {
			System.out.print(word[i]);
		}
		System.out.println();
	}
	
	private static void printWords(char[][] words) {
		for (int i = 0; i < words.length; i++) {
			printWord(words[i]);
		}
	}
	
	private static boolean isPalindrome(char[] word) {
		for (int i = 0; i < word.length / 2; i++) {
			if (word[i] != word[word.length - i - 1]) {
				return false;
			}
		}
		return true;
	}
	
	private static void printPalindromes(char[][] words) {
		for (int i = 0; i < words.length; i++) {
			if (isPalindrome(words[i])) {
				printWord(words[i]);
			}
		}
	}
	
	private static char[] reverseWord(char[] word) {
		char tmp;
		
		for (int i = 0; i < word.length / 2; i++) {
			tmp = word[i];
			word[i] = word[word.length - i - 1];
			word[word.length - i - 1] = tmp;
		}
		
		return word;
	}
	
	private static char[][] reverseWords(char[][] words) {
		for (int i = 0; i < words.length; i++) {
			reverseWord(words[i]);
		}
		return words;
	}
}
