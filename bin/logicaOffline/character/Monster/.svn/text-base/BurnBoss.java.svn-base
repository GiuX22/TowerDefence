package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class BurnBoss extends AbstractMonster{
		
	
	private int score;
	private boolean isVisible;
	private int ContRechargeMonster;
	
	public BurnBoss( World world) {
		super(world.getGenerator().getX(), world.getGenerator().getY(), world);
		life=2500;
		score=1050;
		speed= 0.05;
		defaultlife=2500;
		isVisible=true;
		setTimeTransition(2.5);
		path=world.getPathMonster();
	}

	@Override
	public int getScore() {
		return score;
	}


	// I BOSS SONO PENSATI IN MODO CHE MAN MANO CHE CAMMINANO LA LORO VITA AUMENTA IN MODO CHE MEGLIO UCCIDERLI IL PRIMA POSSIBILE... 
	// NATURALMENTE AVENDO TRE BOSSO DI LIVELLO CRESCENDE, QUELLO PIU DEBOLE DOVRA CAMMINARE DI PIU PER AUMENTARE LA SUA VITA, QUELLO MEDIO UN P������������������ DI MENO
	// E QUELLO PIU FORTE DOVRA CAMMINARE POCO PER POTERSI RICARICARE.
	
	// MAN MANO CHE AVANZANO AUMENTA ANCHE LA LORO VELOCITA
	
	@Override
	public void update() {
		ContRechargeMonster++;
		super.update();
		if(ContRechargeMonster>=50)
		{
			life=life+50;
			this.speed=this.speed+0.01;
			ContRechargeMonster=0;
		}
	}


}
