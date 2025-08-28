package org.example.back.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


    @RestController
    public class RandomDataSseController {

        private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

//        public void publish(String data) {
//            sink.tryEmitNext(data);
//        }

        @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public Flux<String> stream() {
            return sink.asFlux();
        }
    }

