package au.com.carsguide.memberservice.eventsourcing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemberEventService {

    @Autowired
    private StringRedisTemplate eventTemplate;
  
    public void addEvent(MemberEvent memberEvent) {
        eventTemplate.opsForList().rightPush(memberEvent.id, memberEvent.serialize());
    }
}