import java.util.concurrent.locks.*;
import java.util.Random;

// Lock objects work very much like the implicit locks used by synchronized code (structured locks).

// Example: Imagine a scenario 2 people are shooting each other (victim and shooter).
// When person victim shoots person shooter, person shooter cannot shoot back (hiding)..
// When person shooter shoots person victim, person victim cannot shoot back (hiding)..

public class Example {
	public static void main(String[] args) {
		final Person shooter = new Person("X");
		final Person victim = new Person("Y");
		
		new Thread(new ShootingLoop(shooter, victim)).start();
		new Thread(new ShootingLoop(victim, shooter)).start();
	}

	public static class Person {
		private final String name;
		private final Lock lock = new ReentrantLock();

		public Person(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public boolean impendingShoot(Person shooter) {
			Boolean myLock = false;
			Boolean yourLock = false;
			
			try {
				myLock = lock.tryLock();
				yourLock = shooter.lock.tryLock();
			} finally {
				if(!(myLock && yourLock)) {
					if(myLock) lock.unlock();
					if(yourLock) shooter.lock.unlock();
				}
			}

			return myLock && yourLock;
		}

		public void shoot(Person shooter) {
			if(impendingShoot(shooter)) {
				try {
					System.out.printf("%s: %s has shot me!\n", this.name, shooter.getName());
					shooter.shootBack(this);
				} finally {
					lock.unlock();
					shooter.lock.unlock();
				}
			} else {
				System.out.printf("%s: %s tried  to shoot me, but saw that I was already shooting him..\n", this.name, shooter.getName());
			}
		}

		public void shootBack(Person shooter) {
			System.out.printf("%s: %s shot back!!\n", this.name, shooter.getName());
		}
	}

	public static class ShootingLoop implements Runnable {
		private Person shooter;
		private Person victim;

		public ShootingLoop(Person shooter, Person victim) {
			this.shooter = shooter;
			this.victim = victim;
		}

		public void run() {
			Random rand = new Random();
			while(true) { // infinite loop
				try {
					Thread.sleep(rand.nextInt(300));
				} catch(InterruptedException e) {} // do nothing.
				
				victim.shoot(shooter);
			}
		}
	}
}
