package com.amdtelecom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

public class Solution {

	public String findSeven(int[] arr) {
		OptionalInt optional = Arrays.stream(arr).filter(x->x==7).findFirst();
		if(optional.isPresent())
			return "Found";
		return "there is no 7 in the array";

	}

	public int digitSum(int number) {

		int sum = String.valueOf(number)
				.chars()
				.map(Character::getNumericValue)
				.sum();

		if(sum>9)
		{
			return digitSum(sum);
		}

		return sum;
	}


	public String doRemake(String input) {
		Set<String> setVowles= new HashSet<>();
		setVowles.add("a");
		setVowles.add("e");
		setVowles.add("i");
		setVowles.add("o");
		setVowles.add("u");
		setVowles.add("A");
		setVowles.add("E");
		setVowles.add("I");
		setVowles.add("O");
		setVowles.add("U");
		StringBuffer reversedSentence = new StringBuffer();
		String [] words = input.split(" ");
		
		for(int i=0; i<words.length; i++) {
			StringBuffer word = new StringBuffer();
			String firstWord = words[i].substring(0,1);
			String remainingWord = null;
			if(i==words.length-1) {
			remainingWord= words[i].substring(1,(words[i].length())-1);
			}
			else
			remainingWord = words[i].substring(1);
				

			if(setVowles.contains(firstWord)) {
				word.append(firstWord);
				word.append(remainingWord);
				if(i==words.length-1)
					word.append("way.");
				else
				word.append("way");
			}
			else {
				String remainingWordFirstWord= remainingWord.substring(0,1);
				String remainingWordRemaing= remainingWord.substring(1);
				if(i==0) {
					word.append(Character.toUpperCase(remainingWordFirstWord.charAt(0)));
				}
				else
					word.append(remainingWordFirstWord);
				word.append(remainingWordRemaing);
				word.append(Character.toLowerCase(firstWord.charAt(0)));
				if(i==words.length-1)
					word.append("ay.");
				else
					word.append("ay");
			}

			reversedSentence.append(word);
			reversedSentence.append(" ");
		}

		return reversedSentence.toString().trim();

	}
}
