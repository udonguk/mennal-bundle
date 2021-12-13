package com.mannal.server.entity.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey_sub_category")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveySubCategoryEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_sub_category_id", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String code;
    private String useYn;
    private Date regDt;
    private Date editDt;
    private Date delDt;

    @NotNull
    @ManyToOne
    @JoinColumn(name="survey_category_id")
    private SurveyEntity surveyEntity;

    @NotNull
    @OneToMany(mappedBy = "surveySubCategoryEntity")
    private List<SurveyItemEntity> surveyItemEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurveySubCategoryEntity that = (SurveySubCategoryEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
