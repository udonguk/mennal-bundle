package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mannal.server.constance.ApplicationCoreConstant;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey_item_option_result")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyItemOptionResultEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_item_option_result_id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private UUID surveyItemOptionId;

    @Column
    private UUID requestId;

    @Column
    @ColumnDefault("N")
    private String checked;

    @Column
    @ColumnDefault("Y")
    private String useYn;

    @Column
    @DateTimeFormat(pattern = ApplicationCoreConstant.DEFAULT_TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime regDt;

    @Column
    @DateTimeFormat(pattern = ApplicationCoreConstant.DEFAULT_TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime editDt;

    @Column
    private LocalDateTime delDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurveyItemOptionResultEntity that = (SurveyItemOptionResultEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
