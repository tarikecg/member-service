package au.com.carsguide.memberservice.eventsourcing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.carsguide.memberservice.models.Member;

public class MemberEvent extends BaseEvent<String> {

    public final Member member;
    public final EventStatus status;

    public enum EventStatus {
        CREATED,
        UPDATED,
        DELETED
    }

    public MemberEvent(Member member, EventStatus status) {
        super(member.getId().toString());
        this.member = member;
        this.status = status;
    }

    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return this.toString();
        }
    }
}