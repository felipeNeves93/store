package com.store.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageService implements MessageService<SpecificRecord> {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Override
    public void send(String destination, SpecificRecord payload) {
        var result = kafkaTemplate.send(destination, payload);

        result.addCallback(new ListenableFutureCallback<SendResult<String, SpecificRecord>>() {

            @Override
            public void onSuccess(SendResult<String, SpecificRecord> result) {
                log.info("Sent message = {} | Offset = {}", payload, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message {} due to : {}", payload, ex.getMessage());
            }
        });
    }
}
