package dev.snowdrop.example.component;

import dev.snowdrop.vertx.mail.MailClient;
import dev.snowdrop.vertx.mail.MailMessage;
import dev.snowdrop.vertx.mail.MailResult;
import dev.snowdrop.vertx.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static javax.ws.rs.core.Response.noContent;

@Component
public class MailHandler {


    private final MailClient mailClient;

    public MailHandler(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    public Mono<MailResult> send(ServerRequest request) {
        return request.formData()
                .log()
                .map(this::formToMessage)
                .flatMap(mailClient::send);
    }

    private MailMessage formToMessage(MultiValueMap<String, String> form) {
        return new SimpleMailMessage()
                .setFrom(form.getFirst("from"))
                .setTo(form.get("to"))
                .setSubject(form.getFirst("subject"))
                .setText(form.getFirst("text"));
    }
}
