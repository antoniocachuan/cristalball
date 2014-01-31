package com.example.crystalball;

import java.util.Random;

public class CrystalBall {

	public String getAnswer(){
		String[] answers = {
				"It is ceratin",
				"It is decidedly so",
				"All signs say YES",
				"My reply is no",
				"It is doubtful",
				"Better not tell you now"
		};
		String answer="";
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(answers.length);
		answer =answers[randomNumber];
		return answer;
	}
}
