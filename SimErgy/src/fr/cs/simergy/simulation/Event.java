package fr.cs.simergy.simulation;
/**
 * Defines the general behavior of our system's events
 *
 */
public abstract class Event {
	protected abstract void actions();
	/**
	 * Schedules an event at a given time
	 * @param evTime time in which we schedule our event
	 */
	public final void schedule(double evTime){
		if (evTime < time){
			throw new RuntimeException("attempt to schedule event in the past");
		}
		cancel();
		eventTime = evTime;
		Event ev = SQS.pred;
		while(ev.eventTime > eventTime)
			ev = ev.pred;
		pred = ev;
		suc = ev.suc;
		ev.suc = suc.pred = this;
	}
	/**
	 * Cancels an event that was already scheduled
	 */
	public final void cancel(){
		if (suc != null){
			suc.pred = pred;
			pred.suc = suc;
			suc = pred = null;
		}
	}
	/**
	 * Returns the time in which the Event is scheduled
	 * @return schedule time
	 */
	public final static double time(){
		return time;
	}
	/**
	 * Runs the simulation during a given time period
	 * @param period 
	 */
	public final static void runSimulation(double period){
		while(SQS.suc != SQS){
			Event ev = SQS.suc;
			time = ev.eventTime;
			if (time > period)
				break;
			ev.cancel();
			ev.actions();
		}
		stopSimulation();
	}
	/**
	 * Stops the Simulation execution
	 */
	public final static void stopSimulation(){
		while (SQS.suc != SQS)
			SQS.suc.cancel();
		time = 0;
	}
	/**
	 * Executes only the first event in the events queue
	 */
	public final static void executeFirstEvent(){
		if(SQS.suc != SQS){
			Event ev = SQS.suc;
			time = ev.eventTime;
			ev.cancel();
			ev.actions();
		}
	}
	
	
	public void report(){	
	}
	
	public final static Event SQS = new Event(){
		{ Event pred = this; Event suc = this; this.pred = pred; this.suc = suc;}
		protected void actions() {}
	};
	
	private static double time = 0;
	private double eventTime;
	public Event pred, suc;
	
	
}
