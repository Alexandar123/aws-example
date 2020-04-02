package com.quantox.main.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quantox.main.domain.Machine;
import com.quantox.main.domain.User;
import com.quantox.main.enums.Status;
import com.quantox.main.repositories.MachineRepo;

@RestController
@RequestMapping("machine/")
public class MachineFunctionalities extends UserImpl{
	
	@Autowired
	MachineRepo machineRepo;
	
	public MachineFunctionalities(MachineRepo machineRepo) {
		super();
		this.machineRepo = machineRepo;
	}

	@RequestMapping(value="/start", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public synchronized Machine start(@RequestBody Machine machine) {
		
		if (machine.getStatus() != Status.STOPED) {
			try {
				Thread.sleep(15000);
				machine.setStatus(Status.RUNNING);
				return machine;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value="/stop", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public synchronized Machine stop(@RequestBody Machine machine) {
		if (machine.getStatus() == Status.RUNNING) {
			try {
				Thread.sleep(10000);
				machine.setStatus(Status.RUNNING);
				return machine;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value="/restart", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public synchronized Machine restart(@RequestBody Machine machine) {
		if (machine.getStatus() == Status.RUNNING) {
			try {
				long start = System.currentTimeMillis();
				Thread.sleep(10000);
				if ((System.currentTimeMillis() - start) >= 5000 && (System.currentTimeMillis() - start) < 10000)
					machine.setStatus(Status.STOPED);

				if ((System.currentTimeMillis() - start) > 10000)
					machine.setStatus(Status.RUNNING);
				return machine;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public synchronized Machine create(Machine machine, User user) {

		machine.setStatus(Status.STOPED);
		machine.setUID(String.valueOf(Math.random()));
		machine.setUser(user);
		return machineRepo.save(machine);
	}
	
	@RequestMapping(value="/destroy", method = RequestMethod.POST)
	public void destroy(Machine machine) {
		if(machine.getStatus() == Status.STOPED) {
			machine.setActive(false);
		}
	}
	
	@RequestMapping(value="/search/{filter}/{value}", method = RequestMethod.GET)
	public List<Machine> search(@PathVariable("filter") String filter, @PathVariable("value") Object value) {
		ArrayList<Machine> machines = new ArrayList<Machine>();
		switch(filter) {
		case "name" : {
			return machineRepo.getMachineByFilterName((String) value);
		}
		case "status" :{
			return machineRepo.getMachineByFilterStatus((String) value);
		}
		case "dateFrom" : {
			return machineRepo.getMachineByFilterDateFrom((Date) value);
		}
		case "dateTo" : {
			return machineRepo.getMachineByFilterDateTo((Date) value);
		}
		default : {
			return null;
		}
		}
	}
	
	
	
}
