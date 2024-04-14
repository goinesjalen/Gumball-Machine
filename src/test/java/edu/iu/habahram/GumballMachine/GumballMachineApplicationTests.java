package edu.iu.habahram.GumballMachine;

import edu.iu.habahram.GumballMachine.model.GumballMachine2;
import edu.iu.habahram.GumballMachine.model.GumballMachineState;
import edu.iu.habahram.GumballMachine.model.IGumballMachine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GumballMachineApplicationTests {

	@Test
	public void testGumballMachineStateTransition() {
		// Create a GumballMachine2 instance with initial state NO_QUARTER and 120 gumballs
		IGumballMachine gumballMachine = new GumballMachine2("1", "NO_QUARTER", 120);

		// Check initial state
		assertEquals("NO_QUARTER", gumballMachine.getTheStateName());

		// Insert a quarter
		gumballMachine.insertQuarter();

		// Check state after inserting a quarter
		assertEquals("HAS_QUARTER", gumballMachine.getTheStateName());

		// Eject the quarter
		gumballMachine.ejectQuarter();

		// Check state after ejecting the quarter
		assertEquals("NO_QUARTER", gumballMachine.getTheStateName());

		// Turn the crank without inserting a quarter
		gumballMachine.turnCrank();

		// Check state after turning the crank without inserting a quarter
		assertEquals("NO_QUARTER", gumballMachine.getTheStateName());

	}

	@Test
	public void testGumballMachineStates() {
		// Create a GumballMachine2 instance with initial state NO_QUARTER and 120 gumballs
		IGumballMachine gumballMachine = new GumballMachine2("1", "NO_QUARTER", 120);

		// Check initial state
		assertEquals("NO_QUARTER", gumballMachine.getTheStateName());

		// Change the State
		gumballMachine.changeTheStateTo(GumballMachineState.HAS_QUARTER);

		// Check state after inserting a quarter
		assertEquals("HAS_QUARTER", gumballMachine.getTheStateName());

	}
}
