package com.mail;

import com.model.Vote;

public class Test {
public static void main(String[] args) {
	VoteDao v = new VoteDao();
	Vote vObj = new Vote();
	vObj.setUsername("sumanthfromjava");
	vObj.setVote("Y");
	v.insertVoteToRemote(vObj);
}
}
