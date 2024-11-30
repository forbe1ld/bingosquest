import java.util.Random;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EnemyCatalog {
	public static Enemy enemyFromCatalog(int id) {
		switch(id) {
		case 0:
			Enemy brambleBlight = new Enemy();
			brambleBlight.setDmg(2).setHpMax(12).setName("Brableblight").setXp(7).setGold(4);
			brambleBlight.setI1(new Image("img/BramblBlight_frame1.png"));
			brambleBlight.setI2(new Image("img/BramblBlight_frame2.png"));
			return brambleBlight;
		case 1:
			Enemy possesedKnight = new Enemy() {
				@Override
				public void logic(Enemy en, Player pl, Stage stage) {
					Random r = new Random();
					int rand = r.nextInt(1, 20);
					if(rand == 20) {
						pl.setHp(pl.getHp() - (2 * en.getDmg()));
					}
					pl.setHp(pl.getHp() - en.getDmg());
					
				}
			};
			possesedKnight.setDmg(6).setHpMax(25).setName("Possesed Knight").setXp(25).setGold(20);
			possesedKnight.setI1(new Image("img/PossesedKnight_Frame1.png"));
			possesedKnight.setI2(new Image("img/PossesedKnight_Frame2.png"));
			return possesedKnight;
		case 2:
			Enemy hellHound = new Enemy();
			hellHound.setDmg(7).setHpMax(15).setName("Hell Hound").setXp(22).setGold(12);
			hellHound.setI1(new Image("img/HellHound_frame1.png"));
			hellHound.setI2(new Image("img/HellHound_frame2.png"));
			return hellHound;
		case 3:
			Enemy pyromancer = new Enemy() {
				@Override
				public void logic(Enemy en, Player pl, Stage stage) {
					if(en.getHp() < 5) {
						en.setHp(en.getHp() + 8);
					}
					else {
						pl.setHp(pl.getHp() - en.getDmg());
					}
				}
					
			};
			pyromancer.setDmg(5).setHpMax(18).setName("Pyromancer").setXp(21).setGold(15);
			pyromancer.setI1(new Image("img/Pyromancer_frame1.png"));
			pyromancer.setI2(new Image("img/Pyromancer_frame2.png"));
			return pyromancer;
		case 4:
			Enemy runicMonolith = new Enemy();
			runicMonolith.setDmg(3).setHpMax(30).setName("Runic Monolith").setXp(18).setGold(25);
			runicMonolith.setI1(new Image("img/RunicMonolith_frame1.png"));
			runicMonolith.setI2(new Image("img/RunicMonolith_frame2.png"));
			return runicMonolith;
			
		}
		return null;
	}
}
