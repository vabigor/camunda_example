package com.example.workflow.service.bp_events.end;

import org.springframework.stereotype.Component;

@Component
public class EndService {

    public void end(){
        System.out.println("end process");
    }
}
