package com.grievance.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class grievance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "citizen_name", nullable = false)
    private String citizenName;

    @Column(name = "citizen_email")
    private String citizenEmail;

    @Column(name = "citizen_phone")
    private String citizenPhone;

    //  Grievance Details
    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GrievanceCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GrievancePriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GrievanceStatus status;
    //  Department Assignment
    // ---------------------------------------------------------------
    @Column(name = "assigned_department")
    private String assignedDepartment;

    @Column(name = "resolution_notes", columnDefinition = "TEXT")
    private String resolutionNotes;

    // ---------------------------------------------------------------
    //  AI Classification Metadata
    // ---------------------------------------------------------------
    @Column(name = "ai_suggested_category")
    private String aiSuggestedCategory;

    @Column(name = "ai_suggested_priority")
    private String aiSuggestedPriority;

    @Column(name = "ai_suggested_department")
    private String aiSuggestedDepartment;

    @Column(name = "ai_confidence_score")
    private Double aiConfidenceScore;

    @Column(name = "ai_reasoning", columnDefinition = "TEXT")
    private String aiReasoning;

    @Column(name = "ai_sentiment")
    private String aiSentiment;

    // ---------------------------------------------------------------
    //  Tracking
    // ---------------------------------------------------------------
    @Column(name = "tracking_number", unique = true, nullable = false)
    private String trackingNumber;

    // ---------------------------------------------------------------
    //  Audit Timestamps
    // ---------------------------------------------------------------
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    // ---------------------------------------------------------------
    //  Lifecycle Hooks
    // ---------------------------------------------------------------
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = GrievanceStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
