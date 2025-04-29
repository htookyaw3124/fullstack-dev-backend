package com.avalant.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attachment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

    @Id
    @Column(name = "attachment", length = 32, nullable = false)
    private String attachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "file_name", length = 120)
    private String fileName;

    @Column(name = "ref_id", length = 400)
    private String refId;
}
