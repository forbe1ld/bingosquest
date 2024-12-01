import java.util.Random;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EnemyCatalog {
	public static Enemy enemyFromCatalog(int id) {
		switch(id) {
		case 0:
			Enemy brambleBlight = new Enemy();
			brambleBlight.setDmg(2).setHpMax(20).setName("Brableblight").setXp(7).setGold(4);
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
			possesedKnight.setDmg(6).setHpMax(35).setName("Possesed Knight").setXp(25).setGold(20);
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
			pyromancer.setDmg(5).setHpMax(26).setName("Pyromancer").setXp(21).setGold(15);
			pyromancer.setI1(new Image("img/Pyromancer_frame1.png"));
			pyromancer.setI2(new Image("img/Pyromancer_frame2.png"));
			return pyromancer;
		case 4:
			Enemy runicMonolith = new Enemy();
			runicMonolith.setDmg(3).setHpMax(40).setName("Runic Monolith").setXp(18).setGold(25);
			runicMonolith.setI1(new Image("img/RunicMonolith_frame1.png"));
			runicMonolith.setI2(new Image("img/RunicMonolith_frame2.png"));
			return runicMonolith;
			
		}
		return null;
	}

	public static Enemy enemyFromBossCatalog(int id) {
		switch (id) {
		case 0:
			Enemy worldQuaker = new Enemy() {
				@Override
				public void logic(Enemy en, Player pl, Stage stage) {
					pl.setHp(pl.getHp() - en.getDmg());
				}
			};
			worldQuaker.setDmg(15).setHpMax(135).setName("World Quaker").setXp(80).setGold(100);
			worldQuaker.setI1(new Image("img/WorldQuaker_Frame1.png"));
			worldQuaker.setI2(new Image("img/WorldQuaker_Frame2.png"));
			return worldQuaker;
		case 1:
			Enemy blueWraith = new Enemy();
			blueWraith.setDmg(25).setHpMax(95).setName("Blue Wriath").setXp(80).setGold(100);
			blueWraith.setI1(new Image("img/BlueWraith_Frame1.png"));
			blueWraith.setI2(new Image("img/BlueWraith_Frame2.png"));
			return blueWraith;
		case 2:
			Enemy ceaselessRot = new Enemy(){
				@Override
				public void logic(Enemy en, Player pl, Stage stage) {
					pl.setHp(pl.getHp() - en.getDmg());
					en.setDmg(en.getDmg() * 2);
				}
			};
			ceaselessRot.setDmg(1).setHpMax(100).setName("Ceaseless Rot").setXp(80).setGold(100);
			ceaselessRot.setI1(new Image("img/CeaselessRot_Frame1.png"));
			ceaselessRot.setI2(new Image("img/CeaselessRot_Frame2.png"));
			return ceaselessRot;
		case 3:
			Enemy grandWitness = new Enemy(){
				@Override
				public void logic(Enemy en, Player pl, Stage stage) {
					pl.setHp(pl.getHp() - en.getDmg());
					en.setHp(en.getHp() + (int)(en.getHpMax()) / 20);
					if(en.getHp() > en.getHpMax()) {
						en.setHp(en.getHpMax());
					}
				}
			};
			grandWitness.setDmg(12).setHpMax(120).setName("Grand Witness").setXp(80).setGold(100);
			grandWitness.setI1(new Image("img/GrandWitness_Frame1.png"));
			grandWitness.setI2(new Image("img/GrandWitness_Frame2.png"));
			return grandWitness;
		default:
			return null;
		}
	}
}
