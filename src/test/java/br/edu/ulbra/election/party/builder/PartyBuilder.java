package br.edu.ulbra.election.party.builder;

import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.model.Party;
import br.edu.ulbra.election.party.output.v1.PartyOutput;

import java.util.Collections;
import java.util.List;

public class PartyBuilder {

    public static PartyOutput getPartyOutput() {
        PartyOutput partyOutput = new PartyOutput();
        partyOutput.setId(1L);
        partyOutput.setCode("TT");
        partyOutput.setName("Party test");
        partyOutput.setNumber(50);

        return partyOutput;
    }

    public static List<PartyOutput> getPartyOutputList() {
        return Collections.singletonList(getPartyOutput());
    }

    public static PartyInput getPartyInput() {

        PartyInput partyInput = new PartyInput();
        partyInput.setCode("TT");
        partyInput.setName("Party test");
        partyInput.setNumber(50);

        return partyInput;
    }

    public static Party getParty() {
        Party party = new Party();

        party.setId(1L);
        party.setCode("TT");
        party.setName("Party Test");
        party.setNumber(50);

        return party;
    }

    public static List<Party> getPartyList() {
        return Collections.singletonList(getParty());
    }
}
