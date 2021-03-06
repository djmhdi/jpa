package jpabook.start.loading.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "CH08_LOADING_TEAM")
@SequenceGenerator(
	name = "CH08_LOADING_TEAM_SEQUENCE"
	, sequenceName = "CH08_LOADING_TEAM_SEQ"
	, initialValue = 1
	, allocationSize = 1
)
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CH08_LOADING_TEAM_SEQ")
	@Column(name = "TEAM_ID")
	private long id;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private List<Member> member = new ArrayList<Member>();
	
	private String name;
	
	public Team() { }
	
	public Team(String name) {
		this.name = name;
	}

	public long getId() { return id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public List<Member> getMember() { return member; }
	public void addMember(Member member) {
		if(!this.member.contains(member)) {
			this.member.add(member);
		}
		
		if(member.getTeam() != this) {
			member.setTeam(this);
		}
	}
}
