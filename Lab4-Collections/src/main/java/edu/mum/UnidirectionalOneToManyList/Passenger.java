package cmp1.UnidirectionalOneToManyList;

import java.util.*;
import javax.persistence.*;

@Entity
public class Passenger {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "passenger")
	@OrderColumn(name = "sequence")
	List<Flight> flightlist = new ArrayList();

	public Passenger(String name) {
		this.name = name;
	}

	public Passenger() {
	}

	public long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flight> getFlightlist() {
		return Collections.unmodifiableList(flightlist);
	}

	public boolean addFlight(Flight flight) {
		return flightlist.add(flight);
	}

	public boolean removeFlight(Flight flight) {
		return flightlist.remove(flight);
	}
}
