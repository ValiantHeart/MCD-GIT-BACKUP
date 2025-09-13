/*
	**************************************************************************
	 VotingMachine class for Project 2 of CS211-220 (Spring 2017)
	 Coded by Jeffrey Wilhite	
	**************************************************************************
*/
public class VotingMachine
{
	//initializing intance methods
	private CandidateList candidates;
	private VoteList votes;
	private String office;
	private Log log;
	
	public VotingMachine(String office) //Constructor type 1
	{
		this.office = office;
	}
	
	public VotingMachine(String office, CandidateList candidates) //Constructor type 2
	{
		this.office = office;
		this.candidates = candidates;
	}
	
	public VotingMachine(String office, CandidateList candidates, VoteList votes) //Constructor type 3
	{
		this.office = office;
		this.candidates = candidates;
		this.votes = votes;
	}
	// VV UNFINISHED METHODS VV	
	public void addVote(Vote vote)
	{
		
	}
	
	public void addCandidate(String candidate)
	{
		
	}

/* 	public VoteList reportVotes()
	{
		
	}
	
	public CandidateList reportCandidates()
	{
		
	}
	
	public String reportOffice()
	{
		
	}
	
	public boolean validateVotes()
	{
		
	} 
	
	*/
	
	
}