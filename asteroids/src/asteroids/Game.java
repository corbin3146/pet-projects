package asteroids;

public class Game {
	Game(){
		UserInterface UI = new UserInterface();
		Player P = new Player(0,0,0,0);
		while(true) {
			moveObjects();
			checkCollisions();
		}
	}
	void checkInput() {
		
	}
	void moveObjects() {
		
	}
	void checkCollisions(){
		
	}
}
