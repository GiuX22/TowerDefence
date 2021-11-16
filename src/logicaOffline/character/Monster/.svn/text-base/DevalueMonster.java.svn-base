package logicaOffline.character.Monster;

import logicaOffline.world.World;

public class DevalueMonster extends AbstractMonster{

	
	//IL VALORE (SCORE) DI QUESTO MOSTRO DIMINUIRA DI VOLTA IN VOLTA
		
		private int score;
		private boolean isVisible;
		private int CountNumberUpdate;

		public DevalueMonster( World world) {
			super(world.getGenerator().getX(), world.getGenerator().getY(), world);
			life=350;
			score=280;
			speed= 0.08;
			isVisible=true;
			defaultlife=350;
			setTimeTransition(2);

			path=world.getPathMonster();
		}
		
		//SET METHOD
		public void setVisible(boolean isVisible) {
			this.isVisible = isVisible;
		}


		@Override
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		
		//GET METHOD

		@Override
		public double getSpeed() {
			return speed;
		}
		@Override
		public int getScore() {
			return score;
		}
		@Override
		public int getLife() {
			return life;
		}

		@Override
		public void update()
		{
			CountNumberUpdate++;
			super.update();
			if(CountNumberUpdate>=10)
			{
				if(this.score>=20)
				{
				this.score=this.score-1;
				}
				CountNumberUpdate=0;
			}
		}
}
