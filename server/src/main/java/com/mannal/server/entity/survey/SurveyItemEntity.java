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
@Table(name = "survey_item")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SurveyItemEntity implements Serializable {

    private static final long serialVersionUID = -7635881858924252965L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="survey_item_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private Integer orderNum;

    @Column
    private String useYn;

    @Column
    private Date regDt;

    @Column
    private Date editDt;

    @Column
    private Date delDt;

    @NotNull
    @ManyToOne
    @JoinColumn(name="survey_sub_category_id")
    private SurveySubCategoryEntity surveySubCategoryEntity;

    @NotNull
    @OneToMany(mappedBy = "surveyItemEntity")
    private List<SurveyItemOptionEntity> surveyItemOptionEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurveyItemEntity that = (SurveyItemEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Transient
    private List<SurveyItemResultEntity> surveyResultEntities;
}
