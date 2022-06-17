package br.com.coffe.explorer.core.domain.entity;

import lombok.Getter;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class Coffe {

    private Coffe(String id,
                  String description,
                  LocalDateTime creationDateTime,
                  String createdBy,
                  Flavor flavor,
                  List<String> imagesUrls) {
        this.id = id;
        this.description = description;
        this.creationDateTime = creationDateTime;
        this.createdBy = createdBy;
        this.flavor = flavor;
        this.imagesUrls = imagesUrls;
    }

    private final String id;
    private final String description;
    private final LocalDateTime creationDateTime;
    private final String createdBy;
    private final Flavor flavor;
    private final List<String> imagesUrls;

    public static class Builder {

        private String id;
        private String description;
        private LocalDateTime creationDateTime;
        private String createdBy;
        private Flavor flavor;
        private List<String> imagesUrls;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder withCreationDateTime(LocalDateTime creationDateTime) {
            this.creationDateTime = creationDateTime;
            return this;
        }

        public Builder withFlavor(Flavor flavor) {
            this.flavor = flavor;
            return this;
        }

        public Builder withImages(List<String> imagesUrls) {
            this.imagesUrls = imagesUrls;
            return this;
        }

        public Coffe build() {
            if (this.id == null) {
                this.withId(UUID.randomUUID().toString());
            }
            if (this.creationDateTime == null) {
                this.withCreationDateTime(LocalDateTime.now(Clock.systemUTC()));
            }
            return new Coffe(
                    this.id,
                    this.description,
                    this.creationDateTime,
                    this.createdBy,
                    this.flavor,
                    this.imagesUrls
            );
        }

    }


}
