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
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE forms SET status = 'DELETED' WHERE forms_id = ?")
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
    @Column(nullable = false)
    private FormsAccess access;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FormsStatus status;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime endedAt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Setter
    private LocalDateTime modifiedAt;

    @Builder
    public Forms(Long id, Member member, String title, String description, FormsAccess access, FormsStatus status, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime createdAt, LocalDateTime modifiedAt) {
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

    public void setTitle(String title) {
        if (title == null) return;
        this.title = title;
    }

    public void setDescription(String description) {
        if (description == null) return;
        this.description = description;
    }

    public void setAccess(FormsAccess access) {
        if (access == null) return;
        this.access = access;
    }

    public void setStatus(FormsStatus status) {
        if (status == null) return;
        this.status = status;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        if (startedAt == null) return;
        this.startedAt = startedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        if (endedAt == null) return;
        this.endedAt = endedAt;
    }
}
