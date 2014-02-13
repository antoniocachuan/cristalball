package com.antoniocachuan.esferadelfuturo;

import java.util.Random;

public class CrystalBall {

	public String getAnswer(){
		String[] answers = {
				"Es muy probable",
				"Definitivamente Si",
				"Definitivamente No",
				"Todo es posible",
				"Mejor paso",
				"Esta vez no",
				"A nada",
				"Para la próxima",
				"Para que responder",
				"Tú sabes la respuesta"
		};
		String answer="";
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt(answers.length);
		answer =answers[randomNumber];
		return answer;
	}
}
