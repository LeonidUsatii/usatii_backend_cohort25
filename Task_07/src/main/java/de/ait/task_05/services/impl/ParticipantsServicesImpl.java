package de.ait.task_05.services.impl;

import de.ait.task_05.dto.NewParticipantDto;
import de.ait.task_05.dto.ParticipantDto;
import de.ait.task_05.exceptions.RestException;
import de.ait.task_05.models.Participant;
import de.ait.task_05.repositories.ParticipantsRepository;
import de.ait.task_05.services.ParticipantsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static de.ait.task_05.dto.ParticipantDto.from;

@RequiredArgsConstructor
@Service
public class ParticipantsServicesImpl implements ParticipantsServices {

    private final ParticipantsRepository participantsRepository;
    @Override
    public ParticipantDto addParticipant(NewParticipantDto newParticipant) {

        if (participantsRepository.existsByEmail(newParticipant.getEmail())) {
            throw new RestException(HttpStatus.CONFLICT,
                    "User with email <" + newParticipant.getEmail() + "> already exists");
        }

        Participant participant = Participant.builder()
                .firstName(newParticipant.getFirstName())
                .lastName(newParticipant.getLastName())
                .email(newParticipant.getEmail())
                .build();

        participantsRepository.save(participant);

        return from(participant);
    }

}
