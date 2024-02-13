package com.forms.api.forms.domain;

import com.forms.api.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Forms {
    @Id @Column(name = "forms_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false)
    private FormsAccess access;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false)
    private FormsStatus status;

    @Column(updatable = false)
    private LocalDateTime startedAt;

    @Column(updatable = false)
    private LocalDateTime endedAt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Forms(Member member, String title, String description, FormsAccess access, FormsStatus status, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.access = access;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
