package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mannal.server.constance.ApplicationCoreConstant;
import com.sun.istack.NotNull;
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
@Table(name = "survey_item_result")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyItemResultEntity implements Serializable {

    private static final long serialVersionUID = 2309277319970353311L;
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_item_result_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column
    private UUID surveyItemId;

    @NotNull
    @Column
    private UUID requestId;

    @NotNull
    @Column
    private String type;

    @NotNull
    @Column
    private Integer totalScore;

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

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name="survey_item_id")
//    private SurveyItemEntity surveyItemEntity;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurveyItemResultEntity that = (SurveyItemResultEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
