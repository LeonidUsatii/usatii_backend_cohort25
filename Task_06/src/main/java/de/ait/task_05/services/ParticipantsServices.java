package de.ait.task_05.services;

import de.ait.task_05.dto.NewParticipantDto;
import de.ait.task_05.dto.ParticipantDto;
import de.ait.task_05.models.Participant;

public interface ParticipantsServices {
    ParticipantDto addParticipant(NewParticipantDto newParticipant);
}
