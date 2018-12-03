package br.edu.ulbra.election.party.service;


import br.edu.ulbra.election.party.TestConfig;
import br.edu.ulbra.election.party.builder.PartyBuilder;
import br.edu.ulbra.election.party.exception.GenericOutputException;
import br.edu.ulbra.election.party.input.v1.PartyInput;
import br.edu.ulbra.election.party.output.v1.PartyOutput;
import br.edu.ulbra.election.party.repository.PartyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(PartyService.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class PartyServiceTest {

    @MockBean
    private PartyRepository partyRepository;

    @Autowired
    private PartyService partyService;

    @Test
    public void shouldReturnEmptyList() {
        given(partyRepository.findAll())
                .willReturn(new ArrayList<>());

        List<PartyOutput> partyOutputList = partyService.getAll();
        Assert.assertEquals(0, partyOutputList.size());
    }

    @Test
    public void shouldFindAllElements() {
        given(partyRepository.findAll())
                .willReturn(PartyBuilder.getPartyList());

        List<PartyOutput> partyOutputList = partyService.getAll();
        Assert.assertEquals(1, partyOutputList.size());
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailGetByIdNotFound() {
        given(partyRepository.findById(anyLong()))
            .willReturn(Optional.empty());
        partyService.getById(1L);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailGetByIdNUll() {
        given(partyRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        partyService.getById(null);
    }

    @Test
    public void shouldGetById() {
        given(partyRepository.findById(anyLong()))
                .willReturn(Optional.of(PartyBuilder.getParty()));
        PartyOutput partyOutput = partyService.getById(1L);
        Assert.assertEquals((Long) 1L, partyOutput.getId());
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateInvalidCode() {
        PartyInput partyInput = PartyBuilder.getPartyInput();
        partyInput.setCode(null);
        partyService.create(partyInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateEmptyCode() {
        PartyInput partyInput = PartyBuilder.getPartyInput();
        partyInput.setCode("");
        partyService.create(partyInput);
    }
}












