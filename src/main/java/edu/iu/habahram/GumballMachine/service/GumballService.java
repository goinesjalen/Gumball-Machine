package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.*;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return stateChange(id, 1);
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        return stateChange(id, 2);
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException {
        return stateChange(id, 3);
    }

    // On state change, the stateChange method is called, and using a switch it delegates the method to the machine.
    public TransitionResult stateChange(String id, int action) throws IOException{
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
        TransitionResult result = switch (action) {
            case 1 -> machine.insertQuarter();
            case 2 -> machine.ejectQuarter();
            case 3 -> machine.turnCrank();
            default -> null;
        };
        if (result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}
