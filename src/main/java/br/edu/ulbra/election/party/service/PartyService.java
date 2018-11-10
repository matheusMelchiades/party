package br.edu.ulbra.election.party.service;


import br.edu.ulbra.election.party.exception.GenericOutputException;
import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.model.Party;
import br.edu.ulbra.election.party.output.v1.GenericOutput;
import br.edu.ulbra.election.party.output.v1.PartyOutput;
import br.edu.ulbra.election.party.repository.PartyRepository;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    private final ModelMapper modelMappaer;

    private static final String MESSAGE_INVALID_ID = "Invalid id";
    private static final String MESSAGE_PARTY_NOT_FOUND = "Party not found";

    @Autowired
    public PartyService(PartyRepository partyRepository, ModelMapper modelMappaer) {
        this.partyRepository = partyRepository;
        this.modelMappaer = modelMappaer;
    }

    public List<PartyOutput> getAll(){
        Type partyOutputListType = new TypeToken<List<PartyOutput>>(){}.getType();
        return modelMappaer.map(partyRepository.findAll(), partyOutputListType);
    }

    public PartyOutput create(PartyInput partyInput){
        validateInput(partyInput);
        Party party = modelMappaer.map(partyInput, Party.class);
        party = partyRepository.save(party);
        return modelMappaer.map(party, PartyOutput.class);
    }

    public PartyOutput getById(Long partyID){
        if (partyID == null) {
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Party party = partyRepository.findById(partyID).orElse(null);

        if (party == null) {
            throw  new GenericOutputException(MESSAGE_PARTY_NOT_FOUND);
        }

        return modelMappaer.map(party, PartyOutput.class);
    }

    public PartyOutput update(Long partyID, PartyInput partyInput) {
        if (partyID == null) {
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        validateInput(partyInput);

        Party party = partyRepository.findById(partyID).orElse(null);

        if (party == null) {
            throw new GenericOutputException(MESSAGE_PARTY_NOT_FOUND);
        }

        party.setCode(partyInput.getCode());
        party.setName(partyInput.getName());
        party.setNumber(partyInput.getNumber());

        party = partyRepository.save(party);

        return modelMappaer.map(party, PartyOutput.class);
    }

    public GenericOutput delete(Long partyID) {
        if (partyID == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Party party = partyRepository.findById(partyID).orElse(null);

        if (party == null) {
            throw new GenericOutputException(MESSAGE_PARTY_NOT_FOUND);
        }

        partyRepository.delete(party);

        return new GenericOutput("Party deleted");
    }

    private void validateInput(PartyInput partyInput) {
        if (StringUtils.isBlank(partyInput.getCode())) {
            throw new GenericOutputException("Invalid Code");
        }
        if (StringUtils.isBlank(partyInput.getName())) {
            throw new GenericOutputException("Invalid name");
        }
        if (StringUtils.isBlank(" " + partyInput.getNumber())){
            throw new GenericOutputException("Invalid Number");
        }
    }
}
